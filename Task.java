import java.sql.Date;

public class Task {
    private int id;
    private String taskName;
    private String description;
    private Date dueDate;

    public Task(int id, String taskName, String description, Date dueDate) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Name: " + taskName + ", Description: " + description + ", Due Date: " + dueDate;
    }
}
