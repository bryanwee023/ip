package duke.util.sort;

import java.util.Comparator;

import duke.task.Task;

public class NameSorter implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDescription().compareTo(t2.getDescription());
    }

}
