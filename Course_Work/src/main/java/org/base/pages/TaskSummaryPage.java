package org.base.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class TaskSummaryPage {

    public SelenideElement commentDropdown = $x("//details[@class=\"accordion-title\"][last()]");
    public SelenideElement writeComment = $x("//div[@class=\"text-editor-write-mode\"]//textarea[@name=\"comment\"]");
    public SelenideElement saveCommentBtn = $x("//div[@id=\"modal-content\"]//button[@type=\"submit\"]");
    public SelenideElement closeTask = $x("//div[@class=\"sidebar sidebar-icons\"]//a[contains href = \"/task/*/close\"]");
    public SelenideElement yesBtn = $x("//div[@class=\"form-actions\"]//button[@id=\"modal-confirm-button\"]");
    public SelenideElement availableComment = $x("//div[@class=\"comment-content\"]//div[@class=\"markdown\"].text()");

    public void openTaskSummary(String taskName){
        SelenideElement taskSummary = $x("//*[@class=\"table-list-title\"]//a[@href='/board/*"+taskName+"']");
        taskSummary.click();
    }
    public void addComment(String comment){
        commentDropdown.click();
        writeComment.setValue(comment);
        saveCommentBtn.click();
    }
    public void closeTask(){
        closeTask.click();
        yesBtn.click();
    }

    public String getComment(){
        return availableComment.toString();
    }
}
