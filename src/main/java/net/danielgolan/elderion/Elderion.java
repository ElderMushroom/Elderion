package net.danielgolan.elderion;

import net.danielgolan.elderion.library.blocks.VariedBlock;
import net.fabricmc.api.ModInitializer;

import static net.danielgolan.elderion.ElderionAuthor.canedpeanutshels;
import static net.danielgolan.elderion.ElderionAuthor.crystalline_robin;
import static net.minecraft.block.Blocks.*;

public class Elderion implements ModInitializer {
    public static final String MOD_ID = "elderion";

    //region Peanut
    public static final VariedBlock CHISELED_END_STONE;
    public static final VariedBlock CHISELED_END_STONE_BRICKS;
    public static final VariedBlock CHISELED_OBSIDIAN;
    public static final VariedBlock CHISELED_PURPUR_BRICKS;
    public static final VariedBlock CHISELED_PURPUR_PILLAR;
    public static final VariedBlock CHISELED_WAXED_OXIDIZED_COPPER;
    public static final VariedBlock CHISELED_WAXED_WEATHERED_COPPER;
    public static final VariedBlock CORRUPTED_COPPER;
    public static final VariedBlock CUT_PRISMARINE;
    public static final VariedBlock CUT_PRISMARINE_BRICKS;
    public static final VariedBlock DIMMED_GLOWSTONE;
    public static final VariedBlock ILLUMINATED_DARK_PRISMARINE;
    public static final VariedBlock POLISHED_OBSIDIAN;
    public static final VariedBlock POLISHED_PRISMARINE;
    public static final VariedBlock POLISHED_PRISMARINE_BRICKS;
    public static final VariedBlock SMOOTH_AMETHYST;
    //endregion
    //region Robin
    public static final VariedBlock CUT_EMERALD;
    public static final VariedBlock CUT_AMETHYST;
    public static final VariedBlock CUT_IRON;
    public static final VariedBlock CUT_NETHERITE;
    public static final VariedBlock CUT_GOLD;
    public static final VariedBlock CUT_DIAMOND;
    public static final VariedBlock CUT_LAPIS_LAZULI;
    public static final VariedBlock CUT_REDSTONE;
    /*
    public static final VariedBlock STRING_BLOCK;
    public static final VariedBlock SPIDER_EGG;
     */
    //endregion

    /*
    private static final ConfiguredFeature<?, ?> UMBRASAND_ORE;
    public static final VariedBlock UMBRASAND;
    public static final VariedBlock UMBRASAND_PILLAR;
     */

    public void onInitialize() {
        //((BlockAccessor) STONE).setDefaultBlockState(STONE.getStateManager().getDefaultState().with(Properties.SNOWY, false));
    }

