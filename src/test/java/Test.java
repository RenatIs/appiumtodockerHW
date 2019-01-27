import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.*;
import org.testng.annotations.*;

public class Test {

    private AppiumDriver<WebElement> driver;

    @BeforeTest
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../../");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","LGE Nexus 4");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @org.testng.annotations.Test
    public void apiDemo(){
        WebElement el = driver.findElement(By.xpath(".//*[@text='Animation']"));

        Assert.assertEquals("Animation", el.getText());
        el = driver.findElementByClassName("android.widget.TextView");
        Assert.assertEquals("API Demos", el.getText());
        el = driver.findElement(By.xpath(".//*[@text='App']"));
        el.click();
        List<WebElement> els = driver.findElementsByClassName("android.widget.TextView");
        Assert.assertEquals("Activity", els.get(2).getText());
    }
}
