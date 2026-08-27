public class Events extends Task {
    private String eventStartTime;
    private String eventEndTime;

    public Events(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String getEventStartTime() {
        return this.eventStartTime;
    }

    public String getEventEndTime() {
        return this.eventEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }
}