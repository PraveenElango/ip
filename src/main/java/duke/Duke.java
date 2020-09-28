package duke;


import  java.util.Scanner;
import java.io.InputStream;
import java.lang.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;


public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int tasksCount = 0;

    private static void addTask(String description) {
        tasks.add(new Task(description));
//        tasks[tasksCount] = new Task(description);
        System.out.print(tasks.get(tasksCount).typeChar() + " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        tasksCount += 1;
    }

    private static void addTodo(String description) {
        tasks.add(new Todo(description));
        System.out.print(tasks.get(tasksCount).typeChar() + " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        tasksCount += 1;
    }

    private static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.print(tasks.get(tasksCount).typeChar() + " ");
        System.out.print(tasks.get(tasksCount).getStatusIcon() + " ");
        System.out.println(tasks.get(tasksCount).getDesc() + " ");
        System.out.println("(" + tasks.get(tasksCount).getBy() + ") ");
        tasksCount += 1;
    }

    private static void addEvent(String description, String by) {
        tasks.add(new Events(description, by));
        System.out.print(tasks.get(tasksCount).typeChar() + " ");
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

    private static void printTodoEventDeadlineList() {
        int i = 0;
        while (i < tasks.size()) {
            System.out.print((i + 1) + ".");
            System.out.print(tasks.get(i).typeChar() + " ");
            System.out.print(tasks.get(i).getStatusIcon() + " ");

            if (tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Events) {
                System.out.print(tasks.get(i).getDesc() + " ");
                System.out.println("(" + tasks.get(i).getBy() + ") ");
            }else{
                System.out.println(tasks.get(i).getDesc() + " ");
            }
            i++;
        }
    }

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
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index-1).markAsDone();
            System.out.print(tasks.get(index-1).getStatusIcon() + " ");
            System.out.println(tasks.get(index-1).getDesc() + " ");
        }catch(IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please check again!");
        }
    }

    private static void handleTodo(String args){
        System.out.println("Got it. I've added this task:");
        addTodo(args);
        System.out.println("Now you have " + tasksCount + " tasks in the list");
    }

    private static void handleDeadline(String desc, String by){
        System.out.println("Got it. I've added this task:");
        addDeadline(desc,by);
        System.out.println("Now you have " + tasksCount + " tasks in the list");
    }

    private static void handleEvent(String desc, String by){
        System.out.println("Got it. I've added this task:");
        addEvent(desc,by);
        System.out.println("Now you have " + tasksCount + " tasks in the list");
    }

    private static void handleDelete(String args){
        int i = Integer.parseInt(args);
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
    }

    private static void handleBye(){
        try {
            FileWriter initialWriter = new FileWriter("data/duke.txt");
            BufferedWriter myWriter = new BufferedWriter(initialWriter);
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
        System.out.println("");
    }


    private static boolean handleCommand(String cmd) throws DukeException {
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
            printTodoEventDeadlineList();
        }else if(cmd.startsWith("delete")){
            handleDelete(cmdArgs);
        }else if(cmd.equals("bye")){
            handleBye();
            return false;
        }else{
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }


    public static void main(String[] args) throws FileNotFoundException, DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        PrintWriter writer = new PrintWriter("data/duke.txt");
        writer.print("");
        writer.close();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        boolean dukeExecuting = true;
        do{
            dukeExecuting = handleCommand(cmd);
            if(cmd.equals("bye")){
                return;
            }
            cmd = sc.nextLine();
        }while(dukeExecuting);

    }
}


