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
                try {
                    int index = Integer.parseInt(request[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                } catch (NumberFormatException e) {
                    return ui.showInvalidTaskNumber();
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ui.showMissingTaskNumber();
                }
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsDone();
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.showStorageError();
                }
                return ui.showTaskMarkedAsDone(task);
            }

            case UNMARK: {
                if (tasks.isEmpty()) {
                    return ui.showEmptyTaskListMessage();
                }
                try {
                    int index = Integer.parseInt(request[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                } catch (NumberFormatException e) {
                    return ui.showInvalidTaskNumber();
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ui.showMissingTaskNumber();
                }
                Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                task.markAsNotDone();
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskMarkedAsNotDone(task);
            }

            case TODO: {
                String description = input.substring(4).trim();
                if (description.isEmpty()) {
                    return ui.showEmptyDescription("todo");
                }
                Task task = new ToDo(description);
                tasks.add(task);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return ui.showStorageError();
                }
                return ui.showTaskAdded(task, tasks.size());
            }

            case DEADLINE: {
                int byIndex = input.indexOf("/by");
                if (byIndex == -1) {
                    return ui.showInvalidDeadlineFormat();
                }
                String description = input.substring(9, byIndex).trim();
                if (description.isEmpty()) {
                    return ui.showEmptyDescription("deadline");
                }

                String by = input.substring(byIndex + 3).trim();
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
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                if (fromIndex == -1 || toIndex == -1) {
                    return ui.showInvalidEventFormat();
                }
                String description = input.substring(5, fromIndex).trim();
                if (description.isEmpty()) {
                    return ui.showEmptyDescription("event");
                }
                String from = input.substring(fromIndex + 5, toIndex).trim();
                LocalDateTime formattedFromDateTime;
                LocalDateTime formattedToDateTime;
                try {
                    formattedFromDateTime = Parser.parseUserDateTime(from);
                } catch (DateTimeParseException e) {
                    return ui.showInvalidDateTime();
                }

                String to = input.substring(toIndex + 3).trim();
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
                try {
                    int index = Integer.parseInt(request[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        return ui.showInvalidTaskNumber(tasks.size());
                    }
                } catch (NumberFormatException e) {
                    return ui.showInvalidTaskNumber();
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ui.showMissingTaskNumber();
                }
                int index = Integer.parseInt(request[1]) - 1;
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
                String keyword = input.substring(5).trim();
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
