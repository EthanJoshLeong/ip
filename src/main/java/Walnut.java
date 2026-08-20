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
        while (exit != 1) {
            System.out.print("You: ");
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            String output = "Walnut: "
            + input
            + "\n"
            + "____________________________________________________________";
            System.out.println(output);   
        }
        System.out.println(farewell);
    }
}