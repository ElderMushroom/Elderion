package net.danielgolan.elderion.blocks;

import net.minecraft.block.*;

public interface BlockGenerator {
    BlockGenerator DEFAULT = (settings, original, variation) -> switch (variation) {
        case STAIRS -> generateStairs(original, settings);
        case BLOCK -> original;
        case WALL -> new WallBlock(settings);
        case SLAB -> new SlabBlock(settings);
        case FENCE -> new FenceBlock(settings);
        case FENCE_GATE -> new FenceGateBlock(settings);
    };

    Block generateVariation(AbstractBlock.Settings settings, Block original, BlockVariation variation);

    default Block generate(AbstractBlock.Settings settings) {
        return new Block(settings);
    }

    static StairsBlock generateStairs(Block original, AbstractBlock.Settings settings) {
        return new StairsGenerator(original.getDefaultState(), settings);
    }

    static StairsBlock generateStairs(BlockState state, AbstractBlock.Settings settings) {
        return new StairsGenerator(state, settings);
    }

    final class StairsGenerator extends StairsBlock {
        private StairsGenerator(BlockState state, Settings settings) {
            super(state, settings);
        }
    }
}
