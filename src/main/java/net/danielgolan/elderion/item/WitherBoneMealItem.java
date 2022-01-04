package net.danielgolan.elderion.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WitherBoneMealItem extends Item {
    public WitherBoneMealItem(Settings settings) {
        super(settings);
    }

    /*
    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        World world;
        world = context.getWorld();

        Random random = world.getRandom();
        BlockPos pos = context.getBlockPos();
        BlockState target = world.getBlockState(pos);

        if (target.isIn(BlockTags.NYLIUM)) {

            int size = random.nextInt(10) + 5;
            List<BlockPos> nettheracks = BlockPos.stream(new Box(pos).expand(7)).filter((blockPos) ->
                            world.getBlockState(blockPos).isOf(Blocks.NETHERRACK) && world.getBlockState(blockPos.add(0, 1, 0)).isAir())
                    .sorted(Comparator.comparingInt(o -> o.getManhattanDistance(pos))).toList();

            if (size > nettheracks.size()) size = nettheracks.size();

            for (int i = 0; i < size; i++) {
                world.setBlockState(nettheracks.get(i), target);
                System.out.printf("Setting block at %s to %s%n", nettheracks.get(i), target);
            }

            return ActionResult.CONSUME;
        } else return ActionResult.PASS;
    }
     */
}
