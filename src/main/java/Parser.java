public class Parser {

    public static Commands parseCommand(String command) {

        switch (command.toLowerCase()) {
        case "todo":
            return Commands.TODO;

        case "deadline":
            return Commands.DEADLINE;

        case "event":
            return Commands.EVENT;

        case "mark":
            return Commands.MARK;

        case "unmark":
            return Commands.UNMARK;

        case "list":
            return Commands.LIST;

        case "bye":
            return Commands.BYE;

        case "remove":
            return Commands.REMOVE;

        default:
            return null;
        }
    }
}