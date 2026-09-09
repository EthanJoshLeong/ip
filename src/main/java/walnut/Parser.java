package walnut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts user input and stored task data into Walnut objects.
 */
public class Parser {

    // Centralized date-time formatters to avoid duplication and mismatch
    public static final DateTimeFormatter STORAGE_FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public static final DateTimeFormatter USER_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter DISPLAY_FMT = DateTimeFormatter.ofPattern("MMM d yyyy h a");

    /**
     * Returns the command represented by the specified command string.
     *
     * @param command Command string to parse.
     * @return Corresponding command, or {@code null} if the command is not recognized.
     */
    public static Command parseCommand(String command) {

        assert command != null && !command.isBlank()
                : "Command cannot be null or blank";
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

            case "find":
                return Command.FIND;

            default:
                return null;
        }
    }

    /**
     * Returns the task represented by the specified stored task record.
     *
     * @param task Stored task record to parse.
     * @return Parsed task, or {@code null} if the task type is not recognized.
     */
    public static Task parseTask(String task) {
        assert task != null && !task.isBlank()
                : "Stored task record cannot be null or blank";
        try {
            String[] data = task.split(" \\| ");
            assert data.length >= 3 : "Stored task record has too few fields";
            assert data[0].equals("T")
                    || data[0].equals("D")
                    || data[0].equals("E")
                    : "Unknown stored task type";
            assert data[1].equals("0") || data[1].equals("1")
                    : "Invalid completion status";
            Task newTask;
            switch (data[0]) {
                case "T":
                    newTask = new ToDo(data[2]);
                    break;
                case "D":
                    if (data.length < 4) {
                        return null;
                    }
                    newTask = new Deadline(data[2], Parser.parseDateTime(data[3]));
                    break;
                case "E":
                    if (data.length < 4) {
                        return null;
                    }
                    String[] dateTime = data[3].split("-");
                    if (dateTime.length < 2) {
                        return null;
                    }
                    newTask = new Event(data[2], Parser.parseDateTime(dateTime[0]), Parser.parseDateTime(dateTime[1]));
                    break;
                default:
                    return null;
            }

            if ("1".equals(data[1])) {
                newTask.markAsDone();
            }

            return newTask;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Returns the date and time represented by a stored date-time string.
     *
     * @param input Stored date-time string to parse.
     * @return Parsed date and time.
     */
    public static LocalDateTime parseDateTime(String input) {
        assert input != null && !input.isBlank()
                : "Stored date-time cannot be null or blank";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

        return LocalDateTime.parse(input, STORAGE_FMT);
    }

    /**
     * Returns the date and time represented by a user-entered date-time string.
     *
     * @param input User-entered date-time string to parse.
     * @return Parsed date and time.
     */
    public static LocalDateTime parseUserDateTime(String input) {
        assert input != null && !input.isBlank()
                : "User date-time cannot be null or blank";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        return LocalDateTime.parse(input, USER_FMT);
    }
}
