package org.base.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Board {
    private SelenideElement addTaskBtn = $x("//div[contains(@class, 'board-add-icon')]//i[contains(@class, 'fa fa-plus fa-fw js-modal-large')]");
    public SelenideElement taskName = $x("//div[@class=\"task-board-expanded\"]//div[@class=\"task-board-title\"]");
    public TaskPage addBacklogTask() {
        addTaskBtn.click();
        return new TaskPage();
    }
    public void openBoardByName(String projectName){
        SelenideElement projectByName = $x("//*[@class=\"table-list-title\"]//a[@href='/board/*"+projectName+"']");
        projectByName.click();
    }

    public String getTaskName() {
        return taskName.toString();
    }
}
