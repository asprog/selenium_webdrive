package com.geekbrains.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class CreateNewProject {
    static WebDriver driver;
    private static final String CRM_URL = "https://crm.geekbrains.space";
    public static void main(String[] args) {
       try {
           setProperties();
           login();
           openMyProjects();
           createNewProject();
           fillNewProject();
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally{
         if(driver != null) driver.quit();
       }
    }

    static void setProperties() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    static void login() {
      driver.get(CRM_URL);
      driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
      driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
      driver.findElement(By.id("_submit")).click();
    }
     static void openMyProjects() throws InterruptedException {
        WebElement weProjects = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li/a/span[.='Проекты']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(weProjects).build().perform();
        driver.findElement(By.xpath("//span[.='Мои проекты']")).click();

    }
    static void createNewProject() throws InterruptedException {
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[.='Создать проект']")).click();
    }
    private static void fillNewProject() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys("test2609210321");
        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[.='1234']")).click();
        /*driver.findElement(By.xpath("//div[contains(@id,'project_contactMain-uid')]//span[@class='select2-arrow']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[.='Bon Jovi Jhon']")).click();*/
        new Select(
           driver.findElement(By.xpath("//select[@name='crm_project[businessUnit]']")))
                .selectByVisibleText("Research & Development");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[curator]']")))
                .selectByVisibleText("Гумённый Пётр");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[rp]']")))
                .selectByVisibleText("Воденеев Денис");
        new Select(
                driver.findElement(By.xpath("//select[@name='crm_project[manager]']")))
                .selectByVisibleText("Прохорова Алла");
        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        Thread.sleep(10000);

    }
}
