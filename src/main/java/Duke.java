import  java.util.Scanner;
import java.io.InputStream;
import java.lang.*;

public class Duke{
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;
    private static void addTask(String description){
        tasks[tasksCount] = new Task(description);
        System.out.print(tasks[tasksCount].typeChar()+ " ");
        System.out.print(tasks[tasksCount].getStatusIcon() + " ");
        System.out.println(tasks[tasksCount].getDesc() + " ");
        tasksCount += 1;
    }
    private static void addTodo(String description){
        tasks[tasksCount] = new Todo(description);
        System.out.print(tasks[tasksCount].typeChar()+ " ");
        System.out.print(tasks[tasksCount].getStatusIcon() + " ");
        System.out.println(tasks[tasksCount].getDesc() + " ");
        tasksCount += 1;
    }
    private static void addDeadline(String description, String by){
        tasks[tasksCount] = new Deadline(description, by);
        System.out.print(tasks[tasksCount].typeChar()+ " ");
        System.out.print(tasks[tasksCount].getStatusIcon() + " ");
        System.out.print(tasks[tasksCount].getDesc() + " ");
        System.out.println("(" + tasks[tasksCount].getBy() + ") ");
        tasksCount += 1;
    }
    private static void addEvent(String description, String by){
        tasks[tasksCount] = new Events(description, by);
        System.out.print(tasks[tasksCount].typeChar()+ " ");
        System.out.print(tasks[tasksCount].getStatusIcon() + " ");
        System.out.print(tasks[tasksCount].getDesc() + " ");
        System.out.println("(" + tasks[tasksCount].getBy() + ") ");
        tasksCount += 1;
    }
    private static void printTasksList() {
        int i = 0;
        while (tasks[i] != null) {
            System.out.print((i + 1) + ".");
            System.out.print(tasks[i].getStatusIcon() + " ");
            System.out.println(tasks[i].getDesc() + " ");
            i++;
        }
    }
    private static void printTodoEventDeadlineList(){
        int i = 0;
        while (tasks[i] != null) {
            System.out.print((i + 1) + ".");
            System.out.print(tasks[i].typeChar()+ " ");
            System.out.print(tasks[i].getStatusIcon() + " ");
            System.out.println(tasks[i].getDesc() + " ");
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        while (!op.equals("bye")) {
            if (!op.equals("list") && !op.startsWith("done") && !op.startsWith("todo") && !op.startsWith("deadline") && !op.startsWith("event")) {
                System.out.println("added: " + op);
                addTask(op);
            } else if (op.startsWith("done")) {
                //if they input done without number
                if (op.length() == 4) {
                    System.out.println("Please specify index of task!");
                    op = sc.nextLine();
                }
                String index_string = op.substring(5);
                int index = Integer.parseInt(index_string);
                if (tasks[index - 1] == null || index < 0 || index > 99) {
                    System.out.println("Out of bounds! Try again!");
                    op = sc.nextLine();
                    continue;
                }
                if (tasks[index - 1].isDone()) {
                    System.out.println("Already done!");
                    op = sc.nextLine();
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks[index - 1].markAsDone();
                System.out.print(tasks[index - 1].getStatusIcon() + " ");
                System.out.println(tasks[index - 1].getDesc() + " ");
            }else if (op.startsWith("todo")) {
                System.out.println("Got it. I've added this task:");
                addTodo(op.substring(5));
                System.out.println("Now you have " + tasksCount + " tasks in the list");
            }else if(op.startsWith("deadline")){
                System.out.println("Got it. I've added this task:");
                int slashIndex = op.indexOf("/");
                addDeadline(op.substring(9, slashIndex-1),op.substring(slashIndex+1));
                System.out.println("Now you have " + tasksCount + " tasks in the list");
            }else if(op.startsWith("event")){
                System.out.println("Got it. I've added this task:");
                int slashIndex = op.indexOf("/");
                addEvent(op.substring(6, slashIndex-1),op.substring(slashIndex+1));
                System.out.println("Now you have " + tasksCount + " tasks in the list");
            }else{
                System.out.println("Here are the tasks in your list: ");
//                printTasksList();
                printTodoEventDeadlineList();
            }
            op = sc.nextLine();
        }
        if (op.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

}




