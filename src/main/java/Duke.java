import  java.util.Scanner;
public class Duke{
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        while (!op.equals("bye")) {
            System.out.println(op);
            op = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


