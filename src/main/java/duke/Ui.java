package duke;

public class Ui {

    public Ui(){}

    //print the UI for Todo
    public void printTodo(TaskList taskList) {
        int tasksCount = taskList.tasksCount();
        System.out.print(taskList.getTask(tasksCount).typeChar() + " ");
        System.out.print(taskList.getTask(tasksCount).getStatusIcon() + " ");
        System.out.println(taskList.getTask(tasksCount).getDesc() + " ");
    }
    //print the UI for Deadline or Event
    public void printDeadlineOrEvent(TaskList taskList) {
        int tasksCount = taskList.tasksCount();
        System.out.print(taskList.getTask(tasksCount).typeChar() + " ");
        System.out.print(taskList.getTask(tasksCount).getStatusIcon() + " ");
        System.out.println(taskList.getTask(tasksCount).getDesc() + " ");
        System.out.println("(" + taskList.getTask(tasksCount).getBy() + ") ");
    }
    //notify user that task has been added
    public void TaskAddedNotifier(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }
    //print out the list of all the tasks
    public static void printTodoEventDeadlineList(TaskList taskList) {
        int index = 0;
        while (index < taskList.size()) {
            System.out.print((index + 1) + ".");
            System.out.print(taskList.getTask(index).typeChar() + " ");
            System.out.print(taskList.getTask(index).getStatusIcon() + " ");

            if (taskList.getTask(index) instanceof Deadline || taskList.getTask(index) instanceof Events) {
                System.out.print(taskList.getTask(index).getDesc() + " ");
                System.out.println("(" + taskList.getTask(index).getBy() + ") ");
            } else {
                System.out.println(taskList.getTask(index).getDesc() + " ");
            }
            index++;
        }
    }
    //print the UI for delete functionality
    public void printDelete(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.print(taskList.getTask(index-1).typeChar()+ " ");
        System.out.print(taskList.getTask(index-1).getStatusIcon() + " ");
        if (taskList.getTask(index-1) instanceof Deadline || taskList.getTask(index-1) instanceof Events){
            System.out.print(taskList.getTask(index-1).getDesc() + " ");
            System.out.println("("+ taskList.getTask(index-1).getBy() + ") ");
        } else {
            System.out.println(taskList.getTask(index-1).getDesc() + " ");
        }
        taskList.removeTask(index-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    //print the UI for the done functionality
    public void printDone(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        taskList.getTask(index-1).markAsDone();
        System.out.print(taskList.getTask(index-1).getStatusIcon() + " ");
        System.out.println(taskList.getTask(index-1).getDesc() + " ");
    }


}
