package dev.flatstrata;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(FlatStrataMod.MOD_ID)
public final class FlatStrataMod {
    public static final String MOD_ID = "flatstrata";

    private static final DeferredRegister<MapCodec<? extends SurfaceRules.ConditionSource>> SURFACE_CONDITIONS = DeferredRegister.create(
        Registries.MATERIAL_CONDITION,
        MOD_ID
    );

    static {
        SURFACE_CONDITIONS.register("surface_biome", () -> SurfaceBiomeConditionSource.CODEC.codec());
    }

    public FlatStrataMod(IEventBus modEventBus) {
        SURFACE_CONDITIONS.register(modEventBus);
    }
}
