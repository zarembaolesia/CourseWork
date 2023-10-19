package UITest;

import com.codeborne.selenide.Condition;
import org.base.BaseGUITest;
import org.base.pages.DashboardPage;
import org.base.pages.LoginPage;
import org.base.pages.ProjectPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ProjectGUITest extends BaseGUITest {
    @Test(groups= {"ProjectTest"})
    public void createProjectTest() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.goToDashboard("admin", "admin");

        dashboardPage.createProject("Project1", "PROJECT1");
        ProjectPage projectPage = new ProjectPage();

        projectPage.getName().shouldBe(Condition.visible);

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(projectPage.getName(), "Project1");
    }
}