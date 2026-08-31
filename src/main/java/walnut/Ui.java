package walnut;

import java.util.Scanner;

/**
 * Handles console input and output for Walnut.
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the application banner.
     */
    public void showBanner() {
        String banner = " _    _       _             _\n"
                + "| |  | | __ _| |_ __  _   _| |_\n"
                + "| |/\\| |/ _` | | '_ \\| | | | __|\n"
                + "|  /\\  | (_| | | | | | |_| | |_\n"
                + "|_/  \\_|\\__,_|_|_| |_|\\__,_|\\__|\n"
                + "____________________________________________________________\n";

        System.out.println(banner);
    }

    /**
     * Displays the welcome message.
     */
    public void showGreeting() {
        String greeting =  "____________________________________________________________\n"
                + "Hello! I'm walnut.Walnut.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);
    }

    /**
     * Displays the farewell message.
     */
    public void showFarewell() {
        String farewell =  "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(farewell);
    }

    /**
     * Returns one command line read from the console.
     *
     * @return Command line entered by the user.
     */
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    /**
     * Displays the supported task command formats.
     */
    public void showInvalidCommand() {
        System.out.println("Invalid task format. Please use the format: todo <description> "
                + "or event <description> /from <start time> /to <end time> "
                + "or deadline <description> /by <deadline>");
        showLine();
    }

    /**
     * Displays all tasks in the specified list.
     *
     * @param tasks Task list to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("walnut.Walnut: Here are the tasks in your list:");
        System.out.println(tasks.toString());
        showLine();
    }

    /**
     * Displays a message for an empty task list.
     */
    public void showEmptyTaskListMessage() {
        System.out.println("Your task list is empty. Please add a task first.\n");
        showLine();
    }

    /**
     * Displays an invalid task-number message with the valid upper bound.
     *
     * @param size Number of tasks in the list.
     */
    public void showInvalidTaskNumber(int size) {
        System.out.println("Invalid task number. Please enter a number between 1 and "
                + size + ".\n");
        showLine();
    }

    /**
     * Displays an invalid task-number message.
     */
    public void showInvalidTaskNumber() {
        System.out.println("Invalid task number. Please enter a valid number.\n");
        showLine();
    }

    /**
     * Displays a message when a task number is missing.
     */
    public void showMissingTaskNumber() {
        System.out.println("Please specify the task number to mark.\n");
        showLine();
    }

    /**
     * Displays confirmation that a task was completed.
     *
     * @param task Task that was completed.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________\n"
                + "\nwalnut.Task marked as done!\n");
        System.out.println(task.toString());
        showLine();
    }

    /**
     * Displays confirmation that a task was marked incomplete.
     *
     * @param task Task that was marked incomplete.
     */
    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("____________________________________________________________\n"
                + "\nwalnut.Task marked as not done!\n");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays a message when a task description is empty.
     *
     * @param taskType Type of task with the empty description.
     */
    public void showEmptyDescription(String taskType) {
        System.out.println("The description of a " + taskType
                + " cannot be empty.\n");
        showLine();
    }

    /**
     * Displays confirmation that a task was added.
     *
     * @param task Task that was added.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public void showTaskAdded(Task task, int numberOfTasks) {
        System.out.println("walnut.Walnut: Added task: "
                + task
                + "\n"
                + "You have " + numberOfTasks + " task[s] in your list."
                + "\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays the required event command format.
     */
    public void showInvalidEventFormat() {
        System.out.println("Invalid event format. Please use the format: "
                + "event <description> /from <start time> /to <end time>\n");
        showLine();
    }

    /**
     * Displays the required deadline command format.
     */
    public void showInvalidDeadlineFormat() {
        System.out.println("Invalid deadline format. Please use the format: "
                + "deadline <description> /by <deadline>\n");
        showLine();
    }

    /**
     * Displays the required date and time format.
     */
    public void showInvalidDateTime() {
        System.out.println("Invalid DateTime format. "
                + "Please use the format <YYYY-mm-dd HHmm>");
    }

    /**
     * Displays confirmation that a task was removed.
     *
     * @param task Task that was removed.
     * @param remainingTasks Number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int remainingTasks) {
        System.out.println("____________________________________________________________\n"
                + "\nwalnut.Task removed!: \n");
        System.out.println(task);
        System.out.println("You have " + remainingTasks + " task[s] in your list.");
        showLine();
    }

    /**
     * Displays a storage error message.
     */
    public void showStorageError() {
        System.out.println("Error loading tasks.");
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
}
