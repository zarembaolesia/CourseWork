package org.base;

import com.google.gson.JsonObject;
import org.utils.CourseJSONRPC;
import java.util.HashMap;
import java.util.Map;


public class Task {
    public String taskName;
    public Integer projectId;

    public Task(Integer projectId, String taskName) {
        this.projectId = projectId;
        this.taskName = taskName;
    }
    public Task() {}

    public JsonObject createTask(String taskTitle, Integer project_id){
        Map<String, Object> params = new HashMap<>(  );
        params.put("title",taskTitle);
        params.put("project_id",project_id);
        return CourseJSONRPC.baseRequest("createTask",params) ;
    }

    public JsonObject deleteTask(String taskName){
        Map<String, Object> params = new HashMap<>(  );
        params.put("task_id",taskName);
        return CourseJSONRPC.baseRequest("removeTask",params) ;
    }
}
