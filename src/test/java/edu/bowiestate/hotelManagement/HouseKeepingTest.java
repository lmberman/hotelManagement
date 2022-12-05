package edu.bowiestate.hotelManagement;

import edu.bowiestate.hotelManagement.housekeep.HouseKeepTask;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

@SpringBootTest
public class HouseKeepingTest extends AbstractSeleniumTest {

    @RepeatedTest(value = 2)
    public void updateHouseKeepTask() {
        loginWithUser("HouseKeep");
        List<WebElement> tasks = webDriver.findElement(By.id("yourTasks"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
        WebElement task = tasks.get(tasks.size() - 1);
        List<WebElement> taskTypeOptions = task.findElements(By.tagName("td")).get(4)
                .findElement(By.id("taskType"))
                .findElements(By.tagName("option"));
        List<WebElement> taskStatusOptions = task.findElements(By.tagName("td")).get(5)
                .findElement(By.id("status"))
                .findElements(By.tagName("option"));
        String previousType = taskTypeOptions.get(0).getText();
        String previousStatus = taskStatusOptions.get(0).getText();
        String selectedType = taskTypeOptions.get(1).getText();
        String selectedStatus = taskStatusOptions.get(1).getText();
        String updatedTasksId = task.findElements(By.tagName("td")).get(0).getText();
        taskTypeOptions.get(1).click();
        taskStatusOptions.get(1).click();
        WebElement updateTaskButton = task.findElement(By.tagName("button"));
        updateTaskButton.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        assert (webDriver.getCurrentUrl().equalsIgnoreCase("http://localhost:9000/home"));

        if (selectedStatus.equalsIgnoreCase(HouseKeepTask.TaskStatus.COMPLETE.name())) {
            List<WebElement> tableRows = webDriver.findElement(By.id("yourTasks"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));
            assert (tableRows.size() == tasks.size() - 1);
            for (WebElement row : tableRows) {
                String rowTaskId = row.findElements(By.tagName("td")).get(0).getText();
                assert (!rowTaskId.equalsIgnoreCase(updatedTasksId));
            }

        } else {
            task = webDriver.findElement(By.id("yourTasks"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr")).get(1);
            List<WebElement> taskTypeNewOptions = task.findElements(By.tagName("td")).get(4)
                    .findElement(By.id("taskType"))
                    .findElements(By.tagName("option"));
            List<WebElement> taskStatusNewOptions = task.findElements(By.tagName("td")).get(5)
                    .findElement(By.id("status"))
                    .findElements(By.tagName("option"));
            assert (!previousType.equalsIgnoreCase(taskTypeNewOptions.get(0).getText()));
            assert (!previousStatus.equalsIgnoreCase(taskStatusNewOptions.get(0).getText()));
            assert (selectedType.equalsIgnoreCase(taskTypeNewOptions.get(0).getText()));
            assert (selectedStatus.equalsIgnoreCase(taskStatusNewOptions.get(0).getText()));
        }
    }

    @Test
    public void claimTask() {
        loginWithUser("HouseKeep");
        List<WebElement> tasks = webDriver.findElement(By.id("yourTasks"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
        int yourTaskListSizeBefore = tasks.size();
        List<WebElement> unOwnedTasks = webDriver.findElement(By.id("unOwnedTasks"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
        if (unOwnedTasks.size() > 0) {
            WebElement task = unOwnedTasks.get(unOwnedTasks.size() - 1);
            String updatedTasksId = task.findElements(By.tagName("td")).get(0).getText();
            int unOwnedTaskListSizeBefore = unOwnedTasks.size();

            WebElement claimButton = task.findElement(By.id("claimTask"));
            claimButton.click();

            List<WebElement> newOwnedTaskList = webDriver.findElement(By.id("yourTasks"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));
            task = newOwnedTaskList.get(newOwnedTaskList.size() - 1);
            String newOwnedTaskId = task.findElements(By.tagName("td")).get(0).getText();

            List<WebElement> unOwnedTasksAfter = webDriver.findElement(By.id("unOwnedTasks"))
                    .findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));
            assert (newOwnedTaskList.size() == yourTaskListSizeBefore + 1);
            assert (unOwnedTasksAfter.size() == unOwnedTaskListSizeBefore - 1);
            assert (updatedTasksId.equals(newOwnedTaskId));
        }
    }
}
