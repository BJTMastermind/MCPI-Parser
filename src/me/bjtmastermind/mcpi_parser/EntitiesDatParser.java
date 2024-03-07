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

import me.bjtmastermind.mcpi_parser.entities.Arrow;
import me.bjtmastermind.mcpi_parser.entities.Chicken;
import me.bjtmastermind.mcpi_parser.entities.Cow;
import me.bjtmastermind.mcpi_parser.entities.Creeper;
import me.bjtmastermind.mcpi_parser.entities.Entity;
import me.bjtmastermind.mcpi_parser.entities.FallingTile;
import me.bjtmastermind.mcpi_parser.entities.ItemEntity;
import me.bjtmastermind.mcpi_parser.entities.Painting;
import me.bjtmastermind.mcpi_parser.entities.Pig;
import me.bjtmastermind.mcpi_parser.entities.ZombiePigman;
import me.bjtmastermind.mcpi_parser.entities.PrimedTnt;
import me.bjtmastermind.mcpi_parser.entities.Sheep;
import me.bjtmastermind.mcpi_parser.entities.Skeleton;
import me.bjtmastermind.mcpi_parser.entities.Snowball;
import me.bjtmastermind.mcpi_parser.entities.Spider;
import me.bjtmastermind.mcpi_parser.entities.ThrownEgg;
import me.bjtmastermind.mcpi_parser.entities.Zombie;
import me.bjtmastermind.mcpi_parser.enums.EntityType;
import me.bjtmastermind.mcpi_parser.enums.TileEntityType;
import me.bjtmastermind.mcpi_parser.tile_entities.Chest;
import me.bjtmastermind.mcpi_parser.tile_entities.Furnace;
import me.bjtmastermind.mcpi_parser.tile_entities.NetherReactorCore;
import me.bjtmastermind.mcpi_parser.tile_entities.Sign;
import me.bjtmastermind.mcpi_parser.tile_entities.TileEntity;
import me.bjtmastermind.mcpi_parser.utils.LittleEndianUtils;
import me.bjtmastermind.nbt.io.NBTUtil;
import me.bjtmastermind.nbt.io.NamedTag;
import me.bjtmastermind.nbt.tag.CompoundTag;
import me.bjtmastermind.nbt.tag.ListTag;

public class EntitiesDatParser {
    private int magic;
    private int version;
    private int byteCount;

    private ArrayList<Entity> entities;
    private ArrayList<TileEntity> tileEntities;

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

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public ArrayList<TileEntity> getTileEntities() {
        return this.tileEntities;
    }

    public void assemble(String filepath, ArrayList<Entity> entities, ArrayList<TileEntity> tileEntities) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream rawOutput = new DataOutputStream(baos);

        ListTag<CompoundTag> nbtEntities = new ListTag<>();
        for (Entity entity : entities) {
            nbtEntities.add(this.fromEntityClass(entity));
        }

        ListTag<CompoundTag> nbtTileEntities = new ListTag<>();
        for (TileEntity tileEntity : tileEntities) {
            nbtTileEntities.add(this.fromTileEntityClass(tileEntity));
        }

        try {
            CompoundTag data = new CompoundTag();
            data.put("Entities", nbtEntities);
            data.put("TileEntities", nbtTileEntities);

            byte[] nbtBytes = NBTUtil.writeLE(data, false).toByteArray();

            rawOutput.write(LittleEndianUtils.intAsLEByteArray(5525061));
            rawOutput.write(LittleEndianUtils.intAsLEByteArray(1));
            rawOutput.write(LittleEndianUtils.intAsLEByteArray(nbtBytes.length));
            rawOutput.write(nbtBytes);

            File output = new File(filepath);
            Files.write(output.toPath(), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CompoundTag fromEntityClass(Entity entity) {
        return switch (EntityType.fromID(entity.getID())) {
            case CHICKEN -> ((Chicken) (entity)).toCompoundTag();
            case COW -> ((Cow) (entity)).toCompoundTag();
            case PIG -> ((Pig) (entity)).toCompoundTag();
            case SHEEP -> ((Sheep) (entity)).toCompoundTag();
            case ZOMBIE -> ((Zombie) (entity)).toCompoundTag();
            case CREEPER -> ((Creeper) (entity)).toCompoundTag();
            case SKELETON -> ((Skeleton) (entity)).toCompoundTag();
            case SPIDER -> ((Spider) (entity)).toCompoundTag();
            case ZOMBIE_PIGMAN -> ((ZombiePigman) (entity)).toCompoundTag();
            case ITEM_ENTITY -> ((ItemEntity) (entity)).toCompoundTag();
            case PRIMED_TNT -> ((PrimedTnt) (entity)).toCompoundTag();
            case FALLING_TILE -> ((FallingTile) (entity)).toCompoundTag();
            case ARROW -> ((Arrow) (entity)).toCompoundTag();
            case SNOWBALL -> ((Snowball) (entity)).toCompoundTag();
            case THROWN_EGG -> ((ThrownEgg) (entity)).toCompoundTag();
            case PAINTING -> ((Painting) (entity)).toCompoundTag();
        };
    }

    private Entity toEntityClass(CompoundTag nbtEntity) {
        return switch (EntityType.fromID(nbtEntity.getInt("id"))) {
            case CHICKEN -> Chicken.fromCompoundTag(nbtEntity);
            case COW -> Cow.fromCompoundTag(nbtEntity);
            case PIG -> Pig.fromCompoundTag(nbtEntity);
            case SHEEP -> Sheep.fromCompoundTag(nbtEntity);
            case ZOMBIE -> Zombie.fromCompoundTag(nbtEntity);
            case CREEPER -> Creeper.fromCompoundTag(nbtEntity);
            case SKELETON -> Skeleton.fromCompoundTag(nbtEntity);
            case SPIDER -> Spider.fromCompoundTag(nbtEntity);
            case ZOMBIE_PIGMAN -> ZombiePigman.fromCompoundTag(nbtEntity);
            case ITEM_ENTITY -> ItemEntity.fromCompoundTag(nbtEntity);
            case PRIMED_TNT -> PrimedTnt.fromCompoundTag(nbtEntity);
            case FALLING_TILE -> FallingTile.fromCompoundTag(nbtEntity);
            case ARROW -> Arrow.fromCompoundTag(nbtEntity);
            case SNOWBALL -> Snowball.fromCompoundTag(nbtEntity);
            case THROWN_EGG -> ThrownEgg.fromCompoundTag(nbtEntity);
            case PAINTING -> Painting.fromCompoundTag(nbtEntity);
        };
    }

    private CompoundTag fromTileEntityClass(TileEntity tileEntity) {
        return switch (TileEntityType.fromID(tileEntity.getID())) {
            case FURNACE -> ((Furnace) tileEntity).toCompoundTag();
            case CHEST -> ((Chest) tileEntity).toCompoundTag();
            case SIGN -> ((Sign) tileEntity).toCompoundTag();
            case NETHER_REACTOR_CORE -> ((NetherReactorCore) tileEntity).toCompoundTag();
        };
    }

    private TileEntity toTileEntityClass(CompoundTag nbtTileEntity) {
        return switch (TileEntityType.fromID(nbtTileEntity.getString("id"))) {
            case FURNACE -> Furnace.fromCompoundTag(nbtTileEntity);
            case CHEST -> Chest.fromCompoundTag(nbtTileEntity);
            case SIGN -> Sign.fromCompoundTag(nbtTileEntity);
            case NETHER_REACTOR_CORE -> NetherReactorCore.fromCompoundTag(nbtTileEntity);
        };
    }
}
