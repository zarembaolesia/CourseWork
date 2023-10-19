package UITest;

import org.base.*;
import org.base.pages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TaskGUITest extends BaseGUITest {
    APISetup apiSetup = new APISetup();

    @BeforeTest
    public void beforeTest(){
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.goToDashboard("admin", "admin");
        String userName =  apiSetup.createUser("user3", "password3", "admin");
        String projectName =  apiSetup.createProject("Project3");
    }

    @Test(groups= {"TaskTest"})
    public void createCloseCommentTaskTest() {
        Board board = new Board();
        TaskPage taskPage;

        board.openBoardByName("Project3");
        taskPage = board.addBacklogTask();
        taskPage.createTask("Task3");

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(board.getTaskName(), "Task3");

        TaskSummaryPage taskSummary = new TaskSummaryPage();
        taskSummary.openTaskSummary("Task3");

        taskSummary.addComment("AnyComment");
        soft.assertEquals(taskSummary.getComment(), "AnyComment");

        taskSummary.closeTask();
        soft.assertTrue(board.getTaskName().isEmpty());
    }
}
