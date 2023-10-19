package org.base.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    public SelenideElement name = $x("//*[@class=\"table-list-title\"]//a[@href='/board/1']");

    public SelenideElement getName() {
        return name;
    }

    public void openBoardByName(String projectName){
        SelenideElement projectByName = $x("//*[@class=\"table-list-title\"]//a[@href='/board/*"+projectName+"']");
        projectByName.click();
    }
}
