package net.danielgolan.elderion;

import net.danielgolan.elderion.blocks.InventoryBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Elderion implements ModInitializer {
    public static final Block CHISELED_END_STONE;
    public static final Block CHISELED_END_STONE_BRICKS;
    public static final Block POLISHED_OBSIDIAN;
    public static final Block CHISELED_OBSIDIAN;
    public static final Block CHISELED_PURPUR_BRICKS;
    public static final Block CHISELED_PURPUR_PILLAR;

    @Override
    public void onInitialize() {
        register("chiseled_end_stone", Author.canedpeanutshels, CHISELED_END_STONE);
        register("chiseled_end_stone_bricks", Author.canedpeanutshels, CHISELED_END_STONE_BRICKS);
        register("polished_obsidian", Author.canedpeanutshels, POLISHED_OBSIDIAN);
        register("chiseled_obsidian", Author.canedpeanutshels, CHISELED_OBSIDIAN);
        register("chiseled_purpur_bricks", Author.canedpeanutshels, CHISELED_PURPUR_BRICKS);
        register("chiseled_purpur_pillar", Author.canedpeanutshels, CHISELED_PURPUR_PILLAR);
    }

    static {
        CHISELED_END_STONE = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW)
                .requiresTool().strength(3.0F, 9.0F));
        CHISELED_END_STONE_BRICKS = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW)
                .requiresTool().strength(3.0F, 9.0F));
        POLISHED_OBSIDIAN = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK)
                .requiresTool().strength(50.0F, 1200.0F));
        CHISELED_OBSIDIAN = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.BLACK)
                .requiresTool().strength(50.0F, 1200.0F));
        CHISELED_PURPUR_BRICKS = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.MAGENTA)
                .requiresTool().strength(1.5F, 6.0F));
        CHISELED_PURPUR_PILLAR = new InventoryBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.MAGENTA)
                .requiresTool().strength(1.5F, 6.0F));
    }

    private static void register(String name, Author author, Block block){
        Registry.register(Registry.BLOCK, new Identifier("elderion", author.name() + '/' + name), block);
        if (block instanceof InventoryBlock b)
            Registry.register(Registry.ITEM, new Identifier("elderion", author.name() + '/' + name), b.item());
    }
}
