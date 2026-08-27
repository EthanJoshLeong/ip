import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                + ")";
    }
}