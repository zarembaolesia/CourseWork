package org.base.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class LoginPage
{
    final public SelenideElement username    = Selenide.$x("//input[@id='form-username']");
    final public SelenideElement password = Selenide.$x("//input[@id='form-password']");
    final public SelenideElement loginBtn = Selenide.$x("//form[@id='post']//button[@type='submit']");

    public DashboardPage goToDashboard(String name, String password) {
        username.sendKeys(name);
        this.password.sendKeys(password);
        loginBtn.click();
        return new DashboardPage();
    }

}
