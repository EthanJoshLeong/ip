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
        
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("You: ");
            String input = System.console().readLine();
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Walnut: Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________\n");
                continue;
            } else if (words[0].equals("mark")) {
                System.out.println("____________________________________________________________\n"
                + "Task marked as done!\n");
                Task task = tasks.get(Integer.parseInt(words[1]) - 1);
                task.markAsDone();
                System.out.println(task.toString());
                System.out.println("____________________________________________________________\n");
            } else if (words[0].equals("unmark")) {
                System.out.println("____________________________________________________________\n"
                + "Task marked as not done!\n");
                Task task = tasks.get(Integer.parseInt(words[1]) - 1);
                task.markAsNotDone();
                System.out.println(task.toString());
                System.out.println("____________________________________________________________\n");
            } else {
                String output = "Walnut: Added task: "
                + input
                + "\n"
                + "____________________________________________________________\n";
                System.out.println(output); 
                tasks.add(new Task(input));
            }
        }
        System.out.println(farewell);
    }
}