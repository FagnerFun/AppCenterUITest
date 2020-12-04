package com.dextra.dextragames;

import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;
import org.junit.Rule;

public class StartAppTest {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    private static EnhancedAndroidDriver<MobileElement> driver;

    public static EnhancedAndroidDriver<MobileElement> startApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        capabilities.setCapability(MobileCapabilityType.APP, "D:/Projetos/Dextra/AppCenter-Samples/Appium/Android/com.dextra.dextragames.apk");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 7913);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        URL url = new URL("http://localhost:4723/wd/hub");

        return Factory.createAndroidDriver(url, capabilities);
    }


    @Test
    public void canStartAppInTest() throws MalformedURLException, InterruptedException {
        driver = startApp();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.findElementByXPath ("//android.widget.TextView[@text='Log in with Google']").click();
    	System.out.println(driver.findElementByXPath ("//android.widget.TextView[@text='DEXTRA GAMES']").isDisplayed());
    	driver.findElementByXPath ("//android.widget.TextView[@text='Happy Hours']").click();
    	System.out.println(driver.findElementByXPath ("//android.widget.TextView[@text='Happy Hours']").isDisplayed());
    	
        Thread.sleep(5000);
    }

    @After
    public void after() throws Exception {
        if (driver != null) {
            driver.label("Stopping App");
            driver.quit();
        }
    }
}
