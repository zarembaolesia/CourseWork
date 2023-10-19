package org.utils;

import com.google.gson.JsonObject;
import org.base.Project;
import org.base.Task;
import org.base.User;

import java.util.Map;

public class CourseJSONRPC
{
    static private long rpcIdCounter = 1;
    final public User user = new User();
    final public Project project = new Project();
    final public Task task = new Task();

    static public JsonObject baseRequest(String method, Map<String, Object> parameters) {
        JsonObject requestPayload = new JsonObject();
        requestPayload.addProperty("jsonrpc",   "2.0");
        requestPayload.addProperty("method",    method);
        requestPayload.addProperty("id",        rpcIdCounter ++);

        JsonObject params = new JsonObject();
        for (String param : parameters.keySet()) {
            Object value = parameters.get(param);
            if (value.getClass() == String.class)
                params.addProperty(param, (String) value);
            else if (value.getClass() == Integer.class)
                params.addProperty(param, (Integer) value);
            else if (value.getClass() == Boolean.class)
                params.addProperty(param, (Boolean) value);
            else
                throw new RuntimeException("Unsupported value " + value.getClass().getSimpleName());
        }

        requestPayload.add("params", params);

        return requestPayload;
    }
}
