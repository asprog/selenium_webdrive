package com.geekbrains.lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class CreateNewContact {
    static WebDriver driver;
    static final String CRM_URL = "https://crm.geekbrains.space";
    static void main(String[] args) {
       try {
           setProperties();
           login();
           openContacts();
           createNewContact();
           fillNewContact();
           Thread.sleep(10000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       } finally{
         if(driver != null) driver.quit();
       }
    }

    static void setProperties() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
    }
    static void login() {
      driver.get(CRM_URL);
      driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
      driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
      driver.findElement(By.id("_submit")).click();
    }
    static void openContacts() throws InterruptedException {
        WebElement weProjects = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li/a/span[.='Контрагенты']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(weProjects).build().perform();
        driver.findElement(By.xpath("//a/span[.='Контактные лица']")).click();
    }
    static void createNewContact() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[.='Создать контактное лицо']")).click();
    }
    static void fillNewContact() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='crm_contact[lastName]']")).sendKeys("Пупкин");
        driver.findElement(By.xpath("//input[@name='crm_contact[firstName]']")).sendKeys("Вася");
        driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_contact_company')]//span[@class='select2-arrow']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[.='1234']")).click();
        driver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("Студент");
        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
    }
}
