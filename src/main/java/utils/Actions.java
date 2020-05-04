package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;

public class Actions {
	private AppiumDriver<AndroidElement> driver;
	private Dimension size;
	private int starty;
	private int endy;
	private int startx;
	private int endx;

	public Actions(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
		size = driver.manage().window().getSize();
		starty = (int) (size.height * 0.80);
		endy = (int) (size.height * 0.20);
		startx = size.width / 2;
	}
	
	public void moveScreenDown() {
		driver.swipe(startx, starty, startx, endy, 2000);
	}
	
	public void moveScreenUp() {
		driver.swipe(startx, endy, startx, starty, 2000);
	}

	public void moveScreenLeft(){
		startx = (int) (size.width*0.90);
		endx = (int) (size.width*0.10);
		starty = size.height/2;
		driver.swipe(startx, starty, endx, starty, 200);
	}

	public void waitThreeSeconds(){
		try {
			Thread.sleep(3000);
		}catch (InterruptedException ex){
			ex.getMessage();
		}
	}
}
