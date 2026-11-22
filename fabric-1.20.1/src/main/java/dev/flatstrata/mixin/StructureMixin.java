package dev.flatstrata.mixin;

import dev.flatstrata.StructureGenerationValidator;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Structure.class)
public abstract class StructureMixin {
    @Inject(method = "generate", at = @At("RETURN"), cancellable = true)
    private void flatstrata$validateStructure(
        RegistryAccess registryAccess,
        ChunkGenerator chunkGenerator,
        BiomeSource biomeSource,
        RandomState randomState,
        StructureTemplateManager structureTemplateManager,
        long seed,
        ChunkPos chunkPos,
        int references,
        LevelHeightAccessor heightAccessor,
        Predicate<Holder<Biome>> validBiome,
        CallbackInfoReturnable<StructureStart> cir
    ) {
        Structure.GenerationContext context = new Structure.GenerationContext(
            registryAccess,
            chunkGenerator,
            biomeSource,
            randomState,
            structureTemplateManager,
            seed,
            chunkPos,
            heightAccessor,
            validBiome
        );
        if (!StructureGenerationValidator.isValid(cir.getReturnValue(), context)) {
            cir.setReturnValue(StructureStart.INVALID_START);
        }
    }
}
