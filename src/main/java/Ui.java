import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void showBanner() {
        String banner = " _    _       _             _\n"
                + "| |  | | __ _| |_ __  _   _| |_\n"
                + "| |/\\| |/ _` | | '_ \\| | | | __|\n"
                + "|  /\\  | (_| | | | | | |_| | |_\n"
                + "|_/  \\_|\\__,_|_|_| |_|\\__,_|\\__|\n"
                + "____________________________________________________________\n";

        System.out.println(banner);
    }

    public void showGreeting() {
        String greeting =  "____________________________________________________________\n"
                + "Hello! I'm Walnut.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);
    }

    public void showFarewell() {
        String farewell =  "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(farewell);
    }

    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    public void showInvalidCommand() {
        System.out.println("Invalid task format. Please use the format: todo <description> "
                + "or event <description> /from <start time> /to <end time> "
                + "or deadline <description> /by <deadline>");
        showLine();
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Walnut: Here are the tasks in your list:");
        System.out.println(tasks.toString());
        showLine();
    }

    public void showEmptyTaskListMessage() {
        System.out.println("Your task list is empty. Please add a task first.\n");
        showLine();
    }

    public void showInvalidTaskNumber(int size) {
        System.out.println("Invalid task number. Please enter a number between 1 and "
                + size + ".\n");
        showLine();
    }

    public void showInvalidTaskNumber() {
        System.out.println("Invalid task number. Please enter a valid number.\n");
        showLine();
    }

    public void showMissingTaskNumber() {
        System.out.println("Please specify the task number to mark.\n");
        showLine();
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________\n"
                + "\nTask marked as done!\n");
        System.out.println(task.toString());
        showLine();
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("____________________________________________________________\n"
                + "\nTask marked as not done!\n");
        System.out.println(task);
        showLine();
    }

    public void showEmptyDescription(String taskType) {
        System.out.println("The description of a " + taskType
                + " cannot be empty.\n");
        showLine();
    }

    public void showTaskAdded(Task task, int numberOfTasks) {
        System.out.println("Walnut: Added task: "
                + task
                + "\n"
                + "You have " + numberOfTasks + " task[s] in your list."
                + "\n"
                + "____________________________________________________________\n");
    }

    public void showInvalidEventFormat() {
        System.out.println("Invalid event format. Please use the format: "
                + "event <description> /from <start time> /to <end time>\n");
        showLine();
    }

    public void showInvalidDeadlineFormat() {
        System.out.println("Invalid deadline format. Please use the format: "
                + "deadline <description> /by <deadline>\n");
        showLine();
    }

    public void showInvalidDateTime() {
        System.out.println("Invalid DateTime format. "
                + "Please use the format <YYYY-mm-dd HHmm>");
    }

    public void showTaskRemoved(Task task, int remainingTasks) {
        System.out.println("____________________________________________________________\n"
                + "\nTask removed!: \n");
        System.out.println(task);
        System.out.println("You have " + remainingTasks + " task[s] in your list.");
        showLine();
    }

    public void showStorageError() {
        System.out.println("Error loading tasks.");
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
}
