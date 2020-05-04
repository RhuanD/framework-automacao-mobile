package edittext;

import interfaces.IEditText;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;


public class EditText implements IEditText {
	private AppiumDriver<AndroidElement> driver;
	private AndroidElement element;
	/**
	 * Classe de implementação da interface IEditText
	 * */
	public EditText(AppiumDriver<AndroidElement> driver, By by){
		try{
			this.driver = driver;
			this.element = this.driver.findElement(by);
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
	}

	public String getText(){
		String text = "";
		try {
			if (this.element.isDisplayed()){
				text = this.element.getText();
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return text;
	}

}
