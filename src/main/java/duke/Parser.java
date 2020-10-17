package duke;


import java.util.ArrayList;

/**
 * Represents the component of the program that handles and processes
 * user inputs and interacts with the UI accordingly. The adding,
 * handling, and deleting of all tasks is done here.
 */
public class Parser {

    private static TaskList taskList;
    private static Ui ui;

    /**
     * Initialises Parser.
     */
    public Parser(Ui ui, TaskList taskList) {
        Parser.ui = ui;
        Parser.taskList = taskList;

    }

    /**
     * Adds a Todo task to the TaskList and prints it in the UI
     * if it has been successfully added.
     * @param description Description of the Todo task.
     */
    private static void addTodo(String description) {
        taskList.addTask(new Todo(description));
        ui.printTodo(taskList);
    }

    /**
     * Adds a Deadline task to the TaskList and prints it in the UI
     * if it has been successfully added.
     * @param description Description of the Deadline task.
     */
    private static void addDeadline(String description, String by) {
        taskList.addTask(new Deadline(description, by));
        ui.printDeadlineOrEvent(taskList);
    }

    /**
     * Adds an Event task to the TaskList and prints it in the UI
     * if it has been successfully added.
     * @param description Description of the Event task.
     */
    private static void addEvent(String description, String by) {
        taskList.addTask(new Events(description, by));
        ui.printDeadlineOrEvent(taskList);
    }

    /**
     * Marks a task as done
     * @param args Used to obtain index of task in TaskList to mark as done.
     * @throws NumberFormatException if formatting of input is incorrect, i.e.
     * not aligned with what is pointed out in the UG
     * @throws IndexOutOfBoundsException if the task is not in the TaskList.
     */
    private static void handleDone(String args) {
        int index;
        //Check correct formatting
        try {
            index = Integer.parseInt(args);
        }catch(NumberFormatException e){
            System.out.println("Check formatting of usage of done!");
            return;
        }
        //Marking task as done
        try {
            ui.printDone(taskList, index);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please check again!");
        }
    }

    private static void handleTodo(String args) {
        addTodo(args);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleDeadline(String desc, String by) {
        addDeadline(desc,by);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleEvent(String desc, String by) {
        addEvent(desc,by);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleDelete(String args) {
        int index = Integer.parseInt(args);
        ui.printDelete(taskList, index);
    }

    private static void handleBye() {
        Storage.saveList(taskList);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleFind(String args) {
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> matchingTaskList = new ArrayList<>();
        for(Task task:tasks){
            if(task.getDesc().contains(args)){
                matchingTaskList.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        Ui.findList(matchingTaskList);

    }

    /**
     * Handles the commands passed in by the user accordingly.
     * @param cmd Command passed in by user.
     * @return false If user inputs bye as command.
     * @return true If method has completed execution.
     */
    public static boolean handleCommand(String cmd) {
        String cmdArgs = "";
        String cmdAfterSlash = "";
        int spaceIndex = cmd.indexOf(" ");
        int slashIndex = cmd.indexOf("/");
        //indexing
        if(spaceIndex != -1){
            cmdArgs = cmd.substring(spaceIndex + 1).trim();
        }
        if(slashIndex != -1){
            cmdAfterSlash = cmd.substring(slashIndex + 1).trim();
        }
        //cases
        if (cmd.startsWith("done")) {
            handleDone(cmdArgs);
        } else if(cmd.startsWith("todo")) {
            handleTodo(cmdArgs);
        } else if(cmd.startsWith("deadline")) {
            handleDeadline(cmd.substring(spaceIndex + 1,slashIndex), cmdAfterSlash);
        } else if(cmd.startsWith("event")) {
            handleEvent(cmd.substring(spaceIndex + 1,slashIndex),cmdAfterSlash);
        } else if(cmd.equals("list")) {
            Ui.printTodoEventDeadlineList(taskList);
        } else if(cmd.startsWith("delete")) {
            handleDelete(cmdArgs);
        } else if(cmd.startsWith("find")) {
            handleFind(cmdArgs);
        }
        else if(cmd.equals("bye")) {
            handleBye();
            return false;
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
