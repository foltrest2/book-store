package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BookStoreManager;

public class Main extends Application {

	private BookStoreGUI initialInterfaceGUI;
	private BookStoreManager bookStore;
	
	public Main() {
		bookStore = new BookStoreManager();
		initialInterfaceGUI = new BookStoreGUI(bookStore);
	}
	
	public static void main(String[] args) {
//				launch(args);
		System.out.println(123%11);
		System.out.println(345%11);
		System.out.println(678%11);
		System.out.println(91011%11);
		System.out.println(111213%11);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basePane.fxml"));
		fxmlLoader.setController(initialInterfaceGUI);
		Parent root = fxmlLoader.load();
		initialInterfaceGUI.toLoadProgressFigure();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book Store");
		primaryStage.show();
	}

}
