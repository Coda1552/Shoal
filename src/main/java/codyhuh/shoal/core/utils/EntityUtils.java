package codyhuh.shoal.core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class EntityUtils {

    public static boolean canWaterAnimalSpawn(EntityType<? extends PathfinderMob> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos).is(Blocks.WATER) && worldIn.getBlockState(blockPos.above()).is(Blocks.WATER);
    }

    public static boolean canAnimalSpawn(EntityType<? extends PathfinderMob> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos blockPos, RandomSource randomIn) {
        return worldIn.getBlockState(blockPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && worldIn.getRawBrightness(blockPos, 0) > 8;
    }
}
