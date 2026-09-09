package walnut;

/**
 * Stores a task description and its completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates an incomplete task with the specified description.
     */
    public Task(String description) {
        assert description != null && !description.isBlank()
                : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
