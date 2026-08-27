import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    public Event(String description, LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getEventStartTime() {
        return this.eventStartTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    public String getEventEndTime() {
        return this.eventEndTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.eventStartTime.format(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                + " to: "
                + this.eventEndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                + ")";
    }
}