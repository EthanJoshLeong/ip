public class Events extends Task {
    private String eventStartTime;
    private String eventEndTime;

    public Events(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }
}