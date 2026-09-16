package walnut;

import java.util.ArrayList;

/**
 * Handles console input and output for Walnut.
 */
public class Ui {

    private static final String ADDED_TASK_TEMPLATE = "Added task: %s%nYou have %d %s in your list.";
    private static final String REMOVED_TASK_TEMPLATE = "Task removed:%n%s%nYou have %d %s in your list.";

    /**
     * Displays the application banner.
     *
     * @return the ASCII-art banner string
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
     *
     * @return the greeting message shown to users
     */
    public String showGreeting() {
        return "Hello! I'm Walnut.\n"
                + "What can I do for you?";
    }

    /**
     * Displays the farewell message.
     *
     * @return the farewell message shown when the application exits
     */
    public String showFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the supported task command formats.
     *
     * @return a message describing the expected task command format
     */
    public String showInvalidCommand() {
        return "Invalid task format. Type /help to see the available commands.";
    }

    /**
     * Displays all tasks in the specified list.
     *
     * @param tasks Task list to display.
     * @return a formatted string containing all tasks in the list
     */
    public String showTaskList(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    /**
     * Displays a message for an empty task list.
     *
     * @return a message indicating that the task list is empty
     */
    public String showEmptyTaskListMessage() {
        return "Your task list is empty. Please add a task first.\n";
    }

    /**
     * Displays an invalid task-number message with the valid upper bound.
     *
     * @param size Number of tasks in the list.
     * @return an error message that includes the valid task-number range
     */
    public String showInvalidTaskNumber(int size) {
        assert size >= 0 : "Task-list size cannot be negative";
        return "Invalid task number. Please enter a number between 1 and "
                + size + ".\n";
    }

    /**
     * Displays an invalid task-number message.
     *
     * @return an error message asking the user to enter a valid task number
     */
    public String showInvalidTaskNumber() {
        return "Invalid task number. Please enter a valid number.\n";
    }

    /**
     * Displays a message when a task number is missing.
     *
     * @return a prompt asking the user to specify the task number
     */
    public String showMissingTaskNumber() {
        return "Please specify the task number to mark.\n";
    }

    /**
     * Displays confirmation that a task was completed.
     *
     * @param task Task that was completed.
     * @return a confirmation message indicating the task was marked as done
     */
    public String showTaskMarkedAsDone(Task task) {
        return "Task marked as done!";
    }

    /**
     * Displays confirmation that a task was marked incomplete.
     *
     * @param task Task that was marked incomplete.
     * @return a confirmation message indicating the task was marked as not done
     */
    public String showTaskMarkedAsNotDone(Task task) {
        return "Task marked as not done!";
    }

    /**
     * Displays a message when a task description is empty.
     *
     * @param taskType Type of task with the empty description.
     * @return an error message indicating the description cannot be empty
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
     * @return a message confirming the added task and current task count
     */
    public String showTaskAdded(Task task, int numberOfTasks) {
        String plural = numberOfTasks == 1 ? "task" : "tasks";
        return String.format(ADDED_TASK_TEMPLATE, task, numberOfTasks, plural);
    }

    /**
     * Displays the required event command format.
     *
     * @return a message describing the correct event command format
     */
    public String showInvalidEventFormat() {
        return "Invalid event format. Please use the format: "
                + "event <description> /from <start time> /to <end time>\n";
    }

    /**
     * Displays the required deadline command format.
     *
     * @return a message describing the correct deadline command format
     */
    public String showInvalidDeadlineFormat() {
        return "Invalid deadline format. Please use the format: "
                + "deadline <description> /by <deadline>\n";
    }

    /**
     * Displays the required date and time format.
     *
     * @return a message describing the expected date/time format
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
     * @return a message confirming the removed task and remaining task count
     */
    public String showTaskRemoved(Task task, int remainingTasks) {
        String plural = remainingTasks == 1 ? "task" : "tasks";
        return String.format(REMOVED_TASK_TEMPLATE, task, remainingTasks, plural);
    }

    /**
     * Displays a storage loading error.
     *
     * @return the loading error message shown when saved tasks cannot be loaded
     */
    public String showStorageLoadError() {
        return "I could not load your saved tasks. "
                + "The application started with an empty list.";
    }

    /**
     * Displays a storage saving error.
     *
     * @return the saving error message shown when changes cannot be saved
     */
    public String showStorageSaveError() {
        return "I could not save that change. "
                + "Your previous task data has been preserved.";
    }

    /**
     * Displays a message when no search keyword is provided.
     *
     * @return a prompt asking the user to specify a search keyword
     */
    public String showEmptyKeyword() {
        return "Please specify a keyword to search for.";
    }

    /**
     * Displays the tasks that match the specified keyword.
     *
     * @param foundTasks Tasks that match the search keyword.
     * @return a formatted string containing the matching tasks
     */
    public String showFoundTasks(ArrayList<Task> foundTasks) {
        return "Here are the matching tasks in your list:\n" + TaskList.toString(foundTasks);
    }

    /**
     * Displays a message when the user enters no command.
     *
     * @return a message explaining that a command is required
     */
    public String showEmptyCommand() {
        return "Please enter a command. Type /help to see the available commands.";
    }

    /**
     * Displays a message when an event's end time is not after its start time.
     *
     * @return a message indicating the event end time must be later than the start
     */
    public String showInvalidEventTimeRange() {
        return "The event end time must be later than the start time.";
    }

    /**
     * Displays a message when a duplicate task is added.
     *
     * @return a message indicating the task already exists in the list
     */
    public String showDuplicateTask() {
        return "This task already exists in your task list.";
    }
}
