package reports;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ScreenShot {

	public ScreenShot() {
	}

	public static String capture(WebDriver driver) {
		File source = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "./reports/imgs/" + source.getName();
		return saveImageFile(source, path);
	}

	public static String capture(AppiumDriver<AndroidElement> driver) {
		File source = (File) driver.getScreenshotAs(OutputType.FILE);
		String path = "./reports/imgs/" + source.getName();
		return saveImageFile(source, path);
	}

	public static String captureFullScreen() throws AWTException {
		String newPath = "./reports/imgs/" + System.currentTimeMillis() + ".png";

		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = (new Robot()).createScreenCapture(screenRect);
			ImageIO.write(capture, "png", new File(newPath));
		} catch (IOException var3) {
			Report.log(Status.WARNING, "Erro ao capturar ScreenShot: " + var3.getMessage());
		}

		return newPath.replace("./reports/", "");
	}

	public static String captureRegion(int x, int y, int w, int h) throws AWTException {
		String newPath = "./reports/imgs/" + System.currentTimeMillis() + ".png";

		try {
			Rectangle rectangle = new Rectangle(x, y, w, h);
			BufferedImage capture = (new Robot()).createScreenCapture(rectangle);
			ImageIO.write(capture, "png", new File(newPath));
		} catch (IOException var7) {
			Report.log(Status.WARNING, "Erro ao capturar ScreenShot: " + var7.getMessage());
		}

		return newPath.replace("./reports/", "");
	}

	private static String saveImageFile(File source, String path) {
		try {
			FileUtils.copyFile(source, new File(path));
		} catch (IOException var3) {
			Report.log(Status.FAIL, "Erro ao capturar ScreenShot: " + var3.getMessage());
		}

		return path.replace("./reports/", "");
	}
}
