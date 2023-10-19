package UITest;

import org.base.BaseGUITest;
import org.base.pages.DashboardPage;
import org.base.pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AuthorizationGUITest extends BaseGUITest
{
    @Test(groups= {"LoginTest"})
    public void PositiveLoginCheck() {
        LoginPage loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.goToDashboard("admin", "admin");

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(dashboardPage.overview.isDisplayed());
    }
    @Test(groups= {"LoginTest", "NegativeCases"})
    public void NoLoginWithoutPassword() {
        LoginPage loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.goToDashboard("admin", "");

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(dashboardPage.overview.isDisplayed());
    }

    @Test(groups= {"LoginTest", "NegativeCases"})
    public void NoLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.goToDashboard("adm1n", "admin");

        SoftAssert soft = new SoftAssert();
        soft.assertFalse(dashboardPage.overview.isDisplayed());
    }
}