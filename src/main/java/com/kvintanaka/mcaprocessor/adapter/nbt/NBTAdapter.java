package com.kvintanaka.mcaprocessor.adapter.nbt;

import org.json.JSONObject;

/**
 * <h2>Adapter to NBT Libraries</h2>
 * <p>
 * Internally reads NBT as JSON format
 * </p>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public interface NBTAdapter {
    public JSONObject toJSON();
}