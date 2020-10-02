package duke;

import java.io.IOException;
import  java.util.Scanner;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;



public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static Parser parser;

    //declaration of Duke constructor
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        parser = new Parser(ui, taskList, storage);
    }

    //execution of Duke program
    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        File file= new File("duke.txt");
        if(!file.createNewFile()){
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        }

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        boolean dukeExecuting = true;
        do {
            dukeExecuting = duke.parser.handleCommand(cmd);
            if(cmd.equals("bye")) {
                return;
            }
            cmd = sc.nextLine();
        } while(dukeExecuting);

    }
}


