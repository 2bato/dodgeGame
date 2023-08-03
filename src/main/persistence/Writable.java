package persistence;

import org.json.JSONObject;

// Based from JsonSerializationDemo
public interface Writable {
    JSONObject toJson();
}
