package org.base.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {

    private SelenideElement taskName = Selenide.$("input#form-title");
    public SelenideElement saveBtn = $x("//div[@id=\"modal-content\"]//button[@type=\"submit\"]");

    public void createTask(String taskName){
        this.taskName.sendKeys(taskName);
        saveBtn.click();
    }
}
