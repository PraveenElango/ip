package duke;

/**
 * Represents a deadline task in the todo list when added and comes
 * with deadline of date and time by which task is to be completed.
 */
public class Deadline extends Todo {

    protected String by;


    /**
     * Initialises Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Set the task as done or otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Setting of deadline.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Obtaining deadline details.
     * @return Date and time of deadline.
     */
    public String getBy() {
        return by;
    }


    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
    public String typeChar() {
        return "[D]";
    }
}