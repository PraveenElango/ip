package duke;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Storage of items in list in a text file.
 */
public class Storage {

    private static String filePath;
    private static TaskList taskList;

    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * Method to save the tasks in the list by reading and writing it in
     * the text file.
     *
     * @param taskList TaskList instance containing the ArrayList of tasks.
     * @throws IOException If there are problems with writing input/output to
     *                     the text file.
     */
    public static void saveList(TaskList taskList) {
        try {
            FileWriter initialWriter = new FileWriter("duke.txt");
            BufferedWriter myWriter = new BufferedWriter(initialWriter);
            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write(taskList.getTask(i).typeChar() + " ");
                myWriter.write(taskList.getTask(i).getStatusIcon() + " ");
                if (taskList.getTask(i) instanceof Deadline || taskList.getTask(i) instanceof Events) {
                    myWriter.write(taskList.getTask(i).getDesc() + " ");
                    myWriter.write("(" + taskList.getTask(i).getBy() + ") ");
                } else {
                    myWriter.write(taskList.getTask(i).getDesc() + " ");
                }
                myWriter.newLine();
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static TaskList loadList(String filePath, TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner reader = new Scanner(file);
        if (!reader.hasNext()) {
            return taskList;
        }
        while (reader.hasNext()) {
            taskList.addTask(parseListLine(reader.nextLine()));
        }
        return taskList;
    }

    private static Task parseListLine(String taskLine) {
        String type = Character.toString(taskLine.charAt(1));
        String description;
        String by = "";

        String isDone = Character.toString(taskLine.charAt(4));

        int startBracketIndex = taskLine.indexOf("(");
        int closeBracketIndex = taskLine.lastIndexOf(")");

        if (startBracketIndex != -1) {
            description = taskLine.substring(6, startBracketIndex).trim();
            by = taskLine.substring(startBracketIndex + 1, closeBracketIndex).trim();
        } else {
            description = taskLine.substring(6).trim();
        }

        if (type.equals("T")) {
            Todo todo = new Todo(description);
            if (isDone.equals("\u2713")) {
                todo.markAsDone();
            }
            return todo;
        } else if (type.equals("D")) {
            Deadline deadline = new Deadline(description, by);
            if (isDone.equals("\u2713")) {
                deadline.markAsDone();
            }
            return deadline;
        } else {
            Events event = new Events(description, by);
            if (isDone.equals("\u2713")) {
                event.markAsDone();
            }
            return event;
        }

    }

}