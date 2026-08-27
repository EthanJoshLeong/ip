import java.util.List;

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

    public static Task parseTask(String task) {
        String[] data = task.split(" \\| ");
        Task newTask;

        if (data[0].equals("T")) {
            newTask = new ToDo(data[2]);
        } else if (data[0].equals("D")) {
            newTask = new Deadlines(data[2], data[3]);
        } else if (data[0].equals("E")) {
            String[] dateTime = data[3].split("-");
            newTask = new Events(data[2], dateTime[0], dateTime[1]);
        } else {
            return null;
        }

        if (data[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }
}