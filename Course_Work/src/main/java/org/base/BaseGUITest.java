package org.base;

import org.utils.Config;
import org.utils.Session;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseGUITest extends BaseTestNg
{
    @BeforeMethod(alwaysRun = true)
    public void before() {
        this.wd().get(String.format("http://%s:%s",
                Config.HTTP_BASE_HOST.value,
                Config.HTTP_BASE_PORT.value
        ));
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        Session.get().close();
    }

    protected WebDriver wd() {
        return Session.get().webdriver();
    }
}