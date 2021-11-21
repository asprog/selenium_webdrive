package com.geekbrains.lesson5;

import com.geekbrains.lesson5.CreateNewContact;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateNewContactTest {
    WebDriverWait webDriverWait;

    @BeforeAll
    static void registerWebDriver(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setProperties(){
        CreateNewContact.setProperties();
    }
    @BeforeEach
    void setUpBrowser(){
       webDriverWait = new WebDriverWait(CreateNewContact.driver,5);
       CreateNewContact.login();
    }
    @Test
    void openContactsTest() throws InterruptedException {
        WebDriver driver = CreateNewContact.driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement contragents = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li/a/span[.='Контрагенты']"));
        Assertions.assertEquals("Контрагенты",contragents.getText());
        Actions actions = new Actions(driver);
        actions.moveToElement(contragents).build().perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a/span[.='Контактные лица']"));
        Assertions.assertEquals("Контактные лица",element.getText());
    }
    @Test
    void createNewContactTest() throws InterruptedException {
        WebDriver driver = CreateNewContact.driver;
        CreateNewContact.openContacts();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[.='Создать контактное лицо']"));
        Assertions.assertEquals("Создать контактное лицо",element.getText());
    }
    @Test
     void fillNewContactTest() throws InterruptedException {
        WebDriver driver = CreateNewContact.driver;
        CreateNewContact.openContacts();
        String surname = "Пупкин Оглы";
        CreateNewContact.createNewContact();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='crm_contact[lastName]']")).sendKeys(surname);
        driver.findElement(By.xpath("//input[@name='crm_contact[firstName]']")).sendKeys("Вася");
        driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_contact_company')]//span[@class='select2-arrow']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[.='1234']")).click();
        driver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("Студент");
        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']")).click();
        driver.findElement(By.xpath("//div[@class='value-field-frame']/input")).sendKeys(surname);
        driver.findElement(By.xpath("//button[.='Обновить']")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        /*webDriverWait.until(ExpectedConditions.visibilityOf(
                driver.findElement(
                        By.xpath("//div[contains(@id,'grid-crm-contact')]//table//td[@class = 'string-cell grid-cell grid-body-cell grid-body-cell-fio']"))
        ));*/
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@id,'grid-crm-contact')]//table//td[@class = 'string-cell grid-cell grid-body-cell grid-body-cell-fio']"));

        boolean find = false;
        if(elements != null && elements.size() != 0) find = !find;
        Assertions.assertTrue(find);
    }

    @AfterEach
    void tearDown() {
        CreateNewContact.driver.quit();
    }
}
