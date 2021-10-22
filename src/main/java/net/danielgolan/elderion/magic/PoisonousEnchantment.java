package net.danielgolan.elderion.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;


public class PoisonousEnchantment extends Enchantment {
    public PoisonousEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 20 + 10 * (level - 1);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.FIRE_ASPECT;
    }

    @Override
    public int getMaxPower(int level) {
        return (int) (getMinPower(level) * 1.5);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        poison(user, target, level);
    }

    public static void poison(LivingEntity user, Entity target, int level) {
        if (!(target instanceof LivingEntity entity)) {
            System.out.println("Tried to inflict koduko to " + target.getCustomName() + " (" + target.getName().asString() + ") [NOT LIVING]");
            return;
        }

        int head_res = EnchantmentHelper.getLevel(Enchantments.PROTECTION, entity.getEquippedStack(EquipmentSlot.HEAD));
        int chest_res = EnchantmentHelper.getLevel(Enchantments.PROTECTION, entity.getEquippedStack(EquipmentSlot.CHEST));
        int legs_res = EnchantmentHelper.getLevel(Enchantments.PROTECTION, entity.getEquippedStack(EquipmentSlot.LEGS));
        int boots_res = EnchantmentHelper.getLevel(Enchantments.PROTECTION, entity.getEquippedStack(EquipmentSlot.FEET));
        int resistance = Math.max(Math.max(head_res, chest_res), Math.max(legs_res, boots_res)) - 1;
        if (resistance == -1) resistance = 0;
        else {
            if (head_res > 0) resistance++;
            if (chest_res > 0) resistance++;
            if (legs_res > 0) resistance++;
            if (boots_res > 0) resistance++;
        }

        int strength = level - resistance;

        //If target entity resistance is bigger than level or it's not affected by splash potions cancel the act.
        if (strength <= 0 || !entity.isAffectedBySplashPotions()) {
            System.out.println("Tried to inflict koduko to " + target.getCustomName() +
                    " (" + target.getName().asString() + ") [DOES NOT SUPPORTS SPLASH POTIONS] " + entity.getActiveStatusEffects().toString());
            return;
        }


        int duration = strength * 30;
        if (strength >= 3) duration += 45;
        if (strength >= 5) duration += 75;

        int amplifier = (int) (strength / 1.5);
        boolean ambient = resistance > 2 && level < 5;

        ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,
                duration, amplifier, ambient, true, true), user);

        System.out.println("Tried to inflict koduko to " + target.getCustomName() + " (" + target.getName().asString() + ") [DONE] " + entity.getActiveStatusEffects().toString());
    }
}
