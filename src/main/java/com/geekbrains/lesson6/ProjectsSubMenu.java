package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsSubMenu extends BaseView {
  @FindBy(xpath = "//span[.='Мои проекты']")
  public WebElement openMyProjects;

  public ProjectsSubMenu(WebDriver webDriver){
    super(webDriver);
  }

  public ProjectsPage projectsButtonClick(){
    openMyProjects.click();
    return new  ProjectsPage(webDriver);
  }

}
