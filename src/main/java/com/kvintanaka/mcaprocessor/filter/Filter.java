package com.kvintanaka.mcaprocessor.filter;

import java.util.List;

import org.json.JSONObject;

/**
 * <p>
 * Filter used to filter out specified properties from chunk
 * </p>
 * <p>
 * Note: JSON Format is used
 * </p>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 */
public interface Filter {
    public List<JSONObject> extract(JSONObject chunk);
}