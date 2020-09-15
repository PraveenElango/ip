
import  java.util.Scanner;
import java.io.InputStream;
import java.lang.*;

import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.io.BufferedWriter;




public class Duke{
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int tasksCount = 0;
    private static void addTask(String description){
        tasks.add(new Task(description));
//        tasks[tasksCount] = new Task(description);
        System.out.print(tasks.get(tasksCount).typeChar()+ " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        tasksCount += 1;
    }
    private static void addTodo(String description){
        tasks.add(new Todo(description));
        System.out.print(tasks.get(tasksCount).typeChar()+ " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        tasksCount += 1;
    }
    private static void addDeadline(String description, String by){
        tasks.add(new Deadline(description, by));
        System.out.print(tasks.get(tasksCount).typeChar()+ " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        System.out.println("(" + tasks.get(tasksCount).getBy() + ") ");
        tasksCount += 1;
    }
    private static void addEvent(String description, String by){
        tasks.add(new Events(description, by));
        System.out.print(tasks.get(tasksCount).typeChar()+ " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        System.out.println("(" + tasks.get(tasksCount).getBy() + ") ");
        tasksCount += 1;
    }
    private static void printTasksList() {
        int i = 0;
        while (tasks.get(i) != null) {
            System.out.print((i + 1) + ".");
            System.out.print(tasks.get(i).getStatusIcon() + " ");
            System.out.println(tasks.get(i).getDesc() + " ");
            i++;
        }
    }
    private static void printTodoEventDeadlineList(){
        int i = 0;
        while (i<tasks.size()) {
            System.out.print((i + 1) + ".");
            System.out.print(tasks.get(i).typeChar()+ " ");
            System.out.print(tasks.get(i).getStatusIcon() + " ");

            if(tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Events){
                System.out.print(tasks.get(i).getDesc() + " ");
                System.out.println("("+ tasks.get(i).getBy() + ") ");
            }else{
                System.out.println(tasks.get(i).getDesc() + " ");
            }
//            System.out.println(tasks.get(i).getDesc() + " ");
            i++;
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
            PrintWriter writer = new PrintWriter("data/duke.txt");
            writer.print("");
            writer.close();
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            while (!input.equals("bye")) {
//                if (!input.equals("list") && !input.startsWith("done") && !input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
//                    System.out.println("added: " + input);
//                    addTask(input);
//                } else
                    if (input.startsWith("done")) {
                    //if they input done without number
                    if (input.length() == 4) {
                        System.out.println("Please specify index of task!");
                        input = sc.nextLine();
                    }
                    String index_string = input.substring(5);
                    int index = Integer.parseInt(index_string);
                    if (tasks.get(index-1) == null || index < 0 || index > 99) {
                        System.out.println("Out of bounds! Try again!");
                        input = sc.nextLine();
                        continue;
                    }
                    if (tasks.get(index-1).isDone()) {
                        System.out.println("Already done!");
                        input = sc.nextLine();
                        continue;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    tasks.get(index-1).markAsDone();
                    System.out.print(tasks.get(index-1).getStatusIcon() + " ");
                    System.out.println(tasks.get(index-1).getDesc() + " ");
                } else if (input.startsWith("todo")) {
                    System.out.println("Got it. I've added this task:");
                    addTodo(input.substring(5));
                    System.out.println("Now you have " + tasksCount + " tasks in the list");
                } else if (input.startsWith("deadline")) {
                    System.out.println("Got it. I've added this task:");
                    int slashIndex = input.indexOf("/");
                    addDeadline(input.substring(9, slashIndex - 1), input.substring(slashIndex + 1));
                    System.out.println("Now you have " + tasksCount + " tasks in the list");
                } else if (input.startsWith("event")) {
                    System.out.println("Got it. I've added this task:");
                    int slashIndex = input.indexOf("/");
                    addEvent(input.substring(6, slashIndex - 1), input.substring(slashIndex + 1));
                    System.out.println("Now you have " + tasksCount + " tasks in the list");
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
//                printTasksList();
                    printTodoEventDeadlineList();
                } else if(input.startsWith("delete")){
                    String index_string = input.substring(7);
                    int i = Integer.parseInt(index_string);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.print(tasks.get(i-1).typeChar()+ " ");
                    System.out.print(tasks.get(i-1).getStatusIcon() + " ");
                    if(tasks.get(i-1) instanceof Deadline || tasks.get(i-1) instanceof Events){
                        System.out.print(tasks.get(i-1).getDesc() + " ");
                        System.out.println("("+ tasks.get(i-1).getBy() + ") ");
                    }else{
                        System.out.println(tasks.get(i-1).getDesc() + " ");
                    }
                    tasks.remove(i-1);
                    tasksCount-=1;
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                input = sc.nextLine();
                //useless comment
                //useless comment2
            }
            if(input.equals("bye")) {
                try {
                    FileWriter InitialWriter = new FileWriter("data/duke.txt");
                    BufferedWriter myWriter = new BufferedWriter(InitialWriter);
                    myWriter.write("List");
                    myWriter.newLine();
                    System.lineSeparator();
                    for(int i=0; i<tasks.size(); i++) {
                        myWriter.write(tasks.get(i).typeChar() + " ");
                        myWriter.write(tasks.get(i).getStatusIcon() + " ");
                        if (tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Events) {
                            myWriter.write(tasks.get(i).getDesc() + " ");
                            myWriter.write("(" + tasks.get(i).getBy() + ") ");
                        } else {
                            myWriter.write(tasks.get(i).getDesc() + " ");
                        }
                        myWriter.newLine();
                    }
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println("Bye. Hope to see you again soon!");

            }
        }
    }





