package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BaseView {
    @FindBy(xpath = "//a[.='Создать проект']")
    public WebElement createNewProjectButton;

    public ProjectsPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void createNewProjectButtonClick() throws InterruptedException {
        Thread.sleep(5000);
        createNewProjectButton.click();
    }
}
