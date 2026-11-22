package dev.flatstrata;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(FlatStrataMod.MOD_ID)
public final class FlatStrataMod {
    public static final String MOD_ID = "flatstrata";

    private static final DeferredRegister<Codec<? extends SurfaceRules.ConditionSource>> SURFACE_CONDITIONS = DeferredRegister.create(
        Registries.MATERIAL_CONDITION,
        MOD_ID
    );

    static {
        SURFACE_CONDITIONS.register("surface_biome", () -> SurfaceBiomeConditionSource.CODEC.codec());
    }

    public FlatStrataMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SURFACE_CONDITIONS.register(modEventBus);
    }
}
