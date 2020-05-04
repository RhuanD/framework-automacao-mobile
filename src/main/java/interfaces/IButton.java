package interfaces;

import io.appium.java_client.SwipeElementDirection;
/**
 * Interface implementada pela classe Button.
 * */
public interface IButton {
	/**
	 * Verifica se o elemento está aparecendo na tela, e após isso, se ele estiver aparecendo, o pressiona. Caso o contrário, apresenta uma exceção.
	 * */
	IButton click();
	
	/**
	 * Verifica se o elemento está aparecendo na tela, e após isso, se ele estiver aparecendo, o move para a direção passada pelo usuário. Caso o contrário, apresenta uma exceção.
	 * */
    IButton swipe(SwipeElementDirection var1, int var2);
    
    /**
     * Verifica se o elemento existe e é disposto na tela.
     * */
    boolean exists();
}
