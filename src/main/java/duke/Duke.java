package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * The Duke program is a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 */
public class Duke {

    private static Ui ui;
    public static Storage storage;
    public static TaskList taskList;
    private static Parser parser;

    /**
     * Initialises Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(filePath, taskList);
    }

    /**
     * This is the main method which makes use of the classes declared
     * in the Duke constructor to initialise the program.
     * @param args Unused.
     * @throws IOException when there are input/output file operation issues while
     * creating and writing the program list to the text file.
     * @throws DukeException when errors specific to Duke are raised.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("duke.txt");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        File file= new File("duke.txt");
        if (!file.createNewFile()) {
            parser = new Parser(ui, storage.loadList("duke.txt", taskList));
        } else {
            parser = new Parser(ui, taskList);
        }
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        boolean dukeExecuting;
        do {
            dukeExecuting = parser.handleCommand(cmd);
            if (cmd.equals("bye")) {
                return;
            }
            cmd = sc.nextLine();
        } while(dukeExecuting);

    }
}