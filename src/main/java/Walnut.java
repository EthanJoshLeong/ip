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
        int exit = 0;
        ArrayList<String> tasks = new ArrayList<>();
        while (exit != 1) {
            System.out.print("You: ");
            String input = System.console().readLine();
            if (input.equals("list")) {
                System.out.println("Walnut: Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
                continue;
            } else {
                tasks.add(input);
            }
            if (input.equals("bye")) {
                break;
            }
            String output = "Walnut: Added task: "
            + input
            + "\n"
            + "____________________________________________________________\n";
            System.out.println(output);   
        }
        System.out.println(farewell);
    }
}