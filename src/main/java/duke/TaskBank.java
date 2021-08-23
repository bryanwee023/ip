package duke;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Utility;

import java.util.ArrayList;
import java.util.function.Function;

public class TaskBank {

    private final ArrayList<Task> taskList;
    private final Storage storage;

    public TaskBank(String filepath) {
        this.storage = new Storage("data/duke.txt");
        this.taskList = storage.readFromDisk();
    }

    public void addTask(String formattedString, Function<String, ? extends Task> create) {
        Task e = create.apply(formattedString);
        this.taskList.add(e);
        Ui.print(String.format("added: %s", e));
        this.storage.writeToDisk(this.taskList);
    }

    public void  markTask(String input) {
        int taskId = -1;
        try {
            taskId = Utility.getIdFromString(input, "done ");
            Task t = this.taskList.get(taskId - 1);
            t.markAsDone();
            Ui.print("Cool, I've marked this duke.task as done\n" + t);
        } catch (IndexOutOfBoundsException e) {
            Ui.print(String.format("Oops, Task.Task #%d doesn't exist\n", taskId));
        }
        this.storage.writeToDisk(this.taskList);
    }

    public void deleteTask(String input) {
        int taskId = -1;
        try {
            taskId = Utility.getIdFromString(input, "delete ");
            Task t = this.taskList.get(taskId - 1);
            this.taskList.remove(taskId - 1);
            Ui.print("Okay, I've removed this duke.task\n" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Oops, Task.Task #%d doesn't exist\n", taskId));
        }
        this.storage.writeToDisk(this.taskList);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.taskList);
    }
}
