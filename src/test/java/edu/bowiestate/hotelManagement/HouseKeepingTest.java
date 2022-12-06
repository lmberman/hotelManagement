package edu.bowiestate.hotelManagement;

import edu.bowiestate.hotelManagement.housekeep.HouseKeepTask;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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


    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void loadHouseKeepingTasksTab(String user) {
        loginWithUser(user);
        webDriver.findElement(By.id("houseKeepingTasksLink")).click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        List<WebElement> cardSections = webDriver.findElements(By.className("card"));
        assert(cardSections.size() == 4);
        assert(cardSections.get(0)
                .findElement(By.className("card-body"))
                .findElement(By.tagName("h1"))
                .getText()
                .equalsIgnoreCase("Add HouseKeep Task To Room"));
        assert(cardSections.get(1)
                .findElement(By.className("card-body"))
                .findElement(By.className("card-title"))
                .getText()
                .equalsIgnoreCase("HouseKeeping Tasks"));
        assert(cardSections.get(2).findElement(By.className("card-title"))
                .getText()
                .equalsIgnoreCase("In-Progress Tasks"));
        assert(cardSections.get(3).findElement(By.className("card-title"))
                .getText()
                .equalsIgnoreCase("Completed Tasks"));

        assert(cardSections.get(0)
                .findElement(By.id("add-housekeep-task-section"))
                .findElement(By.id("add_task_to_room"))
                .isDisplayed());
        assert(cardSections.get(2)
                .findElement(By.tagName("table"))
                .isDisplayed());
        assert(cardSections.get(3)
                .findElement(By.tagName("table"))
                .isDisplayed());
    }

    @ValueSource(strings = {"Manager", "Receptionist"})
    @ParameterizedTest
    public void addNewHouseKeepTask(String user) {
        loginWithUser(user);
        webDriver.findElement(By.id("houseKeepingTasksLink")).click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        int inProgressTasksCount = webDriver.findElements(By.className("card"))
                .get(2)
                .findElement(By.tagName("table"))
                .findElements(By.tagName("tr")).size();

        WebElement newTaskForm = webDriver.findElement(By.id("add_task_to_room"));
        WebElement taskRoomNumSelect = newTaskForm.findElement(By.id("taskRoomNum"));
        WebElement taskTypeSelect = newTaskForm.findElement(By.id("taskType"));
        WebElement deadlineDate = newTaskForm.findElement(By.id("deadlineDate"));
        WebElement submitButton = newTaskForm.findElement(By.tagName("button"));

        List<WebElement> roomOptions = taskRoomNumSelect.findElements(By.tagName("option"));
        roomOptions.get(roomOptions.size()-1).click();
        List<WebElement> taskTypeOptions = taskTypeSelect.findElements(By.tagName("option"));
        taskTypeOptions.get(taskTypeOptions.size()-1).click();
        deadlineDate.sendKeys("12-06-2022");

        submitButton.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assert(webDriver.findElement(By.id("updateSuccessMessage")).isDisplayed());

        List<WebElement> inProgressTasks = webDriver.findElements(By.className("card"))
                .get(2)
                .findElement(By.tagName("table"))
                .findElements(By.tagName("tr"));
        int inProgressTasksNewSize = inProgressTasks.size();

        assert(inProgressTasksNewSize == inProgressTasksCount+1);
        List<WebElement> lastTaskData = inProgressTasks
                .get(inProgressTasksNewSize-1)
                .findElements(By.tagName("td"));

        assert(lastTaskData
                .get(0)
                .getText()
                .equalsIgnoreCase("Pending Ownership"));
    }

}
