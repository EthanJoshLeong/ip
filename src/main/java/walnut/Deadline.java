package walnut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specified date and time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a deadline task with the specified description and deadline.
     */
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