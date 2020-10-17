package duke;

/**
 * Represents an event task in the todo list when added and comes
 * with date and time when event will be held.
 */
public class Events extends Todo {

    protected String by;

    /**
     * Initialises Event.
     */
    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Setting of event.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Set the task as done or otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Obtaining event details.
     * @return Date and time of event.
     */
    public String getBy() {
        return by;
    }

    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
    public String typeChar() {
        return "[E]";
    }
}