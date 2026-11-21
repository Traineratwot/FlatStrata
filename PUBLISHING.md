# Публикация Flat Strata 1.1.0

Все файлы для ручной публикации на Modrinth и CurseForge подготовлены. Автоматическая публикация и токены API не настраивались.

## Обязательно перед публикацией

GitHub-репозиторий `Traineratwot/FlatStrata` сейчас **приватный**. Изображения по ссылкам `raw.githubusercontent.com` не будут доступны на страницах модов, пока репозиторий не станет публичным.

Нужно выполнить одно из двух действий:

- сделать репозиторий публичным перед созданием страниц;
- или загрузить изображения на другой публичный хостинг и заменить URL в `docs/modrinth-description.md` и `docs/curseforge-description.html`.

## Общие данные

| Поле | Значение |
|---|---|
| Название | `Flat Strata` |
| Версия | `1.1.0` |
| Канал | `Release` |
| Minecraft | `1.21.1` |
| Загрузчик | `NeoForge` |
| Минимальный NeoForge | `21.1.267` |
| Java | `21` |
| Лицензия | `MIT` |
| Основной файл | `build/libs/flatstrata-1.1.0.jar` |
| Иконка | `src/main/resources/logo.png` |
| Changelog | `CHANGELOG.md` |
| Исходный код | `https://github.com/Traineratwot/FlatStrata` |
| Ошибки | `https://github.com/Traineratwot/FlatStrata/issues` |

Краткое описание для обеих площадок:

```text
A flat overworld with fixed layers, all biomes, caves, ores, fluids and structures, designed for modpack compatibility.
```

Changelog версии:

```text
Added strict structure validation for the Flat Strata preset. Surface structures must stay within one biome and above solid support. Underground structures cannot reach or cross the surface. Validation also applies to modded structure starts and /locate results. Strict filtering reduces the number of accepted structures near biome borders.
```

## Modrinth

### Настройки проекта

| Поле | Значение |
|---|---|
| Project type | `Mod` |
| Name | `Flat Strata` |
| Summary | Краткое описание выше |
| Description | Содержимое `docs/modrinth-description.md` |
| Categories | `worldgen`; дополнительно `utility` |
| License | `MIT` |
| Source code | `https://github.com/Traineratwot/FlatStrata` |
| Issue tracker | `https://github.com/Traineratwot/FlatStrata/issues` |
| Client side | `Required` |
| Server side | `Required` |
| Icon | `src/main/resources/logo.png` |

`Required` на обеих сторонах выбран потому, что пресет нужен при создании одиночного и серверного мира.

### Галерея

Загрузить файлы напрямую из репозитория:

| Файл | Подпись |
|---|---|
| `docs/images/biomes.png` | `Multiple biomes at one surface height` |
| `docs/images/surface.png` | `Flat terrain with vegetation and wildlife` |
| `docs/images/caves.png` | `Caves, ores, fluids, and underground structures below the flat surface` |

Первым изображением рекомендуется `biomes.png`.

### Загрузка версии

| Поле | Значение |
|---|---|
| Version number | `1.1.0` |
| Version title | `Flat Strata 1.1.0` |
| Release channel | `Release` |
| Game version | `1.21.1` |
| Loader | `NeoForge` |
| File | `build/libs/flatstrata-1.1.0.jar` |
| Featured file | Включить |
| Changelog | Текст выше |

## CurseForge

### Настройки проекта

| Поле | Значение |
|---|---|
| Project type | `Mod` |
| Name | `Flat Strata` |
| Summary | Краткое описание выше |
| Description | Содержимое `docs/curseforge-description.html` |
| Main category | `World Gen` |
| License | `MIT` |
| Source | `https://github.com/Traineratwot/FlatStrata` |
| Issues | `https://github.com/Traineratwot/FlatStrata/issues` |
| Logo | `src/main/resources/logo.png` |

### Загрузка файла

| Поле | Значение |
|---|---|
| File | `build/libs/flatstrata-1.1.0.jar` |
| Release type | `Release` |
| Minecraft version | `1.21.1` |
| Mod loader | `NeoForge` |
| Changelog | Текст выше |

Дополнительные зависимости на CurseForge не требуются. Требования NeoForge и Java уже указаны в описании и метаданных JAR.

## Проверка перед отправкой

- [ ] `./gradlew clean build` завершается успешно.
- [ ] Загружается именно `flatstrata-1.1.0.jar`, без суффиксов `sources` или `dev`.
- [ ] JAR содержит `logo.png`, `META-INF/neoforge.mods.toml` и `META-INF/accesstransformer.cfg`.
- [ ] Репозиторий публичный либо URL изображений заменены на публичные.
- [ ] На Modrinth добавлены все три изображения галереи с подписями.
- [ ] В предпросмотре описаний отображаются таблицы и скриншоты.
- [ ] На страницах указаны Minecraft 1.21.1 и NeoForge, без Fabric и других версий Minecraft.
- [ ] Новый мир создаётся с пресетом **Flat Strata / Плоские слои** на чистой установке и в целевой сборке.
