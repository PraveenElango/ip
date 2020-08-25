import  java.util.Scanner;
import java.io.InputStream;
import java.lang.*;

public class Duke{
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;
    private static void addTask(String description){
        tasks[tasksCount] = new Task(description);
        tasksCount += 1;
    }
    private static void printTasksList(){
        int i =0;
        while(tasks[i]!= null){
            System.out.print((i+1)+".");
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
        while(!op.equals("bye")){
            if(!op.equals("list") && !op.startsWith("done")){
                System.out.println("added: " + op);
                addTask(op);
            }else if(op.startsWith("done")){
                System.out.println("Nice! I've marked this task as done:");
                String index_string = op.substring(5);
                int index = Integer.parseInt(index_string);
                tasks[index-1].markAsDone();
                System.out.print(tasks[index-1].getStatusIcon() + " ");
                System.out.println(tasks[index-1].getDesc() + " ");
            }else{
                System.out.println("Here are the tasks in your list: ");
                printTasksList();
            }
            op = sc.nextLine();
        }
        if(op.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
        }

    }
}



