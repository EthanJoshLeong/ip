package walnut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public String addTaskToString(Task task) {
        return "walnut.Walnut: Added task: "
                + task.toString()
                + "\n"
                + "You have " + tasks.size() + " task[s] in your list."
                + "\n"
                + "____________________________________________________________\n";
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns all tasks whose descriptions contain the specified keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     * @return Tasks whose descriptions contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a formatted string containing the specified tasks.
     *
     * @param tasks Tasks to format.
     * @return Formatted representation of the tasks.
     */
    public static String toString(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        sb.append("You have " + tasks.size() + " tasks in your list.");
        return sb.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public String toString() {
        return toString(this.tasks);
    }
}