package net.danielgolan.elderion.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.danielgolan.elderion.mixin.SnowyBlockAccessor.isSnow;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Inject(method = "getStateForNeighborUpdate", at = @At("RETURN"), cancellable = true)
    private void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                           WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (direction == Direction.UP && state.getBlock() == Blocks.STONE) {
            cir.setReturnValue(state.with(Properties.SNOWY, isSnow(state)));
        }
    }

}
