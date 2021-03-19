package thread;

import javafx.application.Platform;
import model.Progressitem;
import ui.BookStoreGUI;

public class ProgressLoadingThread extends Thread{
	

	private Progressitem progi;
	private BookStoreGUI gui;

	
	public ProgressLoadingThread(Progressitem bar, BookStoreGUI bs) {
		progi = bar;
		gui= bs;
	}
	
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
