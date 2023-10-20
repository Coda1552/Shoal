package codyhuh.shoal.common.levelgen;

import codyhuh.shoal.core.registry.ShoalStructureModifiers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;

/**
 * EX:
 * {
 *   "type": "shoal:add_spawn",
 *   "category": "creature",
 *   "spawn": {
 *     "type": "minecraft:dolphin",
 *     "minCount": 1,
 *     "maxCount": 2,
 *     "weight": 10
 *   },
 *   "structures": "minecraft:shipwreck"
 * }
 */
public record AddSpawnsStructureModifier(HolderSet<Structure> structures, MobCategory category, MobSpawnSettings.SpawnerData spawn) implements StructureModifier {
    public static final Codec<AddSpawnsStructureModifier> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            RegistryCodecs.homogeneousList(Registries.STRUCTURE, Structure.DIRECT_CODEC).fieldOf("structures").forGetter(AddSpawnsStructureModifier::structures),
            MobCategory.CODEC.fieldOf("category").forGetter(AddSpawnsStructureModifier::category),
            MobSpawnSettings.SpawnerData.CODEC.fieldOf("spawn").forGetter(AddSpawnsStructureModifier::spawn)
    ).apply(builder, AddSpawnsStructureModifier::new));

    @Override
    public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (phase == Phase.ADD && this.structures.contains(structure)) {
            builder.getStructureSettings().getOrAddSpawnOverrides(category).addSpawn(spawn);
        }
    }

    @Override
    public Codec<? extends StructureModifier> codec() {
        return ShoalStructureModifiers.ADD_SPAWNS_MODIFIER.get();
    }
}