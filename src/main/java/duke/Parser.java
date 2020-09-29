package duke;


import java.util.ArrayList;

public class Parser {

    private static TaskList taskList;
    private static Ui ui;
    private static Storage storage;

    //declaration of Parser constructor
    public Parser(Ui ui, TaskList taskList, Storage storage){
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;

    }
    //adder functions
    private static void addTodo(String description) {
        taskList.addTask(new Todo(description));
        ui.printTodo(taskList);
    }

    private static void addDeadline(String description, String by) {
        taskList.addTask(new Deadline(description, by));
        ui.printDeadlineOrEvent(taskList);
    }

    private static void addEvent(String description, String by) {
        taskList.addTask(new Events(description, by));
        ui.printDeadlineOrEvent(taskList);
    }

    //handler functions
    private static void handleDone(String args){
        int index;
        //Check correct formatting
        try{
            index = Integer.parseInt(args);
        }catch(NumberFormatException e){
            System.out.println("Check formatting of usage of done!");
            return;
        }
        //Marking task as done
        try{
            ui.printDone(taskList, index);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please check again!");
        }
    }

    private static void handleTodo(String args){
        addTodo(args);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleDeadline(String desc, String by){
        addDeadline(desc,by);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleEvent(String desc, String by){
        addEvent(desc,by);
        ui.TaskAddedNotifier(taskList);
    }

    private static void handleDelete(String args){
        int index = Integer.parseInt(args);
        ui.printDelete(taskList, index);
    }

    private static void handleBye(){
        storage.saveList(taskList);
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleFind(String args){
        ArrayList<Task> tasks = taskList.getTasks();
        TaskList matchingTaskList = new TaskList();
        for(Task task:tasks){
            if(task.getDesc().contains(args)){
                matchingTaskList.addTask(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        ui.printTodoEventDeadlineList(matchingTaskList);
    }

    //method to handle the command input of the user
    public static boolean handleCommand(String cmd) throws DukeException {
        String cmdArgs = "";
        String cmdAfterSlash = "";
        int spaceIndex = cmd.indexOf(" ");
        int slashIndex = cmd.indexOf("/");
        //indexing
        if(spaceIndex != -1){
            cmdArgs = cmd.substring(spaceIndex+1).trim();
        }
        if(slashIndex != -1){
            cmdAfterSlash = cmd.substring(slashIndex+1).trim();
        }
        //cases
        if(cmd.startsWith("done")){
            handleDone(cmdArgs);
        }else if(cmd.startsWith("todo")){
            handleTodo(cmdArgs);
        }else if(cmd.startsWith("deadline")){
            handleDeadline(cmd.substring(spaceIndex+1,slashIndex), cmdAfterSlash);
        }else if(cmd.startsWith("event")) {
            handleEvent(cmd.substring(spaceIndex+1,slashIndex),cmdAfterSlash);
        }else if(cmd.equals("list")){
            ui.printTodoEventDeadlineList(taskList);
        }else if(cmd.startsWith("delete")){
            handleDelete(cmdArgs);
        }else if(cmd.startsWith("find")){
            handleFind(cmdArgs);
        }
        else if(cmd.equals("bye")){
            handleBye();
            return false;
        }else{
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
