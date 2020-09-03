package com.kvintanaka.mcaprocessor.adapter.nbt;

import org.json.JSONArray;
import org.json.JSONObject;

import net.querz.nbt.tag.ByteTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.DoubleTag;
import net.querz.nbt.tag.FloatTag;
import net.querz.nbt.tag.IntTag;
import net.querz.nbt.tag.ListTag;
import net.querz.nbt.tag.LongTag;
import net.querz.nbt.tag.NumberTag;
import net.querz.nbt.tag.ShortTag;
import net.querz.nbt.tag.StringTag;
import net.querz.nbt.tag.Tag;

/**
 * <h2>The implementation of adapter for NBT Library</h2>
 * <p>
 * A translation layer for NBT File format
 * </p>
 * <i> NBT Library Source: github.com/Quertz/NBT </i>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public class NBTAdapterImplementation implements NBTAdapter {
    private ListTag<CompoundTag> tileEntities;

    /**
     * Default Constructor
     * 
     * @param tileEntities The tileEntities properties in the chunk
     */
    public NBTAdapterImplementation(ListTag<CompoundTag> tileEntities) {
        this.tileEntities = tileEntities;
    }

    /**
     * Return JSON formatted NBT
     * 
     * @return The JSON formatted NBT
     */
    @Override
    public JSONObject toJSON() {
        return insertToJSON(new JSONObject(), "tileEntities", this.tileEntities);
    }

    /**
     * Mapping NBT to JSON Array
     * 
     * @param jsonArray The JSON Array to be modified
     * @param values    The Value for current data
     * @return The JSON Array itself
     */
    private JSONArray insertToJSONArray(JSONArray jsonArray, Tag<?> values) {
        switch (values.getClass().getSimpleName()) {
            case "ListTag":
                ((ListTag<?>) values).forEach((value) -> {
                    String listType = value.getClass().getSimpleName();
                    switch (listType) {
                        case "EndTag":
                            break;

                        case "CompoundTag":
                            JSONObject compound = new JSONObject();
                            ((CompoundTag) value).forEach((k, v) -> {
                                insertToJSON(compound, k, v);
                            });
                            jsonArray.put(compound);
                            break;

                        case "ByteTag":
                            jsonArray.put(((ByteTag) value).asByte());
                            break;

                        case "DoubleTag":
                            jsonArray.put(((DoubleTag) value).asDouble());
                            break;

                        case "FloatTag":
                            jsonArray.put(((FloatTag) value).asFloat());
                            break;

                        case "IntTag":
                            jsonArray.put(((IntTag) value).asInt());
                            break;

                        case "LongTag":
                            jsonArray.put(((LongTag) value).asLong());
                            break;

                        case "ShortTag":
                            jsonArray.put(((ShortTag) value).asShort());
                            break;

                        case "StringTag":
                            jsonArray.put(((StringTag) value).getValue());
                            break;

                        case "ListTag":
                            jsonArray.put(insertToJSONArray(new JSONArray(), (ListTag<?>) value));
                            break;
                    }
                });
                break;

            case "ArrayTag": // Stub
                // ((ArrayTag) values).forEach((value) -> {
                // jsonArray.put(((Tag) value).asLong());
                // });
                break;

            case "ByteArrayTag": // Stub
                // ((ByteArrayTag) values).forEach((value) -> {
                // jsonArray.put(((LongTag) value).asLong());
                // });
                break;

            case "IntArrayTag": // Stub
                // ((IntArrayTag) values).forEach((value) -> {
                // jsonArray.put(((LongTag) value).asLong());
                // });
                break;

            case "LongArrayTag": // Stub
                // ((LongArrayTag) values).forEach((value) -> {
                // jsonArray.put(((LongTag) value).asLong());
                // });
                break;
        }

        return jsonArray;
    }

    /**
     * Mapping NBT to JSON
     * 
     * @param json  The JSON Object to be modified
     * @param key   The Key for current data
     * @param value The Value for current data
     * @return The JSON Object itself
     */
    private JSONObject insertToJSON(JSONObject json, String key, Tag<?> value) {
        switch (value.getClass().getSimpleName()) {

            case "EndTag":
                break;

            case "CompoundTag":
                JSONObject compound = new JSONObject();
                ((CompoundTag) value).forEach((k, v) -> {
                    insertToJSON(compound, k, v);
                });
                json.put(key, compound);
                break;

            case "ByteTag":
                json.put(key, ((ByteTag) value).asByte());
                break;

            case "DoubleTag":
                json.put(key, ((DoubleTag) value).asDouble());
                break;

            case "FloatTag":
                json.put(key, ((FloatTag) value).asFloat());
                break;

            case "IntTag":
                json.put(key, ((IntTag) value).asInt());
                break;

            case "LongTag":
                json.put(key, ((LongTag) value).asLong());
                break;

            case "NumberTag": // Number
                json.put(key, ((NumberTag<?>) value).asInt());
                break;

            case "ShortTag":
                json.put(key, ((ShortTag) value).asShort());
                break;

            case "StringTag":
                json.put(key, ((StringTag) value).getValue());
                break;

            case "ListTag":
                json.put(key, insertToJSONArray(new JSONArray(), (ListTag<?>) value));
                break;

            case "ArrayTag": // Stub
                // json.put(key, insertToJSONArray(new JSONArray(), (ArrayTag) value));
                break;

            case "ByteArrayTag": // Stub
                // json.put(key, insertToJSONArray(new JSONArray(), (ByteArrayTag) value));
                break;

            case "IntArrayTag": // Stub
                // json.put(key, insertToJSONArray(new JSONArray(), (IntArrayTag) value));
                break;

            case "LongArrayTag": // Stub
                // json.put(key, insertToJSONArray(new JSONArray(), (LongArrayTag) value));
                break;
        }

        return json;
    }
}