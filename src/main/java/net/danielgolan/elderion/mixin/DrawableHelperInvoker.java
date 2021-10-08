package net.danielgolan.elderion.mixin;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DrawableHelper.class)
public interface DrawableHelperInvoker {
    @Invoker("drawTexture")
    void invokeDrawTexture(MatrixStack matrices, int x, int y, int u, int v, int width, int height);

}
