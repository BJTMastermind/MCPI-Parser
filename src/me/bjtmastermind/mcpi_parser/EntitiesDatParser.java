package me.bjtmastermind.mcpi_parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import me.bjtmastermind.mcpi_parser.entities.PiArrow;
import me.bjtmastermind.mcpi_parser.entities.PiChicken;
import me.bjtmastermind.mcpi_parser.entities.PiCow;
import me.bjtmastermind.mcpi_parser.entities.PiCreeper;
import me.bjtmastermind.mcpi_parser.entities.PiEntity;
import me.bjtmastermind.mcpi_parser.entities.PiFallingTile;
import me.bjtmastermind.mcpi_parser.entities.PiItemEntity;
import me.bjtmastermind.mcpi_parser.entities.PiPainting;
import me.bjtmastermind.mcpi_parser.entities.PiPig;
import me.bjtmastermind.mcpi_parser.entities.PiPigZombie;
import me.bjtmastermind.mcpi_parser.entities.PiPrimedTnt;
import me.bjtmastermind.mcpi_parser.entities.PiSheep;
import me.bjtmastermind.mcpi_parser.entities.PiSkeleton;
import me.bjtmastermind.mcpi_parser.entities.PiSnowball;
import me.bjtmastermind.mcpi_parser.entities.PiSpider;
import me.bjtmastermind.mcpi_parser.entities.PiThrownEgg;
import me.bjtmastermind.mcpi_parser.entities.PiZombie;
import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.mcpi_parser.tile_entities.PiChest;
import me.bjtmastermind.mcpi_parser.tile_entities.PiFurnace;
import me.bjtmastermind.mcpi_parser.tile_entities.PiNetherReactorCore;
import me.bjtmastermind.mcpi_parser.tile_entities.PiSign;
import me.bjtmastermind.mcpi_parser.tile_entities.PiTileEntity;
import me.bjtmastermind.mcpi_parser.utils.NumberToLEArray;
import me.bjtmastermind.nbt.io.NBTUtil;
import me.bjtmastermind.nbt.io.NamedTag;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class EntitiesDatParser {
    private int magic;
    private int version;
    private int byteCount;

    private ArrayList<PiEntity> entities;
    private ArrayList<PiTileEntity> tileEntities;

    public void parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        this.magic = buffer.getInt();
        if (this.magic != 5525061) {
            System.err.println("Failed to parse file, Unsupported magic found. Expected 5525061 found "+this.magic);
            return;
        }

        this.version = buffer.getInt();
        if (this.version != 1) {
            System.err.println("Failed to parse file, Unsupported version found. Expected 1 found "+this.version);
            return;
        }

        this.byteCount = buffer.getInt();

        byte[] nbtBytes = new byte[this.byteCount];
        buffer.get(nbtBytes, 0, this.byteCount);

        NamedTag nbt = NBTUtil.readLE(new ByteArrayInputStream(nbtBytes), false);
        CompoundTag root = (CompoundTag) nbt.getTag();

        ListTag<CompoundTag> nbtEntities = root.getListTag("Entities").asCompoundTagList();
        this.entities = new ArrayList<>();
        for (CompoundTag nbtEntity : nbtEntities) {
            entities.add(this.toEntityClass(nbtEntity));
        }

        ListTag<CompoundTag> nbtTileEntities = root.getListTag("TileEntities").asCompoundTagList();
        this.tileEntities = new ArrayList<>();
        for (CompoundTag nbtTileEntity : nbtTileEntities) {
            tileEntities.add(this.toTileEntityClass(nbtTileEntity));
        }
    }

    public ArrayList<PiEntity> getEntities() {
        return this.entities;
    }

    public ArrayList<PiTileEntity> getTileEntities() {
        return this.tileEntities;
    }

    public void assemble(String filepath, ArrayList<PiEntity> entities, ArrayList<PiTileEntity> tileEntities) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream rawOutput = new DataOutputStream(baos);

        ListTag<CompoundTag> nbtEntities = new ListTag<>();
        for (PiEntity entity : entities) {
            nbtEntities.add(this.fromEntityClass(entity));
        }

        ListTag<CompoundTag> nbtTileEntities = new ListTag<>();
        for (PiTileEntity tileEntity : tileEntities) {
            nbtTileEntities.add(this.fromTileEntityClass(tileEntity));
        }

        try {
            CompoundTag data = new CompoundTag();
            data.put("Entities", nbtEntities);
            data.put("TileEntities", nbtTileEntities);

            byte[] nbtBytes = NBTUtil.writeLE(data, false).toByteArray();

            rawOutput.write(NumberToLEArray.toLEInt(5525061));
            rawOutput.write(NumberToLEArray.toLEInt(1));
            rawOutput.write(NumberToLEArray.toLEInt(nbtBytes.length));
            rawOutput.write(nbtBytes);

            File output = new File(filepath);
            Files.write(output.toPath(), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CompoundTag fromEntityClass(PiEntity entity) {
        return switch (EntityType.fromID(entity.getID())) {
            case CHICKEN -> ((PiChicken) (entity)).toCompoundTag();
            case COW -> ((PiCow) (entity)).toCompoundTag();
            case PIG -> ((PiPig) (entity)).toCompoundTag();
            case SHEEP -> ((PiSheep) (entity)).toCompoundTag();
            case ZOMBIE -> ((PiZombie) (entity)).toCompoundTag();
            case CREEPER -> ((PiCreeper) (entity)).toCompoundTag();
            case SKELETON -> ((PiSkeleton) (entity)).toCompoundTag();
            case SPIDER -> ((PiSpider) (entity)).toCompoundTag();
            case PIG_ZOMBIE -> ((PiPigZombie) (entity)).toCompoundTag();
            case ITEM_ENTITY -> ((PiItemEntity) (entity)).toCompoundTag();
            case PRIMED_TNT -> ((PiPrimedTnt) (entity)).toCompoundTag();
            case FALLING_TILE -> ((PiFallingTile) (entity)).toCompoundTag();
            case ARROW -> ((PiArrow) (entity)).toCompoundTag();
            case SNOWBALL -> ((PiSnowball) (entity)).toCompoundTag();
            case THROWN_EGG -> ((PiThrownEgg) (entity)).toCompoundTag();
            case PAINTING -> ((PiPainting) (entity)).toCompoundTag();
        };
    }

    private PiEntity toEntityClass(CompoundTag nbtEntity) {
        return switch (EntityType.fromID(nbtEntity.getInt("id"))) {
            case CHICKEN -> PiChicken.fromCompoundTag(nbtEntity);
            case COW -> PiCow.fromCompoundTag(nbtEntity);
            case PIG -> PiPig.fromCompoundTag(nbtEntity);
            case SHEEP -> PiSheep.fromCompoundTag(nbtEntity);
            case ZOMBIE -> PiZombie.fromCompoundTag(nbtEntity);
            case CREEPER -> PiCreeper.fromCompoundTag(nbtEntity);
            case SKELETON -> PiSkeleton.fromCompoundTag(nbtEntity);
            case SPIDER -> PiSpider.fromCompoundTag(nbtEntity);
            case PIG_ZOMBIE -> PiPigZombie.fromCompoundTag(nbtEntity);
            case ITEM_ENTITY -> PiItemEntity.fromCompoundTag(nbtEntity);
            case PRIMED_TNT -> PiPrimedTnt.fromCompoundTag(nbtEntity);
            case FALLING_TILE -> PiFallingTile.fromCompoundTag(nbtEntity);
            case ARROW -> PiArrow.fromCompoundTag(nbtEntity);
            case SNOWBALL -> PiSnowball.fromCompoundTag(nbtEntity);
            case THROWN_EGG -> PiThrownEgg.fromCompoundTag(nbtEntity);
            case PAINTING -> PiPainting.fromCompoundTag(nbtEntity);
        };
    }

    private CompoundTag fromTileEntityClass(PiTileEntity tileEntity) {
        return switch (TileEntityType.fromID(tileEntity.getID())) {
            case FURNACE -> ((PiFurnace) tileEntity).toCompoundTag();
            case CHEST -> ((PiChest) tileEntity).toCompoundTag();
            case SIGN -> ((PiSign) tileEntity).toCompoundTag();
            case NETHER_REACTOR_CORE -> ((PiNetherReactorCore) tileEntity).toCompoundTag();
        };
    }

    private PiTileEntity toTileEntityClass(CompoundTag nbtTileEntity) {
        return switch (TileEntityType.fromID(nbtTileEntity.getString("id"))) {
            case FURNACE -> PiFurnace.fromCompoundTag(nbtTileEntity);
            case CHEST -> PiChest.fromCompoundTag(nbtTileEntity);
            case SIGN -> PiSign.fromCompoundTag(nbtTileEntity);
            case NETHER_REACTOR_CORE -> PiNetherReactorCore.fromCompoundTag(nbtTileEntity);
        };
    }
}
