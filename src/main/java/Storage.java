import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    Path path = Paths.get("./data/Walnut.txt");
    Path dir = Paths.get("./data/");

    public List<String> load() throws IOException {
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        List<String> data;
        data = Files.readAllLines(path);
        return data;
    }

    public void save(TaskList tasks) throws IOException {
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        List<String> data = new ArrayList<>();

        for (Task task : tasks) {
            String line = "";
            if (task instanceof ToDo) {
                line = "T | " + (task.isDone() ? 1 : 0) +" | " +
                        task.getDescription();
            } else if (task instanceof Deadline) {
                line = "D | " + (task.isDone() ? 1 : 0) +" | " +
                        task.getDescription() + " | " + ((Deadline) task).getDeadline();
            } else if (task instanceof Event) {
                line = "E | " + (task.isDone() ? 1 : 0) +" | " +
                        task.getDescription() + " | " + ((Event) task).getEventStartTime() +
                        "-" + ((Event) task).getEventEndTime();
            }
            data.add(line);
        }
        Files.write(path, data);
    }
}
