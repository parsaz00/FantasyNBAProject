package persistence;

import org.json.JSONObject;

// Source: JsonSerializationDemo
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
