package duke;

import java.io.IOException;
import  java.util.Scanner;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;



import java.io.IOException;
import  java.util.Scanner;
import java.lang.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

/**
 * The Duke program is a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static Parser parser;

    /**
     * Initialises Duke.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        parser = new Parser(ui, taskList, storage);
    }

    /**
     * This is the main method which makes use of the classes declared
     * in the Duke constructor to initialise the program.
     * @param args Unused.
     * @return Nothing.
     * @throws IOException when there are input/output file operation issues while
     * creating and writing the program list to the text file.
     * @throws DukeException when errors specific to Duke are raised.
     */
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