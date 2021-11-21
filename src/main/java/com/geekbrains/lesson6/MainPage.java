package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;

public class MainPage extends BaseView {
    public NavigationMenu navigationMenu;
    public MainPage(WebDriver webDriver) {
        super(webDriver);
        navigationMenu = new NavigationMenu(webDriver);
    }
}