    static {
        CHISELED_END_STONE = VariedBlock.of(END_STONE)
                .build(canedpeanutshels, "chiseled_end_stone").register();
        CHISELED_END_STONE_BRICKS = VariedBlock.of(END_STONE_BRICKS)
                .build(canedpeanutshels, "chiseled_end_stone_bricks").register();
        POLISHED_OBSIDIAN = VariedBlock.of(OBSIDIAN)
                .build(canedpeanutshels, "polished_obsidian").register();
        CHISELED_OBSIDIAN = VariedBlock.of(OBSIDIAN)
                .build(canedpeanutshels, "chiseled_obsidian").register();
        CHISELED_PURPUR_BRICKS = VariedBlock.of(PURPUR_BLOCK)
                .build(canedpeanutshels, "chiseled_purpur_bricks").register();
        CHISELED_PURPUR_PILLAR = VariedBlock.of(PURPUR_BLOCK)
                .build(canedpeanutshels, "chiseled_purpur_pillar").register();
        CHISELED_WAXED_OXIDIZED_COPPER = VariedBlock.of(WAXED_OXIDIZED_COPPER)
                .build(canedpeanutshels, "chiseled_waxed_oxidized_copper").register();
        CHISELED_WAXED_WEATHERED_COPPER = VariedBlock.of(WAXED_WEATHERED_COPPER)
                .build(canedpeanutshels, "chiseled_waxed_weathered_copper").register();
        DIMMED_GLOWSTONE = VariedBlock.of(GLOWSTONE)
                .luminance(13)
                .build(canedpeanutshels, "dimmed_glowstone").register();
        ILLUMINATED_DARK_PRISMARINE = VariedBlock.of(DARK_PRISMARINE)
                .luminance(15)
                .build(canedpeanutshels, "illuminated_dark_prismarine").register();
        POLISHED_PRISMARINE_BRICKS = VariedBlock.of(PRISMARINE_BRICKS)
                .build(canedpeanutshels, "polished_prismarine_bricks").register();
        CUT_PRISMARINE = VariedBlock.of(PRISMARINE)
                .build(canedpeanutshels, "cut_prismarine").register();
        CORRUPTED_COPPER = VariedBlock.of(WAXED_COPPER_BLOCK)
                .build(canedpeanutshels, "corrupted_copper").register();
        CUT_PRISMARINE_BRICKS = VariedBlock.of(PRISMARINE_BRICKS)
                .build(canedpeanutshels, "cut_prismarine_bricks").register();
        POLISHED_PRISMARINE = VariedBlock.of(PRISMARINE)
                .build(canedpeanutshels, "polished_prismarine").register();
        CUT_EMERALD = VariedBlock.of(EMERALD_BLOCK)
                .build(crystalline_robin, "cut_emerald").register();
        CUT_AMETHYST = VariedBlock.of(AMETHYST_BLOCK)
                .build(crystalline_robin, "cut_amethyst").register();
        CUT_IRON = VariedBlock.of(IRON_BLOCK)
                .build(crystalline_robin, "cut_iron").register();
        CUT_NETHERITE = VariedBlock.of(NETHERITE_BLOCK)
                .build(crystalline_robin, "cut_netherite").register();
        CUT_GOLD = VariedBlock.of(GOLD_BLOCK)
                .build(crystalline_robin, "cut_gold").register();
        CUT_DIAMOND = VariedBlock.of(DIAMOND_BLOCK)
                .build(crystalline_robin, "cut_diamond").register();
        CUT_LAPIS_LAZULI = VariedBlock.of(LAPIS_BLOCK)
                .build(crystalline_robin, "cut_lapis_lazuli").register();
        CUT_REDSTONE = VariedBlock.of(REDSTONE_BLOCK)
                .build(crystalline_robin, "cut_redstone").register();
        SMOOTH_AMETHYST = VariedBlock.of(AMETHYST_BLOCK)
                .build(canedpeanutshels, "smooth_amethyst").register();
        /*
        STRING_BLOCK = VariedBlock.of(WHITE_WOOL)
                .build(crystalline_robin, "string_block").register();
        SPIDER_EGG = VariedBlock.of(WHITE_WOOL)
                .boundingBox(Block.createCuboidShape(4, 0, 4, 12, 8, 12))
                .build(crystalline_robin, "spider_egg").register();
         */

        /*
        UMBRASAND = VariedBlock.of(END_STONE)
                .sounds(BlockSoundGroup.GRAVEL)
                .breakByTool(FabricToolTags.SHOVELS, 2)
                .build(crystalline_robin, "umbrasand");
        UMBRASAND_PILLAR = VariedBlock.of(END_STONE)
                .breakByTool(FabricToolTags.PICKAXES, 2)
                .build(crystalline_robin, "umbrasand_pillar");

        UMBRASAND_ORE = Feature.SCATTERED_ORE
                .configure(new OreFeatureConfig(new BlockMatchRuleTest(END_STONE),
                        UMBRASAND.block().getDefaultState(), 30))
                .range(new RangeDecoratorConfig(new UniformHeightProvider()));
         */
    }
}
