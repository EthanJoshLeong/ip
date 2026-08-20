import java.util.ArrayList;

public class Walnut {
    public static void main(String[] args) {
        String banner = " _    _       _             _\n"
            + "| |  | | __ _| |_ __  _   _| |_\n"
            + "| |/\\| |/ _` | | '_ \\| | | | __|\n"
            + "|  /\\  | (_| | | | | | |_| | |_\n"
            + "|_/  \\_|\\__,_|_|_| |_|\\__,_|\\__|\n"
            + "____________________________________________________________\n";
        String greeting = "____________________________________________________________\n"
            + "Hello! I'm Walnut.\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n";
        String farewell = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________";
        System.out.println(banner);
        System.out.println(greeting);
        
        TaskList tasks = new TaskList();

        while (true) {
            System.out.print("You: ");
            String input = System.console().readLine();
            String[] request = input.split(" ");
            String command = request[0];

            if (input.equals("bye")) {
                break;
                
            } else if (input.equals("list")) {
                System.out.println("Walnut: Here are the tasks in your list:");
                System.out.println(tasks.toString());
                System.out.println("____________________________________________________________\n");
                continue;

            } else if (command.equals("mark")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your task list is empty. Please add a task first.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                try {
                    int index = Integer.parseInt(request[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        System.out.println("Invalid task number. Please enter a number between 1 and " + tasks.size() + ".\n");
                        System.out.println("____________________________________________________________\n");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number. Please enter a valid number.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please specify the task number to mark.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                System.out.println("____________________________________________________________\n"
                + "\nTask marked as done!\n");
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsDone();
                System.out.println(task.toString());
                System.out.println("\n____________________________________________________________\n");
                

            } else if (command.equals("unmark")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your task list is empty. Please add a task first.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                try {
                    int index = Integer.parseInt(request[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        System.out.println("Invalid task number. Please enter a number between 1 and " + tasks.size() + ".\n");
                        System.out.println("____________________________________________________________\n");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number. Please enter a valid number.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please specify the task number to mark.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                System.out.println("____________________________________________________________\n"
                + "\nTask marked as not done!\n");
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsNotDone();
                System.out.println(task.toString());
                System.out.println("____________________________________________________________\n");
                

            } else if (command.equals("todo")) {
                String description = input.substring(4).trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a todo cannot be empty.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                Task task = new ToDo(description);
                tasks.add(task);
                System.out.println(tasks.addTaskToString(task));

            } else if (command.equals("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                if (fromIndex == -1 || toIndex == -1) {
                    System.out.println("Invalid event format. Please use the format: event <description> /from <start time> /to <end time>\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                String description = input.substring(5, fromIndex).trim();
                if (description.isEmpty()) {
                    System.out.println("The description of an event cannot be empty.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                String from = input.substring(fromIndex + 5, toIndex).trim();
                String to = input.substring(toIndex + 3).trim();
                Task task = new Events(description, from, to);
                tasks.add(task);
                System.out.println(tasks.addTaskToString(task));

            } else if (command.equals("deadline")) {
                int byIndex = input.indexOf("/by");
                if (byIndex == -1) {
                    System.out.println("Invalid deadline format. Please use the format: deadline <description> /by <deadline>\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                String description = input.substring(9, byIndex).trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a deadline cannot be empty.\n");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                String by = input.substring(byIndex + 3).trim();
                Task task = new Deadlines(description, by);
                tasks.add(task);
                System.out.println(tasks.addTaskToString(task)); 

            } else {
                String output = "Invalid task format. Please use the format: todo <description> or event <description> /from <start time> /to <end time> or deadline <description> /by <deadline>"
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output);
            }
        }
        System.out.println(farewell);
    }
}