import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrameEJanelas {
    private WebDriver driver;

    @Before
    public void initializesBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\VIC\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void quitBrowser(){
        driver.quit();
    }

    @Test
    public void deveInteragirComFrames() {


        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
        Assert.assertEquals(msg, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

    }

    @Test
    public void deveInteragirComJanelas() {


        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");

        Assert.assertEquals("Deu Certo?", driver.findElement(By.id("elementosForm:sugestoes"))
                .getAttribute("value"));

    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {

        driver.findElement(By.id("buttonPopHard")).click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Olá, funcionou");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora ?");

    }

}
