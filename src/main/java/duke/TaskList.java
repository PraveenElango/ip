package duke;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public int size(){
        return tasks.size();
    }

    public int tasksCount(){
        return tasks.size()-1;
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int index){
        tasks.remove(index);
    }



}
