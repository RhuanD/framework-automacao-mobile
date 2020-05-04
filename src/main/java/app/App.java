package app;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App {
    private static AppiumServer server;

    public App() {
    }

    public static AppiumDriver<IOSElement> startIOS(String deviceName, String apkFile) throws Exception {
        try {
            initAppiumServer();
            String appFilePath = getApkFilePath(apkFile);
            URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");
            AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(serverUrl, configCapabilities(deviceName, appFilePath, false));
            driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
            return driver;
        } catch (MalformedURLException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static AppiumDriver<AndroidElement> startAndroid(String deviceName, String apkFile) {
        try {
            initAppiumServer();
            String appFilePath = getApkFilePath(apkFile);
            URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");
            AppiumDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(serverUrl, configCapabilities(deviceName, appFilePath, true));
            driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
            return driver;
        } catch (MalformedURLException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static void waitForLoading() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException var1) {
            System.out.println(var1.getMessage());
        }

    }

    public static void stop(AppiumDriver<AndroidElement> driver) {
        driver.quit();
        if (server != null) {
            server.stopServer();
        }

    }

    private static void initAppiumServer() {
        ServerArguments arguments = new ServerArguments();
        arguments.setArgument("--address", "127.0.0.1");
        arguments.setArgument("--port", "4723");
        arguments.setArgument("--no-reset", true);
    }

    private static DesiredCapabilities configCapabilities(String deviceName, String appFilePath, boolean isAndroid) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", appFilePath);
        if (isAndroid){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "uiautomator1");
            capabilities.setCapability("appPackage", "android.app");
            capabilities.setCapability("appActivity", "android.app.ui.SplashActivity");
        } else {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "13.3");
            capabilities.setCapability("automationName", "XCUITest");
        }
        return capabilities;
    }

    private static String getApkFilePath(String apkFile) {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/apps/");
        File apk = new File(appDir, apkFile);
        return apk.getAbsolutePath();
    }
}
