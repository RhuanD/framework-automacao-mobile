package textfield;

import interfaces.ITextField;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import reports.Report;
import reports.ScreenShot;


public class TextField implements ITextField {
	 private AppiumDriver<AndroidElement> driver;
	    private AndroidElement element;
	    /**
	     * Classe de implementação da interface ITextField
	     * */
	    public TextField(AppiumDriver<AndroidElement> driver, By by){
	        try{
	            this.driver = driver;
	            this.element = this.driver.findElement(by);
	        }catch(Exception e){
	            System.out.print(e.getMessage());
	        }
	    }

	    public ITextField setText(String text){
	        try{
	            if(this.element.isDisplayed()){
	                element.sendKeys(text);
	                Report.log("Inserido o texto: " + text, ScreenShot.capture(driver));
	            }else{
	            	Report.logFail("Elemento não encontrado");
	            }
	        }catch (Exception e){
	            System.out.print(e.getMessage());
	        }
	        return this;
	    }
}
