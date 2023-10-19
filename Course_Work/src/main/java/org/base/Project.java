package org.base;

import com.google.gson.JsonObject;
import org.utils.CourseJSONRPC;
import java.util.HashMap;
import java.util.Map;

public class Project {
    public Integer projectId;
    public String projectName;

    public Project(Integer projectId,String projectName){
        this.projectId = projectId;
        this.projectName = projectName;
    }
    public Project(){
    }

    public JsonObject createProject(String projectName){
        Map<String, Object> params = new HashMap<>();
        params.put("name", projectName);
        return CourseJSONRPC.baseRequest("createProject", params);
    }

    public JsonObject deleteProject(String projectName){
        Map<String, Object> params = new HashMap<>(  );
        params.put("name", projectName);
        return CourseJSONRPC.baseRequest("removeProject", params);
    }

    public Integer getId() {
        return projectId;
    }

    public String getName() {
        return projectName;
    }
}
