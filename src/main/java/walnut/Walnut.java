package walnut;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs the Walnut command-line task manager.
 */
public class Walnut {
    /**
     * Starts Walnut, loads saved tasks, and processes commands until exit.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        List<String> data = new ArrayList<>();

        ui.showBanner();
        ui.showGreeting();

        try {
            data = storage.load();
        } catch (IOException e) {
            ui.showStorageError();
        }

        for (String line : data) {
            Task task = Parser.parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        while (true) {
            String input = ui.readCommand();
            String[] request = input.split(" ");

            Command command = Parser.parseCommand(request[0]);

            if (command == null) {
                ui.showInvalidCommand();
                continue;
            }

            switch (command) {
                case BYE: {
                    ui.showFarewell();
                    return;
                }

                case LIST: {
                    ui.showTaskList(tasks);
                    break;
                }

                case MARK: {
                    if (tasks.isEmpty()) {
                        ui.showEmptyTaskListMessage();
                        continue;
                    }
                    try {
                        int index = Integer.parseInt(request[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showInvalidTaskNumber(tasks.size());
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNumber();
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.showMissingTaskNumber();
                        continue;
                    }
                    Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                    task.markAsDone();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskMarkedAsDone(task);
                    break;
                }

                case UNMARK: {
                    if (tasks.isEmpty()) {
                        ui.showEmptyTaskListMessage();
                        continue;
                    }
                    try {
                        int index = Integer.parseInt(request[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showInvalidTaskNumber(tasks.size());
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNumber();
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.showMissingTaskNumber();
                        continue;
                    }
                    Task task = tasks.get(Integer.parseInt(request[1]) - 1);
                    task.markAsNotDone();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskMarkedAsNotDone(task);
                    break;
                }

                case TODO: {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        ui.showEmptyDescription("todo");
                        continue;
                    }
                    Task task = new ToDo(description);
                    tasks.add(task);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskAdded(task, tasks.size());
                    break;
                }

                case DEADLINE: {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        ui.showInvalidDeadlineFormat();
                        continue;
                    }
                    String description = input.substring(9, byIndex).trim();
                    if (description.isEmpty()) {
                        ui.showEmptyDescription("deadline");
                        continue;
                    }

                    String by = input.substring(byIndex + 3).trim();
                    LocalDateTime formattedByDateTime;
                    try {
                        formattedByDateTime = Parser.parseUserDateTime(by);
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDateTime();
                        continue;
                    }

                    Task task = new Deadline(description, formattedByDateTime);
                    tasks.add(task);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskAdded(task, tasks.size());
                    break;
                }

                case EVENT: {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        ui.showInvalidEventFormat();
                        continue;
                    }
                    String description = input.substring(5, fromIndex).trim();
                    if (description.isEmpty()) {
                        ui.showEmptyDescription("event");
                        continue;
                    }
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    LocalDateTime formattedFromDateTime;
                    LocalDateTime formattedToDateTime;
                    try {
                        formattedFromDateTime = Parser.parseUserDateTime(from);
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDateTime();
                        continue;
                    }

                    String to = input.substring(toIndex + 3).trim();
                    try {
                        formattedToDateTime = Parser.parseUserDateTime(to);
                    } catch (DateTimeParseException e) {
                        ui.showInvalidDateTime();
                        continue;
                    }

                    Task task = new Event(description, formattedFromDateTime, formattedToDateTime);
                    tasks.add(task);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskAdded(task, tasks.size());
                    break;
                }

                case REMOVE: {
                    if (tasks.isEmpty()) {
                        ui.showEmptyTaskListMessage();
                        continue;
                    }
                    try {
                        int index = Integer.parseInt(request[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            ui.showInvalidTaskNumber(tasks.size());
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNumber();
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.showMissingTaskNumber();
                        continue;
                    }
                    int index = Integer.parseInt(request[1]) - 1;
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        ui.showStorageError();
                    }
                    ui.showTaskRemoved(task, tasks.size());
                    break;
                }

                case FIND: {
                    if (tasks.isEmpty()) {
                        ui.showEmptyTaskListMessage();
                        continue;
                    }
                    String keyword = input.substring(5).trim();
                    if (keyword.isEmpty()) {
                        ui.showEmptyKeyword();
                        continue;
                    }
                    ArrayList<Task> foundTasks = tasks.find(keyword);
                    ui.showFoundTasks(foundTasks);
                    break;
                }

                default: {
                    ui.showInvalidCommand();
                    break;
                }
            }
        }
    }
}
