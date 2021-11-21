package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateProjectPage extends BaseView {
    public CreateProjectPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@name='crm_project[name]']")
    public WebElement projectName;

    public CreateProjectPage inputProjectName(String name) {
        this.projectName.sendKeys(name);
        return this;
    }

    @FindBy(xpath = "//span[.='Укажите организацию']")
    public WebElement selectOrganization;

    public CreateProjectPage selectOrganization() {
        this.selectOrganization.click();
        return this;
    }

    @FindBy(xpath = "//div[.='1234']")
    public WebElement selectOrganizationName;

    public CreateProjectPage selectOrganizationNameClick() throws InterruptedException {
        Thread.sleep(5000);
        selectOrganizationName.click();
        return this;
    }
    @FindBy(xpath = "//select[@name='crm_project[businessUnit]']")
    public WebElement businessUnitSelect;

    public CreateProjectPage selectBusinessUnit(String option){
        new Select(businessUnitSelect).selectByVisibleText(option);
      return this;
    }

    @FindBy(xpath = "//select[@name='crm_project[curator]']")
    public WebElement curatorSelect;

    public CreateProjectPage selectCurator(String option){
        new Select(curatorSelect).selectByVisibleText(option);
        return this;
    }
    @FindBy(xpath = "//select[@name='crm_project[rp]']")
    public WebElement rpSelect;

    public CreateProjectPage selectProjectDirector(String option){
        new Select(rpSelect).selectByVisibleText(option);
        return this;
    }
    @FindBy(xpath = "//select[@name='crm_project[manager]']")
    public WebElement managerSelect;

    public CreateProjectPage selectManager(String option){
        new Select(managerSelect).selectByVisibleText(option);
        return this;
    }

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    public WebElement saveButton;

    public MainPage saveButtonClick() throws InterruptedException {
        saveButton.click();
        return new MainPage(webDriver);
    }
}
