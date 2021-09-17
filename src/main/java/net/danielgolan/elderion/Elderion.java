package net.danielgolan.elderion;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.danielgolan.elderion.blocks.InventoryBlock;
import net.danielgolan.elderion.config.ElderionConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.block.Blocks.*;

public class Elderion implements ModInitializer {
    public static final Block CHISELED_END_STONE;
    public static final Block CHISELED_END_STONE_BRICKS;
    public static final Block POLISHED_OBSIDIAN;
    public static final Block CHISELED_OBSIDIAN;
    public static final Block CHISELED_PURPUR_BRICKS;
    public static final Block CHISELED_PURPUR_PILLAR;
    public static final Block CHISELED_WAXED_OXIDIZED_COPPER;
    public static final Block CHISELED_WAXED_WEATHERED_COPPER;
    public static final Block DIMMED_GLOWSTONE;
    public static final Block ILLUMINATED_DARK_PRISMARINE;
    public static final Block POLISHED_PRISMARINE_BRICKS;
    public static final Block CUT_PRISMARINE;

    @Override
    public void onInitialize() {
        AutoConfig.register(ElderionConfig.class, GsonConfigSerializer::new);

        register("chiseled_end_stone", Author.canedpeanutshels, CHISELED_END_STONE);
        register("chiseled_end_stone_bricks", Author.canedpeanutshels, CHISELED_END_STONE_BRICKS);
        register("polished_obsidian", Author.canedpeanutshels, POLISHED_OBSIDIAN);
        register("chiseled_obsidian", Author.canedpeanutshels, CHISELED_OBSIDIAN);
        register("chiseled_purpur_bricks", Author.canedpeanutshels, CHISELED_PURPUR_BRICKS);
        register("chiseled_purpur_pillar", Author.canedpeanutshels, CHISELED_PURPUR_PILLAR);
        register("chiseled_waxed_oxidized_copper", Author.canedpeanutshels, CHISELED_WAXED_OXIDIZED_COPPER);
        register("chiseled_waxed_weathered_copper", Author.canedpeanutshels, CHISELED_WAXED_WEATHERED_COPPER);
        register("dimmed_glowstone", Author.canedpeanutshels, DIMMED_GLOWSTONE);
        register("illuminated_dark_prismarine", Author.canedpeanutshels, ILLUMINATED_DARK_PRISMARINE);
        register("polished_prismarine_bricks", Author.canedpeanutshels, POLISHED_PRISMARINE_BRICKS);
        register("cut_prismarine", Author.canedpeanutshels, CUT_PRISMARINE);
    }

    static {
        CHISELED_END_STONE = copy(END_STONE);
        CHISELED_END_STONE_BRICKS = copy(END_STONE_BRICKS);
        POLISHED_OBSIDIAN = copy(OBSIDIAN);
        CHISELED_OBSIDIAN = copy(OBSIDIAN);
        CHISELED_PURPUR_BRICKS = copy(OBSIDIAN);
        CHISELED_PURPUR_PILLAR = copy(OBSIDIAN);
        CHISELED_WAXED_OXIDIZED_COPPER = copy(WAXED_OXIDIZED_COPPER);
        CHISELED_WAXED_WEATHERED_COPPER = copy(WAXED_WEATHERED_COPPER);
        DIMMED_GLOWSTONE = new InventoryBlock(of(GLOWSTONE).luminance(state -> 13));
        ILLUMINATED_DARK_PRISMARINE = new InventoryBlock(of(DARK_PRISMARINE).luminance(state -> 15));
        POLISHED_PRISMARINE_BRICKS = copy(PRISMARINE_BRICKS);
        CUT_PRISMARINE = copy(PRISMARINE);
    }

    private static void register(String name, Author author, Block block){
        Registry.register(Registry.BLOCK, new Identifier("elderion", author.name() + '/' + name), block);
        if (block instanceof InventoryBlock b)
            Registry.register(Registry.ITEM, new Identifier("elderion", author.name() + '/' + name), b.item());
    }

    private static AbstractBlock.Settings of(AbstractBlock block) {
        return AbstractBlock.Settings.copy(block);
    }

    private static InventoryBlock copy(AbstractBlock block) {
        return new InventoryBlock(of(block));
    }
}
