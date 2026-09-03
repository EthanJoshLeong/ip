package walnut;

import java.util.ArrayList;

/**
 * Handles console input and output for Walnut.
 */
public class Ui {

    /**
     * Displays the application banner.
     */
    public String showBanner() {
        return " _    _       _             _\n"
                + "| |  | | __ _| |_ __  _   _| |_\n"
                + "| |/\\| |/ _` | | '_ \\| | | | __|\n"
                + "|  /\\  | (_| | | | | | |_| | |_\n"
                + "|_/  \\_|\\__,_|_|_| |_|\\__,_|\\__|\n";
    }

    /**
     * Displays the welcome message.
     */
    public String showGreeting() {
        return "Hello! I'm Walnut.\n"
                + "What can I do for you?\n";
    }

    /**
     * Displays the farewell message.
     */
    public String showFarewell() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays the supported task command formats.
     */
    public String showInvalidCommand() {
        return "Invalid task format. Please use the format: todo <description> "
                + "or event <description> /from <start time> /to <end time> "
                + "or deadline <description> /by <deadline>";
    }

    /**
     * Displays all tasks in the specified list.
     *
     * @param tasks Task list to display.
     */
    public String showTaskList(TaskList tasks) {
        return "Walnut: Here are the tasks in your list:\n" + tasks.toString();
    }

    /**
     * Displays a message for an empty task list.
     */
    public String showEmptyTaskListMessage() {
        return "Your task list is empty. Please add a task first.\n";
    }

    /**
     * Displays an invalid task-number message with the valid upper bound.
     *
     * @param size Number of tasks in the list.
     */
    public String showInvalidTaskNumber(int size) {
        return "Invalid task number. Please enter a number between 1 and "
                + size + ".\n";
    }

    /**
     * Displays an invalid task-number message.
     */
    public String showInvalidTaskNumber() {
        return "Invalid task number. Please enter a valid number.\n";
    }

    /**
     * Displays a message when a task number is missing.
     */
    public String showMissingTaskNumber() {
        return "Please specify the task number to mark.\n";
    }

    /**
     * Displays confirmation that a task was completed.
     *
     * @param task Task that was completed.
     */
    public String showTaskMarkedAsDone(Task task) {
        return "Walnut: Task marked as done!";
    }

    /**
     * Displays confirmation that a task was marked incomplete.
     *
     * @param task Task that was marked incomplete.
     */
    public String showTaskMarkedAsNotDone(Task task) {
        return "Walnut: Task marked as not done!";
    }

    /**
     * Displays a message when a task description is empty.
     *
     * @param taskType Type of task with the empty description.
     */
    public String showEmptyDescription(String taskType) {
        return "The description of a " + taskType
                + " cannot be empty.";
    }

    /**
     * Displays confirmation that a task was added.
     *
     * @param task Task that was added.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public String showTaskAdded(Task task, int numberOfTasks) {
        return "Walnut: Added task: " + task
                + "\nYou have " + numberOfTasks + " task[s] in your list.";
    }

    /**
     * Displays the required event command format.
     */
    public String showInvalidEventFormat() {
        return "Invalid event format. Please use the format: "
                + "event <description> /from <start time> /to <end time>\n";
    }

    /**
     * Displays the required deadline command format.
     */
    public String showInvalidDeadlineFormat() {
        return "Invalid deadline format. Please use the format: "
                + "deadline <description> /by <deadline>\n";
    }

    /**
     * Displays the required date and time format.
     */
    public String showInvalidDateTime() {
        return "Invalid DateTime format. "
                + "Please use the format <YYYY-mm-dd HHmm>";
    }

    /**
     * Displays confirmation that a task was removed.
     *
     * @param task Task that was removed.
     * @param remainingTasks Number of tasks remaining in the list.
     */
    public String showTaskRemoved(Task task, int remainingTasks) {
        return "Walnut: Task removed!: \n" + task
                + "\nYou have " + remainingTasks + " task[s] in your list.";
    }

    /**
     * Displays a storage error message.
     */
    public String showStorageError() {
        return "Error loading tasks.";
    }

    /**
     * Displays a message when no search keyword is provided.
     */
    public String showEmptyKeyword() {
        return "Please specify a keyword to search for.";
    }

    /**
     * Displays the tasks that match the specified keyword.
     *
     * @param foundTasks Tasks that match the search keyword.
     */
    public String showFoundTasks(ArrayList<Task> foundTasks) {
        return "Walnut: Here are the matching tasks in your list:\n" + TaskList.toString(foundTasks);
    }
}
