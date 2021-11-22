package com.geekbrains.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    private static final String CRM_URL = "https://crm.geekbrains.space";
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setProperties() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get(CRM_URL);
    }

    @BeforeEach
    void setUpBrowser() {
        webDriverWait = new WebDriverWait(webDriver, 5);

    }

    @Test
    void loginTest() throws InterruptedException {
        new LoginPage(webDriver)
                .inputLogin("Applanatest1", "Student2020!")
                .submitLogin();

        new MainPage(webDriver).navigationMenu.openNavigationSubMenu("Проекты");
        new ProjectsSubMenu(webDriver)
                .projectsButtonClick()
                .createNewProjectButtonClick();
        Thread.sleep(5000);
        new CreateProjectPage(webDriver)
                .inputProjectName("test22112108")
                .selectOrganization()
                .selectOrganizationNameClick()
                .selectBusinessUnit("Research & Development")
                .selectCurator("Гумённый Пётр")
                .selectProjectDirector("Воденеев Денис")
                .selectManager("Прохорова Алла")
                .saveButtonClick();
        Thread.sleep(5000);
        Assertions.assertTrue(webDriver.findElement(By.xpath("//div[.='Проект сохранен']")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }
}
