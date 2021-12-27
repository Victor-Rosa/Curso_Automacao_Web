import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCadastro {

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

    }


    @Test
    public void insereNome() {


        driver.findElement(By.id("elementosForm:nome")).sendKeys("Victor");
        Assert.assertEquals("Victor",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

    }

    @Test
    public void insereSobrenome() {


        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Rosa");
        Assert.assertEquals("Rosa",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

    }

    @Test
    public void insereSexo() {


        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());



    }

    @Test
    public void insereComidaFavorita() {


        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());



    }

    @Test
    public void insereEscolaridade() {


        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Superior");
        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());



    }

    @Test
    public void insereEsportes() {

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Futebol");
        combo.selectByVisibleText("Corrida");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }

    @Test
    public void finalizaCadastro() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();

    }

    @Test
    public void insereCadastroCompleto() {


        driver.findElement(By.id("elementosForm:nome")).sendKeys("Victor");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Rosa");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        new Select(driver.findElement(By.id("elementosForm:escolaridade")))
                .selectByVisibleText("Superior");
        new Select(driver.findElement(By.id("elementosForm:esportes")))
                .selectByVisibleText("Futebol");
        driver.findElement(By.id("elementosForm:cadastrar")).click();


        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Victor"));


    }
}
