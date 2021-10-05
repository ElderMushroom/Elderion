package net.danielgolan.elderion.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow protected abstract Block asBlock();

    @Shadow public abstract BlockState getDefaultState();



    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if (asBlock().equals(Blocks.STONE)) {
            BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
            cir.setReturnValue(getDefaultState().with(Properties.SNOWY, SnowyBlockAccessor.isSnow(state)));
        }
    }

    @Inject(method = "appendProperties", at = @At("RETURN"))
    private void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        //if (asBlock().equals(Blocks.STONE)) {
            builder.add(Properties.SNOWY);
        //}
    }

}
