package net.danielgolan.elderion.blocks;

public enum BlockVariation {
    BLOCK(""),
    FENCE("fence"),
    FENCE_GATE("fence_gate"),
    SLAB("slab"),
    STAIRS("stairs"),
    WALL("wall");

    public final int ID = ordinal();
    public final String SUFFIX;

    BlockVariation(String suffix) {
        SUFFIX = '_' + suffix;
    }

}
