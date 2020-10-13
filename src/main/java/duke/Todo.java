package duke;

import duke.Task;

/**
 * Represents a todo task to be added in the todo list.
 */
public class Todo extends Task {
    protected boolean isDone;

    /**
     * Initialises Todo.
     */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    /**
     * Set the task as done or otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns boolean value of whether task is done or not
     * @return status of task completion.
     */
    public boolean isDone() {
        return isDone;
    }

    public String toString() {
        String status = null;
        if (isDone){
            status = "Yes";
        } else {
            status = "No";
        }
        return super.toString() + System.lineSeparator() + "is done? " + status;
    }
    public String typeChar() {
        return "[T]";
    }
}