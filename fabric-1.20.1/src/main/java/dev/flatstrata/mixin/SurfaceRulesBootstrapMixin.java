package dev.flatstrata.mixin;

import com.mojang.serialization.Codec;
import dev.flatstrata.FlatStrataMod;
import dev.flatstrata.SurfaceBiomeConditionSource;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SurfaceRules.ConditionSource.class)
public interface SurfaceRulesBootstrapMixin {
    @Inject(method = "bootstrap", at = @At("RETURN"))
    private static void flatstrata$registerSurfaceBiome(
        Registry<Codec<? extends SurfaceRules.ConditionSource>> registry,
        CallbackInfoReturnable<Codec<? extends SurfaceRules.ConditionSource>> cir
    ) {
        Registry.register(
            registry,
            new ResourceLocation(FlatStrataMod.MOD_ID, "surface_biome"),
            SurfaceBiomeConditionSource.CODEC.codec()
        );
    }
}
