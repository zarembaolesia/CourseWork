package org.base.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class DashboardPage
{
    public SelenideElement overview  = Selenide.$x("//*[@id=\"dashboard\"]/div[1]/ul/li[1]/a[contains()@href, 'dashboard/1')]");

    private SelenideElement newProjectBtn = Selenide.$x("//section//a[@href='/project/create']");
    private SelenideElement nameProjectForm = $x("//div[@id=\"modal-box\"]\"name");
    private SelenideElement idProjectForm = $x("//div[@id=\"modal-box\"]\"identifier");
    private SelenideElement saveProjectButton = $x("//div[@class=\"form-actions\"]/button");

    public void createProject(String name, String id) {
        newProjectBtn.click();
        nameProjectForm.setValue(name);
        idProjectForm.setValue(name);
        saveProjectButton.click();
    }

}
