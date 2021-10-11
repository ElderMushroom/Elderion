package net.danielgolan.elderion.mixin;

import net.danielgolan.elderion.client.ElderionHeartType;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.danielgolan.elderion.mixin.HeartTypeInvoker.invokeFromPlayerState;


@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    private static ElderionHeartType heartType;

    //@Inject(method = "renderHealthBar", at = @At("HEAD"))
    private void renderBar(MatrixStack matrices, PlayerEntity player, int x, int y, int lines,
                           int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption,
                           boolean blinking, CallbackInfo ci) {
        InGameHud.HeartType type = invokeFromPlayerState(player);

        heartType = (type == InGameHud.HeartType.NORMAL) ? player.isOnFire() ? ElderionHeartType.burning
                : player.getRecentDamageSource() == DamageSource.DROWN ? ElderionHeartType.drowning
                : player.getActiveStatusEffects().get(StatusEffects.REGENERATION) != null ? ElderionHeartType.regeneration
                : ElderionHeartType.vanilla : ElderionHeartType.vanilla;
    }

    //@Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void render(MatrixStack matrices, InGameHud.HeartType type, int x, int y, int v, boolean blinking,
                        boolean halfHeart, CallbackInfo ci) {
        if (heartType != ElderionHeartType.vanilla) {
           drawTexture(matrices, x, y, switch (heartType) {
               case burning -> 10;
               case regeneration -> 11;
               case drowning -> 12;

               case vanilla -> throw new AssertionError("`vanilla` option is asserted to not happen, due to being in an if; " +
                       "It still happened, tho. That's a fatal error provided to you by DanielGolan, using the Elderion mod, " +
                       "since another heart adding mod is installed. Try to remove it.");
               default -> throw new AssertionError("That's a fatal error provided to you by DanielGolan, using the " +
                       "Elderion mod, since another heart adding mod is installed. Try to remove it.");
           }, v, 9, 9);
            ci.cancel();
        }
    }

}
