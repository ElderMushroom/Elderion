package net.danielgolan.elderion.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SnowyBlock.class)
public interface SnowyBlockAccessor {
    @Invoker("isSnow")
    static boolean isSnow(BlockState state) {
        throw new AssertionError();
    }
}
