package walnut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    @Test
    void parseTask_todo_createsTodo() {
        Task task = Parser.parseTask("T | 0 | buy milk");

        assertTrue(task instanceof ToDo);
        assertEquals("buy milk", task.getDescription());
        assertFalse(task.isDone());
    }

}
