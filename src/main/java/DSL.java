import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;


    public DSL(WebDriver driver) {

        this.driver = driver;
    }

    public void write(String id_campo,String text ) {

        driver.findElement(By.id(id_campo)).sendKeys(text);
    }

    public String obterValorCampo(String id_campo){

        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }

    public void clickedRadio(String id){
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarked(String id){
        return  driver.findElement(By.id(id)).isSelected();
    }

    public void selectCombo(String id, String valueSelect){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valueSelect);
    }

    public String getValueCombo(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clickedButton(String id){
        driver.findElement(By.id(id)).click();
    }

    public void clickedLink(String link){
        driver.findElement(By.linkText(link)).click();

    }

    public String getTextLink(String id){
       return getTextLink(By.id(id));
    }

    public String getTextLink(By by){
        return driver.findElement(by).getText();
    }
}