package walnut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command parseCommand(String command) {

        switch (command.toLowerCase()) {
        case "todo":
            return Command.TODO;

        case "deadline":
            return Command.DEADLINE;

        case "event":
            return Command.EVENT;

        case "mark":
            return Command.MARK;

        case "unmark":
            return Command.UNMARK;

        case "list":
            return Command.LIST;

        case "bye":
            return Command.BYE;

        case "remove":
            return Command.REMOVE;

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
            newTask = new Deadline(data[2], Parser.parseDateTime(data[3]));
        } else if (data[0].equals("E")) {
            String[] dateTime = data[3].split("-");
            newTask = new Event(data[2], Parser.parseDateTime(dateTime[0]), Parser.parseDateTime(dateTime[1]));
        } else {
            return null;
        }

        if (data[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        return LocalDateTime.parse(input, formatter);
    }

    public static LocalDateTime parseUserDateTime(String input) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        return LocalDateTime.parse(input, formatter);
    }
}