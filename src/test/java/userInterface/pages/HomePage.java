package userInterface.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @FindBy(xpath = "//*[text()='MegaZip']")
    private WebElement headLogo;

    @FindBy(xpath = ".//*[contains(text(), 'HAPPYHOURS')]")
    private WebElement happyHoursText;

    public boolean bucketIconClick(){
        return headLogo.isDisplayed();
    }

    public boolean isSaleTextDisplayed(){
        return happyHoursText.isDisplayed();
    }
}
