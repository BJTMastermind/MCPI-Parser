# Minecraft: Pi Edition Parser

A small libary for parsing and creating Minecraft: Pi Edition world files.<br>

 *\*This library could also be used for old Minecraft: Pocket Edition 0.2.0 - 0.8.2 world files.\**

## How To Use

* Download `MCPI-Parser-v<version>.jar` from [Releases](https://github.com/BJTMastermind/MCPI-Parser/releases) tab.
* Add the library into your project.
* Import `me.bjtmastermind.mcpi_parser.<ChunksDatParser|EntitiesDatParser>` to use.

Example Code:

**Parsing chunks.dat**
```java
// Create a new instance of the ChunksDatParser
ChunksDatParser chunkParser = new ChunksDatParser();

// Use the parse method to parse the chunks.dat file. Returns a HashMap of Chunks
HashMap<String, Chunk> chunks = chunkParser.parse("/path/to/chunks.dat");
```

**Assembling chunks.dat**
```java
// Create a new instance of the ChunksDatParser
ChunksDatParser chunkParser = new ChunksDatParser();

// Use the assemble method to create a chunks.dat file.
HashMap<String, Chunk> chunks = Example.GenerateChunks();
chunkParser.assemble("/path/to/output/chunks.dat", chunks);
```

**Parsing entities.dat**
```java
// Create a new instance of the EntitiesDatParser
EntitiesDatParser entitiesParser = new EntitiesDatParser();

// Use the parse method to parse the entities.dat file.
entitiesParser.parse("/path/to/chunks.dat");
```

**Assembling entities.dat**
```java
// Create a new instance of the EntitiesDatParser
EntitiesDatParser entitiesParser = new EntitiesDatParser();

// Add a entity to the world.
ArrayList<Entity> entities = entitiesParser.getEntities();
Sheep sheep = new Sheep(64f, 72f, 64f, DyeColor.RED);
entities.add(sheep);

// Add/Modify a tile entity to the world. (You don't add to the list if it exist already)
ArrayList<TileEntity> tileEntities = entitiesParser.getTileEntities();
Sign sign = (Sign) LocateHelper.locateTileEntity(tileEntities, TileEntityType.SIGN, 64, 72, 64);
sign.setText2("Hello World!");

// Use the assemble method to create a entities.dat file.
entitiesParser.assemble("/path/to/output/entities.dat", entities, tileEntities);
```

## Minimum Java Version

* Java 17