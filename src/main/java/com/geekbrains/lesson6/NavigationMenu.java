package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavigationMenu extends BaseView {
    ProjectsSubMenu projectsSubMenu;
    @FindBy(xpath = "//div[@id='main-menu']/ul/li")
    public List<WebElement> navigationMenuItems;

    public NavigationMenu(WebDriver webDriver) {
       super(webDriver);
       projectsSubMenu = new ProjectsSubMenu(webDriver);
    }
    public ProjectsSubMenu openNavigationSubMenu(String menuName){
      final Actions actions = new Actions(webDriver);
      actions.moveToElement(navigationMenuItems.stream().filter(element -> element.getText().equals(menuName))
              .findFirst().get()).build().perform();
      return new ProjectsSubMenu(webDriver);
    }
}
