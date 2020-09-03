package com.kvintanaka.mcaprocessor.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

/**
 * Write Books from NBT to JSON format Note: Metadata will be stripped to
 * produce a clean json
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public class BookToJSONConverter implements Converter {

    /**
     * <p>
     * Convert books in NBT structure to a several separate json file
     * </p>
     * <p>
     * Multithreaded file write for each json
     * </p>
     * >
     * 
     * @param books List of books in NBT structure (wrapped in JSON Object)
     */
    public void convert(List<JSONObject> books) {
        if (!new File("Output").exists()) {
            new File("Output").mkdir();
        }
        for (JSONObject book : books) {
            try (FileWriter writer = new FileWriter("Output/" + generateFileName(book))) {
                writer.write(refineBook(book).toString());
            } catch (IOException e) {
                // Logger.write("Something");
            }
        }
    }

    /**
     * Generate filename for each book Formatted as: "<hash-code> [<author>]
     * <book-title>.json"
     * 
     * @param book The book as a refference for filename
     * 
     * @return String representing the filename
     */
    private String generateFileName(JSONObject book) {
        book = book.getJSONObject("tag");
        return String.format("%1.6s - [%1.16s] %1.218s.json", Integer.toHexString(book.hashCode()),
                book.getString("author"), book.getString("title"));
    }

    /**
     * Strip out unnecesary metadata for book
     * 
     * @param book The book refference to be stripped
     * @return The cleaned metadata for book
     */
    private JSONObject refineBook(JSONObject book) {
        book = book.getJSONObject("tag");
        return new JSONObject().put("pages", book.getJSONArray("pages")).put("title", book.getString("title"))
                .put("author", book.getString("author"));
    }
}