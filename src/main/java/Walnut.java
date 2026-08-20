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
                System.out.println("____________________________________________________________\n"
                + "\nTask marked as done!\n");
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsDone();
                System.out.println(task.toString());
                System.out.println("\n____________________________________________________________\n");

            } else if (command.equals("unmark")) {
                System.out.println("____________________________________________________________\n"
                + "\nTask marked as not done!\n");
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsNotDone();
                System.out.println(task.toString());
                System.out.println("____________________________________________________________\n");

            } else if (command.equals("Todo")) {
                Task task = new ToDo(input);
                tasks.add(task);
                String output = "Walnut: Added task: "
                + task
                + "You have " + tasks.size() + " tasks in your list."
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output); 

            } else if (command.equals("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String description = input.substring(5, fromIndex).trim();
                String from = input.substring(fromIndex + 5, toIndex).trim();
                String to = input.substring(toIndex + 3).trim();
                Task task = new Events(description, from, to);
                tasks.add(task);
                String output = "Walnut: Added task: "
                + task
                + "\n"
                + "You have " + tasks.size() + " tasks in your list."
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output); 

            } else if (command.equals("deadline")) {
                int byIndex = input.indexOf("/by");
                String description = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 3).trim();
                Task task = new Deadlines(description, by);
                tasks.add(task);
                String output = "Walnut: Added task: "
                + task
                + "\n"
                + "You have " + tasks.size() + " tasks in your list."
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output); 

            } else {
                String output = "Walnut: Added task: "
                + input
                + "\n"
                + "You have " + tasks.size() + " tasks in your list."
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output); 
                tasks.add(new Task(input));
            }
        }
        System.out.println(farewell);
    }
}