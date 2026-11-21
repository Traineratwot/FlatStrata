# Changelog

## 1.2.1 — 2026-11-22

- Fixed dripstone and lush cave biomes appearing on the flat surface.
- Replaced the vanilla terrain-dependent biome depth with a depth curve designed for Flat Strata.
- Kept surface biomes at the surface, dripstone and lush caves underground, and deep dark below `Y=-48`.
- Preserved biome lookup, river generation, and strict structure validation.

## 1.2.0 — 2026-11-22

- Added noise-based clay deposits to riverbeds at `Y=58`.
- Added clay deposits to the lower riverbanks at `Y=59–60`.
- Preserved gravel patches, open water, and frozen river ice.
- Smoothed surface biome transitions by evaluating vanilla fuzzy biome selection at a fixed surface height instead of using blocky quart-cell boundaries.
- Kept all layers beneath the surface consistent with the selected surface biome.

## 1.1.0 — 2026-11-22

- Added strict validation for vanilla and modded structure starts in the Flat Strata preset.
- Surface structures must remain within one surface biome across their complete footprint.
- Surface structures are rejected when their support zone contains noise-generated cavities.
- Underground structures are rejected when any part reaches or crosses the surface.
- Updated structure checks used by `/locate` to avoid reporting rejected structures.
- Other world presets and dimensions are not affected.

Strict validation reduces the number of accepted structures, especially large structures and structures near biome borders.

## 1.0.1 — 2026-11-21

- Fixed dry river biomes.
- Added water at `Y=59–62` and gravel riverbeds at `Y=58`.
- Added ice at `Y=62` and water below it in frozen river biomes.
- Preserved fixed land, desert, ocean, and underground layers.

## 1.0.0 — 2026-11-21

Initial release for Minecraft 1.21.1 and NeoForge 21.1.267.

- Added the **Flat Strata** overworld preset.
- Added fixed land, soil, sand, sandstone, ocean, water, deepslate, and bedrock levels.
- Preserved vanilla biomes, caves, ores, ore veins, fluids, vegetation, and structures.
- Protected the flat surface crust from cave generation.
- Kept normal Nether and End generation.
- Added English and Russian world preset names.
- Added standard worldgen integration and a surface-biome rule for consistent biome layers.
