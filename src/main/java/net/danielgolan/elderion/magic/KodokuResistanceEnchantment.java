package net.danielgolan.elderion.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class KodokuResistanceEnchantment extends Enchantment {
    public KodokuResistanceEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEARABLE, new EquipmentSlot[]{EquipmentSlot.CHEST, EquipmentSlot.HEAD, EquipmentSlot.FEET, EquipmentSlot.LEGS});
    }

    @Override
    public int getMinPower(int level) {
        return 20 + 10 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return (int) (getMinPower(level) * 1.5);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
