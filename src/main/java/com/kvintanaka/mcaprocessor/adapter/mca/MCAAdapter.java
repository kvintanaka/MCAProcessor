package com.kvintanaka.mcaprocessor.adapter.mca;

import com.kvintanaka.mcaprocessor.adapter.nbt.NBTAdapter;

/**
 * <h2>Adapter to MCA Libraries</h2>
 * <p>
 * This MCAAdapter will use NBTAdapter to output a list of NBT contained inside
 * MCA
 * </p>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public interface MCAAdapter {
    public Iterable<NBTAdapter> getChunks();
}