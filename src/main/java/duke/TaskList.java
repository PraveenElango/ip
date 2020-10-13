package duke;

import java.util.ArrayList;

/**
 * Represents the lists of tasks stored according to tasks
 * added in by user.
 */
public class TaskList {

    private static ArrayList<Task> tasks;

    /**
     * Initialises TaskList with an ArrayList to keep track
     * of the tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Returns size of the tasks ArrayList.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns latest item added to the tasks ArrayList.
     *
     * @return final index of tasks ArrayList.
     */
    public int tasksCount() {
        return tasks.size() - 1;
    }

    /**
     * Returns task from tasks ArrayList based on input index.
     *
     * @param index Index of ArrayList.
     * @return task from tasks ArrayList based on input index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all the tasks from tasks ArrayList.
     *
     * @return the tasks ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to the TaskList.
     *
     * @param task Task to be added to tasks ArrayList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from ArrayList based on index of task
     * given by user.
     *
     * @param index Index of task to be removed tasks ArrayList.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

}
