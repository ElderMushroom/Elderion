package net.danielgolan.elderion.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(InGameHud.HeartType.class)
public interface HeartTypeInvoker {
    @Invoker
    static InGameHud.HeartType invokeFromPlayerState(PlayerEntity player) {
        return null;
    }
}
