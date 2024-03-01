# Minecraft: Pi Edition Parser

A small libary for parsing and creating Minecraft: Pi Edition world files.

## How To Use

* Download `MCPI-Parser-v<version>.jar` from [Releases](https://github.com/BJTMastermind/MCPI-Parser/issues) tab.
* Add the library into your project.
* Import `me.bjtmastermind.mcpi_parser.<ChunksDatParser|EntitiesDatParser>` to use.

Example Code:

**Parsing chunks.dat**
```java
// Create a new instance of the ChunksDatParser
ChunksDatParser chunkParser = new ChunksDatParser();

// Use the parse method to parse the chunks.dat file. Returns a HashMap of PiChunks
HashMap<String, PiChunk> chunks = chunkParser.parse("/path/to/chunks.dat");
```

**Assembling chunks.dat**
```java
// Create a new instance of the ChunksDatParser
ChunksDatParser chunkParser = new ChunksDatParser();

// Use the assemble method to create a chunks.dat file.
HashMap<String, PiChunk> chunks = Example.GenerateChunks();
chunkParser.assemble("/path/to/chunks.dat", chunks);
```

**Parsing entities.dat**
```java
// Create a new instance of the EntitiesDatParser
EntitiesDatParser entitiesParser = new EntitiesDatParser();

// Use the parse method to parse the entities.dat file.
entitiesParser.parse("/path/to/chunks.dat");
```

## Minimum Java Version

* Java 8