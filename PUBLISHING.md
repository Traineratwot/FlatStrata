# Публикация Flat Strata 1.3.0

Подготовлены отдельные файлы для NeoForge, Forge и Fabric. Автоматическая публикация и токены API не настраивались.

## Перед публикацией

GitHub-репозиторий `Traineratwot/FlatStrata` сейчас **приватный**. Для отображения изображений по ссылкам `raw.githubusercontent.com` его нужно сделать публичным либо перенести изображения на публичный хостинг и обновить URL в описаниях.

## Общие данные

| Поле | Значение |
|---|---|
| Название | `Flat Strata` |
| Версия | `1.3.0` |
| Канал | `Release` |
| Лицензия | `MIT` |
| Иконка | `src/main/resources/logo.png` |
| Changelog | `CHANGELOG.md` |
| Исходный код | `https://github.com/Traineratwot/FlatStrata` |
| Ошибки | `https://github.com/Traineratwot/FlatStrata/issues` |

| Загрузчик | Minecraft | Java | Файл |
|---|---|---:|---|
| NeoForge 21.1.267+ | 1.21.1 | 21 | `build/libs/flatstrata-1.3.0.jar` |
| Forge 47.4.20+ | 1.20.1 | 17 | `forge-1.20.1/build/libs/flatstrata-1.20.1-forge-1.3.0.jar` |
| Fabric Loader 0.16.10+ | 1.20.1 | 17 | `fabric-1.20.1/build/libs/flatstrata-1.20.1-fabric-1.3.0.jar` |

Fabric-версия требует Fabric API `0.92.12+1.20.1` или совместимую более новую версию. Файлы разных загрузчиков несовместимы между собой.

Краткое описание:

```text
A flat overworld with fixed layers, all biomes, caves, ores, fluids and structures, designed for modpack compatibility.
```

Changelog версии:

```text
Added separate Forge 1.20.1 and Fabric 1.20.1 builds alongside NeoForge 1.21.1. Fixed layers, river clay, smoothed biome borders, underground cave biomes, and strict structure validation are available on all three loaders. Added independent CI builds and loader-specific release files.
```

## Modrinth

### Настройки проекта

| Поле | Значение |
|---|---|
| Project type | `Mod` |
| Name | `Flat Strata` |
| Summary | Краткое описание выше |
| Description | `docs/modrinth-description.md` |
| Categories | `worldgen`; дополнительно `utility` |
| License | `MIT` |
| Source code | `https://github.com/Traineratwot/FlatStrata` |
| Issue tracker | `https://github.com/Traineratwot/FlatStrata/issues` |
| Client side | `Required` |
| Server side | `Required` |
| Icon | `src/main/resources/logo.png` |

### Галерея

| Файл | Подпись |
|---|---|
| `docs/images/biomes.png` | `Multiple biomes at one surface height` |
| `docs/images/surface.png` | `Flat terrain with vegetation and wildlife` |
| `docs/images/caves.png` | `Caves, ores, fluids, and underground structures below the flat surface` |

Первым изображением рекомендуется `biomes.png`.

### Загрузка версии

| Поле | Значение |
|---|---|
| Version number | `1.3.0` |
| Version title | `Flat Strata 1.3.0` |
| Release channel | `Release` |
| Game versions | `1.20.1`, `1.21.1` |
| Loaders | `Forge`, `Fabric`, `NeoForge` |
| Files | Все три JAR из таблицы выше |
| Changelog | Текст выше |

На Modrinth все три файла можно приложить к одной версии с общим набором загрузчиков и версий Minecraft. Основным файлом рекомендуется выбрать NeoForge 1.21.1.

## CurseForge

### Настройки проекта

| Поле | Значение |
|---|---|
| Project type | `Mod` |
| Name | `Flat Strata` |
| Summary | Краткое описание выше |
| Description | `docs/curseforge-description.html` |
| Main category | `World Gen` |
| License | `MIT` |
| Source | `https://github.com/Traineratwot/FlatStrata` |
| Issues | `https://github.com/Traineratwot/FlatStrata/issues` |
| Logo | `src/main/resources/logo.png` |

Загрузить три отдельных файла `Release`:

1. NeoForge JAR — Minecraft `1.21.1`, загрузчик `NeoForge`.
2. Forge JAR — Minecraft `1.20.1`, загрузчик `Forge`.
3. Fabric JAR — Minecraft `1.20.1`, загрузчик `Fabric`.

Для Fabric-файла добавить зависимость от Fabric API, если интерфейс CurseForge предлагает задать relations. Для остальных файлов дополнительных модовых зависимостей нет.

## Проверка перед отправкой

- [ ] `./gradlew clean build` успешно выполнен для NeoForge.
- [ ] `./gradlew clean build` успешно выполнен в `forge-1.20.1`.
- [ ] `./gradlew clean build` успешно выполнен в `fabric-1.20.1`.
- [ ] Загружаются основные JAR без суффиксов `sources` или `dev`.
- [ ] NeoForge JAR содержит `META-INF/neoforge.mods.toml`.
- [ ] Forge JAR содержит `META-INF/mods.toml`.
- [ ] Fabric JAR содержит `fabric.mod.json`, Access Widener и зависимость Fabric API.
- [ ] Все JAR содержат иконку, лицензию, mixin-конфигурацию и worldgen-ресурсы.
- [ ] Репозиторий публичный либо URL изображений заменены на публичные.
- [ ] На страницах выбраны все три загрузчика и обе поддерживаемые версии Minecraft.
- [ ] Каждый вариант проверен на headless server с созданием мира **Flat Strata / Плоские слои**.
