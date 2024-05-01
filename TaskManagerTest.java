import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.Test;

public class TaskManagerTest {

    @Test
    public void testAddTask() {
        // Créer une instance de la classe TaskManagerWithUI pour tester l'ajout d'une tâche
        TaskManagerWithUI taskManager = new TaskManagerWithUI();

        // Récupérer les champs de saisie de la fenêtre
        JTextField taskNameField = taskManager.getTaskNameField();
        JTextField descriptionField = taskManager.getDescriptionField();
        JTextField dueDateField = taskManager.getDueDateField();

        // Remplir les champs de saisie avec des données fictives
        taskNameField.setText("Test Task");
        descriptionField.setText("Test Description");
        dueDateField.setText("2024-05-05");

        // Créer un événement de clic sur le bouton "Add Task"
        ActionEvent e = new ActionEvent(new JButton(), 0, "");

        // Appeler la méthode addTask pour ajouter une tâche fictive
        taskManager.addTask();

        // Récupérer la liste des tâches après l'ajout
        String taskListAfterAddition = taskManager.getTaskListArea().getText();

        // Vérifier si la tâche ajoutée apparaît dans la liste des tâches
        assertTrue("La tâche ajoutée devrait être présente dans la liste des tâches après l'ajout.", taskListAfterAddition.contains("Test Task"));
    }

    @Test
    public void testRefreshTaskList() {
        // Créer une instance de la classe TaskManagerWithUI pour tester la mise à jour de la liste des tâches
        TaskManagerWithUI taskManager = new TaskManagerWithUI();

        // Récupérer les champs de saisie de la fenêtre
        JTextField taskNameField = taskManager.getTaskNameField();
        JTextField descriptionField = taskManager.getDescriptionField();
        JTextField dueDateField = taskManager.getDueDateField();

        // Remplir les champs de saisie avec des données fictives
        taskNameField.setText("Test Task");
        descriptionField.setText("Test Description");
        dueDateField.setText("2024-05-05");

        // Actualiser la liste des tâches
        taskManager.refreshTaskList();

        // Récupérer la liste des tâches après l'actualisation
        String taskListAfterRefresh = taskManager.getTaskListArea().getText();

        // Vérifier si la liste des tâches a été mise à jour correctement
        assertTrue("La liste des tâches devrait être mise à jour après l'actualisation.", taskListAfterRefresh.contains("Test Task"));
    }


}
