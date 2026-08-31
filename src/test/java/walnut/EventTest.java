package walnut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    void parseTask_event_createsEventCorrectly() {
        Task task = Parser.parseTask(
                "E | 0 | meeting | 2026/09/01 1000-2026/09/01 1200"
        );

        assertTrue(task instanceof Event);

        Event event = (Event) task;

        assertEquals("meeting", event.getDescription());
        assertEquals("2026/09/01 1000", event.getEventStartTime());
        assertEquals("2026/09/01 1200", event.getEventEndTime());
        assertFalse(event.isDone());
    }

    @Test
    void parseTask_event_createsCompletedEvent() {
        Task task = Parser.parseTask(
                "E | 1 | meeting | 2026/09/01 1000-2026/09/01 1200"
        );

        assertTrue(task instanceof Event);

        Event event = (Event) task;

        assertEquals("meeting", event.getDescription());
        assertEquals("2026/09/01 1000", event.getEventStartTime());
        assertEquals("2026/09/01 1200", event.getEventEndTime());
        assertTrue(event.isDone());
    }
}
