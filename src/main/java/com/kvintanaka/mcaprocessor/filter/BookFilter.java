package com.kvintanaka.mcaprocessor.filter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Take all books in the chunk
 * 
 * @author kvintanaka <github.com/kvintanaka>
 * @version 1.0
 */
public class BookFilter implements Filter {

    /**
     * 
     * @param chunk NBT Structured chunk wrapped in JSON
     * 
     * @return List of extracted properties that passed the filter
     */
    @Override
    public List<JSONObject> extract(JSONObject chunk) {
        LinkedList<JSONObject> extracted = new LinkedList<JSONObject>();

        for (JSONObject tileEntity : extractTileEntities(chunk)) {
            if (tileEntity.getString("id").equals("minecraft:chest")) {
                for (JSONObject item : extractItems(tileEntity)) {
                    if (item.getString("id").equals("minecraft:written_book")) {
                        extracted.add(item);
                    }
                }
            }
        }

        return extracted;
    }

    /**
     * Extract tile entites from chunk
     * 
     * @param chunk selected chunk in JSON format
     * @return tile entites from chunk
     */
    private Iterable<JSONObject> extractTileEntities(JSONObject chunk) {
        JSONArray entities = chunk.getJSONArray("tileEntities");

        return new Iterable<JSONObject>() {
            private int index = 0;

            @Override
            public Iterator<JSONObject> iterator() {
                return new Iterator<JSONObject>() {

                    @Override
                    public boolean hasNext() {
                        return index < entities.length();
                    }

                    @Override
                    public JSONObject next() {
                        return entities.getJSONObject(index++);
                    }

                };
            }
        };
    }

    /**
     * Extract item from entites
     * 
     * @param entity Entites that can hold some items
     * @return items from entity
     */
    private Iterable<JSONObject> extractItems(JSONObject entity) {
        JSONArray items = entity.getJSONArray("Items");

        return new Iterable<JSONObject>() {
            private int index = 0;

            @Override
            public Iterator<JSONObject> iterator() {
                return new Iterator<JSONObject>() {

                    @Override
                    public boolean hasNext() {
                        return index < items.length();
                    }

                    @Override
                    public JSONObject next() {
                        return items.getJSONObject(index++);
                    }

                };
            }
        };
    }

}