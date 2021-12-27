import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializesBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\VIC\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);

    }

    @After
    public void quitBrowser(){

        driver.quit();
    }

    @Test
    public void testeTextField() {

        dsl.write("elementosForm:nome", "Victor");
        Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome")
        );

    }

    @Test
    public void deveInteragirComTextArea(){

        dsl.write("elementosForm:sugestoes", "teste");
        Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));

    }

    @Test
    public void deveInteragirComRadioButton(){


        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

    }

    @Test
    public void deveInteragirComCheckbox(){

        dsl.clickedRadio("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isRadioMarked("elementosForm:comidaFavorita:2"));

    }

    @Test
    public void deveInteragirComCombo(){

        dsl.selectCombo("elementosForm:escolaridade", "Mestrado");
        Assert.assertEquals("Mestrado", dsl.getValueCombo("elementosForm:escolaridade"));

    }


    @Test
    public void deveVerificarValoresCombo(){

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean find = false;
            for(WebElement option: options){
                if(option.getText().equals("Mestrado")){
                    find = true;
                    break;
                }
            }
            Assert.assertTrue(find);
            System.out.println(find);

    }

    @Test
    public void deveVerificarValoresComboMultiplo() {

        dsl.selectCombo("elementosForm:esportes", "Natacao");
        dsl.selectCombo("elementosForm:esportes", "Corrida");
        dsl.selectCombo("elementosForm:esportes", "O que eh esporte?");


        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("O que eh esporte?");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }

    @Test
    public void deveInteragirComBotoes() {

        dsl.clickedButton("buttonSimple");
        WebElement button = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", button.getAttribute("value"));

    }

    @Test
    public void deveInteragirComLinks() {

        dsl.clickedLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.getTextLink("resultado"));

    }

    @Test
    public void deveBuscarTextoNaPagina() {

        //Assert.assertTrue(driver.findElement(By.tagName("h3"))
                //.getText().contains("Campo de Treinamento"));

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());

    }


}
