package net.danielgolan.elderion.mixin;

import net.danielgolan.elderion.Elderion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow
    public static void replace(BlockState state, BlockState newState, WorldAccess world, BlockPos pos, int flags) {
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
    at = @At("RETURN"), cancellable = true)
    private static void getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir){
        List<ItemStack>  items = new ArrayList<>();
        List<ItemStack> origin = cir.getReturnValue();

        int level = EnchantmentHelper.getLevel(Elderion.AUTO_SMELTING, stack);

        if (level == 0 || !(entity instanceof PlayerEntity player)) {
            cir.setReturnValue(origin);
            return;
        }


        for (ItemStack item : origin) {
            Optional<SmeltingRecipe> recipe = world.getRecipeManager().listAllOfType(RecipeType.SMELTING).stream().filter(smeltingRecipe ->
                    smeltingRecipe.getIngredients().get(0).test(item)).findFirst();
            if (recipe.isPresent()) {

                if (level <= 2) {
                    if (player.getInventory().contains(ItemTags.COALS)) {
                        if (player.getRandom().nextInt(level == 2 ? 10 : 3) == 1)
                            Inventories.remove(player.getInventory(), itemStack -> ItemTags.COALS.contains(itemStack.getItem()), 1, false);
                    } else if (player.getEnderChestInventory().containsAny(new HashSet<>(ItemTags.COALS.values()))) {
                        if (player.getRandom().nextInt(level == 2 ? 10 : 3) == 1)
                            Inventories.remove(player.getEnderChestInventory(), itemStack -> ItemTags.COALS.contains(itemStack.getItem()), 1, false);
                    } else {
                        items.add(item);
                        continue;
                    }
                }

                ItemStack result = recipe.get().getOutput();
                result.setCount(result.getCount());
                items.add(result);

                if (level >= 2) {
                    int xp = (int) recipe.get().getExperience();
                    world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), level == 3 ? xp : xp / 2));
                }

            } else {
                items.add(item);
            }
        }

        cir.setReturnValue(items);
    }
}
