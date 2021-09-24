package net.danielgolan.elderion.blocks;

import net.danielgolan.elderion.Author;
import net.danielgolan.elderion.ElderionIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.function.ToIntFunction;

public final class VariedBlock {
    private final HashMap<BlockVariation, Block> blocks = new HashMap<>();
    private final HashMap<BlockVariation, BlockItem> items = new HashMap<>();

    private final ElderionIdentifier identifier;

    private VariedBlock(VariedBlock.Builder builder, ElderionIdentifier identifier, boolean register) {
        this.identifier = identifier;

        Item.Settings settings = new Item.Settings().rarity(builder.rarity()).group(builder.type().GROUP);

        blocks.put(BlockVariation.BLOCK, builder.generator().generate(builder));
        items.put(BlockVariation.BLOCK, new BlockItem(block(), settings));

        builder.variations.forEach((variation, bool) -> {
            if (variation == BlockVariation.BLOCK) return;

            blocks.put(variation, builder.generator().generateVariation(builder, block(), variation));
            items.put(variation, new BlockItem(block(variation), settings));
        });

        if (register) register();
    }

    public VariedBlock register() {
        for (BlockVariation variation : BlockVariation.values()) {
            if (block(variation) == null) continue;

            Identifier identifier = this.identifier.toIdentifier(variation.SUFFIX);

            Registry.register(Registry.BLOCK, identifier, block(variation));
            Registry.register(Registry.ITEM, identifier, item(variation));
        }

        return this;
    }

    public Block block() {
        return block(BlockVariation.BLOCK);
    }

    public Block block(BlockVariation variation) {
        return blocks.get(variation);
    }

    public BlockItem item() {
        return item(BlockVariation.BLOCK);
    }

    public BlockItem item(BlockVariation variation) {
        return items.get(variation);
    }

    public static Builder builder(Material material, MapColor color) {
        return new Builder(material, color);
    }

    public static Builder of(AbstractBlock.Settings settings) {
        return new Builder(settings);
    }

    public static Builder of(AbstractBlock block) {
        return of(AbstractBlock.Settings.copy(block));
    }

    public static final class Builder extends FabricBlockSettings {
        private final HashMap<BlockVariation, Boolean> variations = new HashMap<>();
        private BlockType type = BlockType.BUILDING;
        private Rarity rarity = Rarity.COMMON;
        private BlockGenerator generator = BlockGenerator.DEFAULT;

        protected Builder(Material material, MapColor color) {
            super(material, color);
        }

        protected Builder(AbstractBlock.Settings settings) {
            super(settings);
        }

        public Builder enable(BlockVariation... variations) {
            for (BlockVariation variation : variations)
                this.variations.put(variation, true);
            return this;
        }

        public Builder disable(BlockVariation variation) {
            variations.put(variation, true);
            return this;
        }

        public boolean isEnabled(BlockVariation variation) {
            return variations.get(variation);
        }

        public Builder type(BlockType type) {
            this.type = type;
            return this;
        }

        public Builder rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public BlockType type() {
            return type;
        }

        public Rarity rarity() {
            return rarity;
        }

        public VariedBlock build(ElderionIdentifier identifier) {
            return new VariedBlock(this, identifier, false);
        }

        public VariedBlock build(Author author, String path) {
            return build(new ElderionIdentifier(author, path));
        }

        public Builder generator(BlockGenerator generator) {
            this.generator = generator;
            return this;
        }

        public BlockGenerator generator() {
            return generator;
        }

        public Builder noCollision() {
            super.noCollision();
            return this;
        }

        public Builder nonOpaque() {
            super.nonOpaque();
            return this;
        }

        public Builder slipperiness(float value) {
            super.slipperiness(value);
            return this;
        }

        public Builder velocityMultiplier(float velocityMultiplier) {
            super.velocityMultiplier(velocityMultiplier);
            return this;
        }

        public Builder jumpVelocityMultiplier(float jumpVelocityMultiplier) {
            super.jumpVelocityMultiplier(jumpVelocityMultiplier);
            return this;
        }

        public Builder sounds(BlockSoundGroup group) {
            super.sounds(group);
            return this;
        }

        public Builder luminance(ToIntFunction<BlockState> luminanceFunction) {
            super.luminance(luminanceFunction);
            return this;
        }

        public Builder strength(float hardness, float resistance) {
            super.strength(hardness, resistance);
            return this;
        }

        public Builder breakInstantly() {
            super.breakInstantly();
            return this;
        }

        public Builder strength(float strength) {
            super.strength(strength);
            return this;
        }

        public Builder ticksRandomly() {
            super.ticksRandomly();
            return this;
        }

        public Builder dynamicBounds() {
            super.dynamicBounds();
            return this;
        }

        public Builder air() {
            super.air();
            return this;
        }

        public Builder allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
            super.allowsSpawning(predicate);
            return this;
        }

        public Builder solidBlock(AbstractBlock.ContextPredicate predicate) {
            super.solidBlock(predicate);
            return this;
        }

        public Builder suffocates(AbstractBlock.ContextPredicate predicate) {
            super.suffocates(predicate);
            return this;
        }

        public Builder blockVision(AbstractBlock.ContextPredicate predicate) {
            super.blockVision(predicate);
            return this;
        }

        public Builder postProcess(AbstractBlock.ContextPredicate predicate) {
            super.postProcess(predicate);
            return this;
        }

        public Builder emissiveLighting(AbstractBlock.ContextPredicate predicate) {
            super.emissiveLighting(predicate);
            return this;
        }

        public Builder luminance(int luminance) {
            super.luminance(luminance);
            return this;
        }

        public Builder hardness(float hardness) {
            super.hardness(hardness);
            return this;
        }

        public Builder resistance(float resistance) {
            super.resistance(resistance);
            return this;
        }

        public Builder requiresTool() {
            super.requiresTool();
            return this;
        }

        public Builder mapColor(MapColor color) {
            super.mapColor(color);
            return this;
        }

        public Builder mapColor(DyeColor color) {
            return this.mapColor(color.getMapColor());
        }

        public Builder collidable(boolean collidable) {
            super.collidable(collidable);
            return this;
        }

        public Builder breakByHand(boolean breakByHand) {
            super.breakByHand(breakByHand);
            return this;
        }

        public Builder breakByTool(Tag<Item> tag, int miningLevel) {
            super.breakByTool(tag, miningLevel);
            return this;
        }

        public Builder breakByTool(Tag<Item> tag) {
            return this.breakByTool(tag, 0);
        }
    }
}
