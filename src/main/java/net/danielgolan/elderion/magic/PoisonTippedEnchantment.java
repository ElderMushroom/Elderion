package net.danielgolan.elderion.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import static net.danielgolan.elderion.magic.PoisonousEnchantment.poison;

public class PoisonTippedEnchantment extends Enchantment {
    public PoisonTippedEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 20 + 10 * (level - 1);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.FLAME;
    }

    @Override
    public int getMaxPower(int level) {
        return (int) (getMinPower(level) * 1.5);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        poison(user, target, level);
    }
}
