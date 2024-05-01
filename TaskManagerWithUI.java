import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TaskManagerWithUI {
    private JFrame frame;
    private JTextField taskNameField, descriptionField, dueDateField;
    private JButton addButton, refreshButton;
    private JTextArea taskListArea;

    public TaskManagerWithUI() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        inputPanel.add(taskNameField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Due Date (Year-Month-Day):"));
        dueDateField = new JTextField();
        inputPanel.add(dueDateField);

        addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        inputPanel.add(addButton);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTaskList();
            }
        });
        inputPanel.add(refreshButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        taskListArea = new JTextArea();
        frame.add(new JScrollPane(taskListArea), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TaskManagerWithUI();
            }
        });
    }

    private void addTask() {
        String taskName = taskNameField.getText();
        String description = descriptionField.getText();
        String dueDateString = dueDateField.getText();
        if (!taskName.isEmpty() && !dueDateString.isEmpty()) {
            try {
                Connection connection = DatabaseManager.getConnection();
                String addTaskSQL = "INSERT INTO tasks (task_name, description, due_date) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(addTaskSQL);
                preparedStatement.setString(1, taskName);
                preparedStatement.setString(2, description);
                preparedStatement.setDate(3, Date.valueOf(dueDateString));
                preparedStatement.executeUpdate();
                connection.close();
                refreshTaskList();
                clearInputFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Task Name and Due Date are required!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTaskList() {
        try {
            Connection connection = DatabaseManager.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tasks");
            taskListArea.setText("");
            while (resultSet.next()) {
                taskListArea.append(resultSet.getInt("id") + ". "
                        + resultSet.getString("task_name") + " - "
                        + resultSet.getString("description") + " - "
                        + resultSet.getDate("due_date") + "\n");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearInputFields() {
        taskNameField.setText("");
        descriptionField.setText("");
        dueDateField.setText("");
    }
}
