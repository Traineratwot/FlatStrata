package dev.flatstrata;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;

public record SurfaceBiomeConditionSource(List<ResourceKey<Biome>> biomes) implements SurfaceRules.ConditionSource {
    public static final KeyDispatchDataCodec<SurfaceBiomeConditionSource> CODEC = KeyDispatchDataCodec.of(
        RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.BIOME).listOf().fieldOf("biome_is").forGetter(SurfaceBiomeConditionSource::biomes)
        ).apply(instance, SurfaceBiomeConditionSource::new))
    );

    private static final int SURFACE_Y = 63;

    @Override
    public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
        return CODEC;
    }

    @Override
    public SurfaceRules.Condition apply(SurfaceRules.Context context) {
        Set<ResourceKey<Biome>> allowedBiomes = Set.copyOf(this.biomes);
        return () -> {
            Holder<Biome> biome = context.biomeGetter.apply(new BlockPos(context.blockX, SURFACE_Y, context.blockZ));
            return biome.is(allowedBiomes::contains);
        };
    }
}
