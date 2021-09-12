package net.danielgolan.elderion.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.service.mojang.Blackboard;

public class InventoryBlock extends Block {
    private final BlockItem item;

    public InventoryBlock(Settings settings) {
        this(settings, Rarity.COMMON);
    }

    public InventoryBlock(Settings settings, Rarity rarity) {
        this(settings, BlockType.building, rarity);
    }

    public InventoryBlock(Settings settings, BlockType type) {
        this(settings, type, Rarity.COMMON);
    }

    public InventoryBlock(Settings settings, BlockType type, Rarity rarity) {
        super(settings);
        item = new BlockItem(this, new Item.Settings().group(switch (type) {
            case building -> ItemGroup.BUILDING_BLOCKS;
            case decoration -> ItemGroup.DECORATIONS;
        }).rarity(rarity));
    }

    public BlockItem item() {
        return item;
    }
}
