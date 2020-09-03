package com.kvintanaka.mcaprocessor.converter;

import java.util.List;

import org.json.JSONObject;

/**
 * <p>
 * Convert the list of item to any programmed feature
 * </p>
 * <p>
 * Note: JSON Format is used
 * </p>
 * 
 * @author kvintanaka <github.com/kvintanaka>
 */
public interface Converter {
    public void convert(List<JSONObject> items);
}