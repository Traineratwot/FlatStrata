package dev.flatstrata.mixin;

import dev.flatstrata.StructureGenerationValidator;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheck;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureCheck.class)
public abstract class StructureCheckMixin {
    @Shadow @Final private RegistryAccess registryAccess;
    @Shadow @Final private StructureTemplateManager structureTemplateManager;
    @Shadow @Final private ChunkGenerator chunkGenerator;
    @Shadow @Final private RandomState randomState;
    @Shadow @Final private LevelHeightAccessor heightAccessor;
    @Shadow @Final private BiomeSource biomeSource;
    @Shadow @Final private long seed;

    @Inject(method = "canCreateStructure", at = @At("HEAD"), cancellable = true)
    private void flatstrata$checkCompleteStructure(ChunkPos chunkPos, Structure structure, CallbackInfoReturnable<Boolean> cir) {
        if (!StructureGenerationValidator.appliesTo(this.chunkGenerator)) {
            return;
        }

        StructureStart start = structure.generate(
            this.registryAccess,
            this.chunkGenerator,
            this.biomeSource,
            this.randomState,
            this.structureTemplateManager,
            this.seed,
            chunkPos,
            0,
            this.heightAccessor,
            structure.biomes()::contains
        );
        cir.setReturnValue(start.isValid());
    }
}
