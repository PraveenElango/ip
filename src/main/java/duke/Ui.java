package duke;

import java.util.ArrayList;

/**
 * Accounts for the User Interface where the printing is handled based on the
 * user commands.
 */
public class Ui {

    public Ui(){}

    /**
     * Prints the UI for Todo tasks.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     */
    public void printTodo(TaskList taskList) {
        int tasksCount = taskList.tasksCount();
        System.out.print(taskList.getTask(tasksCount).typeChar() + " ");
        System.out.print(taskList.getTask(tasksCount).getStatusIcon() + " ");
        System.out.println(taskList.getTask(tasksCount).getDesc() + " ");
    }

    /**
     * Prints the UI for Deadline or Event tasks.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     */
    public void printDeadlineOrEvent(TaskList taskList) {
        int tasksCount = taskList.tasksCount();
        System.out.print(taskList.getTask(tasksCount).typeChar() + " ");
        System.out.print(taskList.getTask(tasksCount).getStatusIcon() + " ");
        System.out.println(taskList.getTask(tasksCount).getDesc() + " ");
        System.out.println("(" + taskList.getTask(tasksCount).getBy() + ") ");
    }

    /**
     * Prints the UI for tasks added to list.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     */
    public void TaskAddedNotifier(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Prints the UI for Todo, Deadline, and Event tasks.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     */
    public static void printTodoEventDeadlineList(TaskList taskList) {
        int index = 0;
        if (taskList.size() == 0) {
            System.out.println("No tasks in list!");
        }
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

    public static void findList(ArrayList<Task> findList) {
        int index = 0;
        if (findList.size() == 0) {
            System.out.println("No tasks in list!");
        }
        while (index < findList.size()) {
            System.out.print((index + 1) + ".");
            System.out.print(findList.get(index).typeChar() + " ");
            System.out.print(findList.get(index).getStatusIcon() + " ");

            if (findList.get(index) instanceof Deadline || findList.get(index) instanceof Events) {
                System.out.print(findList.get(index).getDesc() + " ");
                System.out.println("(" + findList.get(index).getBy() + ") ");
            } else {
                System.out.println(findList.get(index).getDesc() + " ");
            }
            index++;
        }
    }

    /**
     * Prints the UI for deletion of tasks by user.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     * @param index index of task to be deleted.
     */
    public void printDelete(TaskList taskList, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.print(taskList.getTask(index - 1).typeChar()+ " ");
        System.out.print(taskList.getTask(index - 1).getStatusIcon() + " ");
        if (taskList.getTask(index-1) instanceof Deadline || taskList.getTask(index - 1) instanceof Events){
            System.out.print(taskList.getTask(index - 1).getDesc() + " ");
            System.out.println("("+ taskList.getTask(index - 1).getBy() + ") ");
        } else {
            System.out.println(taskList.getTask(index - 1).getDesc() + " ");
        }
        taskList.removeTask(index-1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the UI for tasks marked done by user.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     * @param index index of task to be marked done.
     */
    public void printDone(TaskList taskList, int index) {
        System.out.println("Nice! I've marked this task as done:");
        taskList.getTask(index-1).markAsDone();
        System.out.print(taskList.getTask(index - 1).getStatusIcon() + " ");
        System.out.println(taskList.getTask(index - 1).getDesc() + " ");
    }

}