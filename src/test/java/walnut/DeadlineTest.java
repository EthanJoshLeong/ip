package walnut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void parseTask_deadline_createsDeadline() {
        Task task = Parser.parseTask(
                "D | 0 | submit assignment | 2026/09/01 1700"
        );

        assertTrue(task instanceof Deadline);
        assertEquals("submit assignment", task.getDescription());
        assertFalse(task.isDone());
    }

}
