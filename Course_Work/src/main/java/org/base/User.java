package org.base;

import com.google.gson.JsonObject;
import org.utils.CourseJSONRPC;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String username;
    private String password;
    private String role;

    public User(){};

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public JsonObject createUser(String username, String password, String role) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", username);
        params.put("password", password);
        params.put("role", role);
        return CourseJSONRPC.baseRequest("createUser", params);
    }

    public JsonObject deleteUser(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", username);
        return CourseJSONRPC.baseRequest("removeUser", params);
    }

    public String getUserName() {
        return username;
    }
}
