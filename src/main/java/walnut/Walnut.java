package walnut;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a Walnut task manager and loads existing tasks from storage.
 *
 * <p>Any tasks successfully parsed from the stored data are added to
 * the task list.</p>
 */
public class Walnut {
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_FIND = "find";
    private static final String SEP_BY = "/by";
    private static final String SEP_FROM = "/from";
    private static final String SEP_TO = "/to";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private List<String> data;
    private String startupMessage;

    /**
     * Creates a Walnut task manager and initializes the user interface,
     * task list, storage, and task data.
     *
     * <p>Loads existing tasks from storage and adds successfully parsed
     * tasks to the task list.</p>
     */
    public Walnut() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage();
        data = new ArrayList<>();

        try {
            data = storage.load();
        } catch (IOException e) {
            startupMessage = ui.showStorageError();
        }

        for (String line : data) {
            Task task = Parser.parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    /**
     * Returns the message generated during application startup.
     *
     * @return the startup message, or {@code null} if no startup error occurred
     */
    public String getStartupMessage() {
        return startupMessage;
    }

    /**
     * Parses and validates a 1-based task index from the request tokens.
     *
     * @param request tokenized user request
     * @param tasks the current TaskList
     * @return zero-based index
     * @throws IllegalArgumentException with message "missing" or "invalid"
     */
    private int parseAndValidateIndex(String[] request, TaskList tasks) throws IllegalArgumentException {
        if (request.length < 2) {
            throw new IllegalArgumentException("missing");
        }
        try {
            int idx = Integer.parseInt(request[1]) - 1;
            if (idx < 0 || idx >= tasks.size()) {
                throw new IllegalArgumentException("invalid");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid");
        }
    }

    /**
     * Processes a user command and returns Walnut's response.
     *
     * <p>The command is parsed and executed based on its type. Commands may
     * add, remove, modify, list, or search for tasks.</p>
     *
     * @param input the command entered by the user
     * @return Walnut's response to the command
     */
    public String getResponse(String input) {
        // String input = ui.readCommand();
        String[] request = input.split(" ");
        if (request.length == 0 || request[0].isEmpty()) {
            return ui.showInvalidCommand();
        }

        Command command = Parser.parseCommand(request[0]);

        if (command == null) {
            return ui.showInvalidCommand();
        }

        switch (command) {
            case BYE: {
                return ui.showFarewell();
            }

            case LIST: {
                return ui.showTaskList(tasks);
            }

            case MARK: {
                if (tasks.isEmpty()) {
                    return ui.showEmptyTaskListMessage();
                }
                int index;
                try {
                    index = parseAndValidateIndex(request, tasks);
                } catch (IllegalArgumentException e) {
                    if ("missing".equals(e.getMessage())) {
                        return ui.showMissingTaskNumber();
                    } else {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                }
                Task task = tasks.get(index);
                task.markAsDone();
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskMarkedAsDone(task);
            }

            case UNMARK: {
                if (tasks.isEmpty()) {
                    return ui.showEmptyTaskListMessage();
                }
                int index;
                try {
                    index = parseAndValidateIndex(request, tasks);
                } catch (IllegalArgumentException e) {
                    if ("missing".equals(e.getMessage())) {
                        return ui.showMissingTaskNumber();
                    } else {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                }
                Task task = tasks.get(index);
                task.markAsNotDone();
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskMarkedAsNotDone(task);
            }

            case TODO: {
                String args = input.substring(CMD_TODO.length()).trim();
                if (args.isEmpty()) {
                    return ui.showEmptyDescription("todo");
                }
                Task task = new ToDo(args);
                tasks.add(task);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskAdded(task, tasks.size());
            }

            case DEADLINE: {
                String args = input.substring(CMD_DEADLINE.length()).trim();
                int byIndex = args.indexOf(SEP_BY);
                if (byIndex == -1) {
                    return ui.showInvalidDeadlineFormat();
                }
                String description = args.substring(0, byIndex).trim();
                if (description.isEmpty()) {
                    return ui.showEmptyDescription("deadline");
                }

                String by = args.substring(byIndex + SEP_BY.length()).trim();
                LocalDateTime formattedByDateTime;
                try {
                    formattedByDateTime = Parser.parseUserDateTime(by);
                } catch (DateTimeParseException e) {
                    return ui.showInvalidDateTime();
                }

                Task task = new Deadline(description, formattedByDateTime);
                tasks.add(task);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskAdded(task, tasks.size());
            }

            case EVENT: {
                String args = input.substring(CMD_EVENT.length()).trim();
                int fromIndex = args.indexOf(SEP_FROM);
                int toIndex = args.indexOf(SEP_TO);
                if (fromIndex == -1 || toIndex == -1) {
                    return ui.showInvalidEventFormat();
                }
                String description = args.substring(0, fromIndex).trim();
                if (description.isEmpty()) {
                    return ui.showEmptyDescription("event");
                }
                String from = args.substring(fromIndex + SEP_FROM.length(), toIndex).trim();
                LocalDateTime formattedFromDateTime;
                LocalDateTime formattedToDateTime;
                try {
                    formattedFromDateTime = Parser.parseUserDateTime(from);
                } catch (DateTimeParseException e) {
                    return ui.showInvalidDateTime();
                }

                String to = args.substring(toIndex + SEP_TO.length()).trim();
                try {
                    formattedToDateTime = Parser.parseUserDateTime(to);
                } catch (DateTimeParseException e) {
                    return ui.showInvalidDateTime();
                }

                Task task = new Event(description, formattedFromDateTime, formattedToDateTime);
                tasks.add(task);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskAdded(task, tasks.size());
            }

            case REMOVE: {
                if (tasks.isEmpty()) {
                    return ui.showEmptyTaskListMessage();
                }
                int index;
                try {
                    index = parseAndValidateIndex(request, tasks);
                } catch (IllegalArgumentException e) {
                    if ("missing".equals(e.getMessage())) {
                        return ui.showMissingTaskNumber();
                    } else {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                }
                Task task = tasks.get(index);
                tasks.remove(index);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskRemoved(task, tasks.size());
            }

            case FIND: {
                if (tasks.isEmpty()) {
                    return ui.showEmptyTaskListMessage();
                }
                String keyword = input.substring(CMD_FIND.length()).trim();
                if (keyword.isEmpty()) {
                    return ui.showEmptyKeyword();
                }
                ArrayList<Task> foundTasks = tasks.find(keyword);
                return ui.showFoundTasks(foundTasks);
            }

            default: {
                return ui.showInvalidCommand();
            }
        }
    }
}
