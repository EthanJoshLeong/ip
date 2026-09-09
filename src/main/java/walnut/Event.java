package walnut;

import java.time.LocalDateTime;

/**
 * Represents a task that takes place during a specified time interval.
 */
public class Event extends Task {
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;

    /**
     * Creates an event with the specified description and time interval.
     */
    public Event(String description, LocalDateTime eventStartTime,
                 LocalDateTime eventEndTime) {
        super(description);
        assert eventStartTime != null : "Event start time cannot be null";
        assert eventEndTime != null : "Event end time cannot be null";
        assert !eventEndTime.isBefore(eventStartTime)
                : "Event end time cannot be before start time";
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getEventStartTime() {
        return this.eventStartTime.format(Parser.STORAGE_FMT);
    }

    public String getEventEndTime() {
        return this.eventEndTime.format(Parser.STORAGE_FMT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.eventStartTime.format(Parser.DISPLAY_FMT)
                + " to: "
                + this.eventEndTime.format(Parser.DISPLAY_FMT)
                + ")";
    }
}
