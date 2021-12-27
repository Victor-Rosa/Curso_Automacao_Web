import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    private WebDriver driver;


    @Test
    public void teste() {

        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get("https://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());

    }
}
