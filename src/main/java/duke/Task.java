package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDesc() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    //overridden methods by subclasses
    public String typeChar() {
        return "duke.Task";
    }
    public String getBy() {
        return "by";
    }

}