package com.kvintanaka.mcaprocessor.adapter.mca;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import com.kvintanaka.mcaprocessor.adapter.nbt.NBTAdapter;
import com.kvintanaka.mcaprocessor.adapter.nbt.NBTAdapterImplementation;

import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;

/**
 * <h2>The implementation of adapter for NBT Library</h2>
 * <p>
 * A translation layer for MCA File format
 * </p>
 * <i> NBT Library Source: github.com/Quertz/NBT </i>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public class MCAAdapterImplementation implements MCAAdapter {
    private MCAFile region;

    /**
     * Default Constructor
     * 
     * @param mca The path to mca file
     * @throws IOException
     */
    public MCAAdapterImplementation(Path mca) throws IOException {
        this.region = MCAUtil.read(mca.toFile());
    }

    public Iterable<NBTAdapter> getChunks() {
        return new Iterable<NBTAdapter>() {
            private int xCoords = 0;
            private int zCoords = 0;
            private Chunk currentChunk = null;

            @Override
            public Iterator<NBTAdapter> iterator() {
                return new Iterator<NBTAdapter>() {

                    @Override
                    public boolean hasNext() {
                        while (xCoords < 32) {
                            while (zCoords < 32) {
                                if ((currentChunk = region.getChunk(xCoords, zCoords)) != null) {
                                    zCoords++;
                                    if (currentChunk.getTileEntities().size() != 0) { // Skip empty chunk
                                        return true;
                                    }
                                } else {
                                    zCoords++;
                                }
                            }
                            xCoords++;
                            zCoords = 0;
                        }

                        return false;
                    }

                    @Override
                    public NBTAdapter next() {
                        return new NBTAdapterImplementation(currentChunk.getTileEntities());
                    }

                };
            }
        };
    }
}