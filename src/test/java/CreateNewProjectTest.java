import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateNewProjectTest {
    WebDriverWait webDriverWait;
    @BeforeAll
    static void registerWebDriver(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setProperties(){
        CreateNewProject.setProperties();
    }
    @BeforeEach
    void setUpBrowser(){
        webDriverWait = new WebDriverWait(CreateNewProject.driver,5);
        CreateNewProject.login();
    }
    @Test
    void openMyProjectsTest() throws InterruptedException {
        WebDriver driver = CreateNewProject.driver;
        WebElement weProjects = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li/a/span[.='Проекты']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(weProjects).build().perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//span[.='Мои проекты']"));
        Assertions.assertEquals("Мои проекты",element.getText());
    }
    @Test
    void createNewProjectTest() throws InterruptedException {
        WebDriver driver = CreateNewProject.driver;
        CreateNewProject.openMyProjects();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[.='Создать проект']"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assertions.assertEquals("Создать проект",element.getText());
    }
    @Test
    void fillNewProjectTest() throws InterruptedException {
        WebDriver driver = CreateNewProject.driver;
        CreateNewProject.openMyProjects() ;
        String crmProjectName = "test" + (new Random().ints(100,10000)
                .findFirst()
                .getAsInt());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        CreateNewProject.createNewProject();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys(crmProjectName);
        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[.='1234']")).click();
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='filter-item oro-drop']")).click();
        driver.findElement(By.xpath("//div[@class='value-field-frame']/input")).sendKeys(crmProjectName);
        driver.findElement(By.xpath("//button[.='Обновить']")).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.xpath("//table[@class='grid table-hover table table-bordered table-condensed']//td[@class='string-cell grid-cell grid-body-cell grid-body-cell-name']"));
        boolean find = false;
        if(elements != null && elements.size() != 0)  find = !find;
        Assertions.assertTrue(find);
    }

    @AfterEach
    void tearDown() {
        CreateNewProject.driver.quit();
    }
}
