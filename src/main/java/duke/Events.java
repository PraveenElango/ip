package duke;

public class Events extends Todo {

    protected String by;

    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

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