package thread;

import javafx.application.Platform;
import model.Progressitem;
import ui.BookStoreGUI;

public class ProgressLoadingThread extends Thread{
	

	private Progressitem progi;
	private BookStoreGUI gui;

	/**
	 * ProgressLoadingThread constructor
	 * @param bar the progress item
	 * @param bs the gui
	 */
	public ProgressLoadingThread(Progressitem bar, BookStoreGUI bs) {
		progi = bar;
		gui= bs;
	}
	
	/**
	 * This method make the progress bar advance
	 */
	public void run() {
		while (progi.isLoading()) {
			progi.advance();
			Platform.runLater(new Thread() {
				public void run() {
					gui.updateBar();
				}
			});
			try {
				Thread.sleep(9);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
}
