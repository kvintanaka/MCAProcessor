package com.kvintanaka.mcaprocessor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import com.kvintanaka.mcaprocessor.adapter.mca.MCAAdapter;
import com.kvintanaka.mcaprocessor.adapter.mca.MCAAdapterImplementation;
import com.kvintanaka.mcaprocessor.converter.Converter;
import com.kvintanaka.mcaprocessor.filter.Filter;

import org.json.JSONObject;

/**
 * Main Class to Process (Convert and Filter) all mca file in the save directory
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public class MCAProcessor {
    private Path inputDirectory;

    /**
     * Default Constructor
     * 
     * @param inputDirectory Input directory for the regions file (in world
     *                       directory > regions)
     */
    public MCAProcessor(Path inputDirectory) {
        this.inputDirectory = inputDirectory;
    }

    /**
     * Walk through input directory to process all mca file Filter and then convert
     * 
     * @param filter    Filter NBT parts that is needed for conversion
     * @param converter Convert filtered parts to a format based on the converter
     *                  subclasss
     */
    public void process(Filter filter, Converter converter) {
        try {
            Files.walkFileTree(inputDirectory, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // Each file in the directory
                    if (file.toString().endsWith(".mca")) {
                        MCAAdapter region = new MCAAdapterImplementation(file); // Read region file

                        region.getChunks().forEach((chunk) -> {
                            List<JSONObject> extracted;
                            if ((extracted = filter.extract(chunk.toJSON())) != null) {
                                converter.convert(extracted);
                            }
                        });
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}