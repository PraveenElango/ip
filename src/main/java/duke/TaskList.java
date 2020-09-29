package duke;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    //declaration of TaskList constructor
    public TaskList(){
        tasks = new ArrayList<Task>();
    }
    //returns size of tasks ArrayList
    public int size(){
        return tasks.size();
    }
    //returns final index of tasks ArrayList
    public int tasksCount(){
        return tasks.size()-1;
    }
    //returns task from tasks ArrayList based on input index
    public Task getTask(int index){
        return tasks.get(index);
    }
    //returns whole tasks ArrayList
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    //adds task to tasks ArrayList
    public void addTask(Task task){
        tasks.add(task);
    }
    //removes task from tasks ArrayList
    public void removeTask(int index){
        tasks.remove(index);
    }



}
