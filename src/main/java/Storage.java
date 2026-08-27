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
            } else if (task instanceof Deadlines) {
                line = "D | " + (task.isDone() ? 1 : 0) +" | " +
                        task.getDescription() + " | " + ((Deadlines) task).getDeadline();
            } else if (task instanceof Events) {
                line = "E | " + (task.isDone() ? 1 : 0) +" | " +
                        task.getDescription() + " | " + ((Events) task).getEventStartTime() +
                        "-" + ((Events) task).getEventEndTime();
            }
            data.add(line);
        }
        Files.write(path, data);
    }
}
