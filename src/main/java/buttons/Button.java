package buttons;

import interfaces.IButton;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidElement;
import reports.Report;
import reports.ScreenShot;


public class Button implements IButton {
	private AppiumDriver<AndroidElement> driver;
    private AndroidElement element;
    /**
     * Classe de implementação da interface IButton
     * */
    public Button(AppiumDriver<AndroidElement> driver, By by){
        try {
            this.driver = driver;
            this.element = this.driver.findElement(by);
        } catch (TimeoutException var4) {
        	Report.logFail("Elemento não encontrado");
        }
    }

    public IButton click(){
        try{
            if(this.element.isDisplayed()){
                this.element.click();
                Report.log("Elemento Clicado", ScreenShot.capture(this.driver));
            }
        }catch (Exception e){
        	Report.logFail("Falha ao clicar no botão");
        }
        return this;
    }

    public IButton swipe(SwipeElementDirection var1, int var2){
        try{
            if(this.element.isDisplayed()){
                this.element.swipe(var1, var2);
                Report.log("Elemento movido com sucesso", ScreenShot.capture(this.driver));
            }
        }catch (Exception e){
        	Report.logFail("Elemento não foi movido.");
        }
        return this;
    }

    public boolean exists(){
//    	try {
//			if(this.element.isDisplayed()) {
//                Report.log("Elemento existente na tela.");
//			    return true;
//			}
//		} catch (Exception e) {
//			Report.logFail("Elemento inexistente.");
//			return false;
//		}
//    	return this;
        if(this.element.isDisplayed()) {
            Report.log("Elemento existente na tela.");
            return true;
        }else{
            Report.log("Elemento não existente na tela.");
            return false;
        }
    }
}
