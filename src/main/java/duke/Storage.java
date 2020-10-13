package duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage of items in list in a text file.
 */
public class Storage {

    public Storage(){}

    /**
     * Method to save the tasks in the list by reading and writing it in
     * the text file.
     * @param taskList TaskList instance containing the ArrayList of tasks.
     * @throws IOException If there are problems with writing input/output to
     * the text file.
     */
    public static void saveList(TaskList taskList){
        try {
            FileWriter initialWriter = new FileWriter("duke.txt");
            BufferedWriter myWriter = new BufferedWriter(initialWriter);
            myWriter.write("List");
            myWriter.newLine();
            for(int i=0; i<taskList.size(); i++) {
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

}