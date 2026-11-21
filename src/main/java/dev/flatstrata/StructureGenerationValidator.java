package dev.flatstrata;

import java.util.Objects;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;

public final class StructureGenerationValidator {
    private static final ResourceKey<NoiseGeneratorSettings> FLAT_STRATA = ResourceKey.create(
        net.minecraft.core.registries.Registries.NOISE_SETTINGS,
        ResourceLocation.fromNamespaceAndPath(FlatStrataMod.MOD_ID, "flat_strata")
    );
    private static final int SURFACE_QUART_Y = QuartPos.fromBlock(63);
    private static final int SUPPORT_DEPTH = 13;

    private StructureGenerationValidator() {
    }

    public static boolean appliesTo(ChunkGenerator generator) {
        return generator instanceof NoiseBasedChunkGenerator noiseGenerator && noiseGenerator.stable(FLAT_STRATA);
    }

    public static boolean isValid(StructureStart start, Structure.GenerationContext context) {
        if (!start.isValid() || !appliesTo(context.chunkGenerator())) {
            return true;
        }

        BoundingBox box = BoundingBox.encapsulatingBoxes(
            start.getPieces().stream().map(StructurePiece::getBoundingBox)::iterator
        ).orElse(null);
        if (box == null) {
            return false;
        }

        Holder<Biome> originBiome = getBiome(
            context,
            QuartPos.fromBlock(start.getChunkPos().getMiddleBlockX()),
            QuartPos.fromBlock(start.getChunkPos().getMiddleBlockZ())
        );

        if (isUnderground(start.getStructure(), box, surfaceHeight(originBiome))) {
            return isBelowSurface(box, context);
        }

        return hasSingleBiome(box, originBiome, context) && hasSolidSupport(box, surfaceHeight(originBiome), context);
    }

    private static boolean isUnderground(Structure structure, BoundingBox box, int surfaceY) {
        GenerationStep.Decoration step = structure.step();
        return step == GenerationStep.Decoration.UNDERGROUND_STRUCTURES
            || step == GenerationStep.Decoration.STRONGHOLDS
            || box.maxY() < surfaceY;
    }

    private static boolean hasSingleBiome(BoundingBox box, Holder<Biome> originBiome, Structure.GenerationContext context) {
        for (int x = QuartPos.fromBlock(box.minX()); x <= QuartPos.fromBlock(box.maxX()); x++) {
            for (int z = QuartPos.fromBlock(box.minZ()); z <= QuartPos.fromBlock(box.maxZ()); z++) {
                if (!Objects.equals(originBiome, getBiome(context, x, z))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isBelowSurface(BoundingBox box, Structure.GenerationContext context) {
        for (int x = QuartPos.fromBlock(box.minX()); x <= QuartPos.fromBlock(box.maxX()); x++) {
            for (int z = QuartPos.fromBlock(box.minZ()); z <= QuartPos.fromBlock(box.maxZ()); z++) {
                if (box.maxY() >= surfaceHeight(getBiome(context, x, z))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasSolidSupport(BoundingBox box, int surfaceY, Structure.GenerationContext context) {
        DensityFunction density = context.randomState().router().finalDensity();
        int minY = Math.max(context.heightAccessor().getMinBuildHeight(), surfaceY - SUPPORT_DEPTH);

        for (int x = QuartPos.fromBlock(box.minX()); x <= QuartPos.fromBlock(box.maxX()); x++) {
            for (int z = QuartPos.fromBlock(box.minZ()); z <= QuartPos.fromBlock(box.maxZ()); z++) {
                int blockX = QuartPos.toBlock(x);
                int blockZ = QuartPos.toBlock(z);
                for (int y = minY; y <= surfaceY; y++) {
                    if (density.compute(new DensityFunction.SinglePointContext(blockX, y, blockZ)) <= 0.0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static Holder<Biome> getBiome(Structure.GenerationContext context, int x, int z) {
        return context.biomeSource().getNoiseBiome(x, SURFACE_QUART_Y, z, context.randomState().sampler());
    }

    private static int surfaceHeight(Holder<Biome> biome) {
        if (biome.is(BiomeTags.IS_RIVER)) {
            return 58;
        }
        if (biome.is(BiomeTags.IS_DEEP_OCEAN)) {
            return 54;
        }
        if (biome.is(BiomeTags.IS_OCEAN)) {
            return 55;
        }
        return 63;
    }
}
