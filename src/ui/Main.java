package ui;

import java.util.ArrayList;

import exceptions.InvalidCharacterException;
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
//		launch(args);
		
		ArrayList<String> books = new ArrayList<>();
		books.add("6545");
		books.add("9485");
		books.add("1654");
		books.add("3154");
		books.add("8464");
		try {
			for (int i = 0; i < books.size(); i++) {
				System.out.println(books.get(i));
			}
			System.out.println();
			CountingSort(books);
		} catch (InvalidCharacterException e) {
			e.printStackTrace();
		}
	}
	
	public static void CountingSort(ArrayList<String> isbnList) throws InvalidCharacterException {
		int n = isbnList.size();
		int[] A = new int[n]; // 1
        int k = 0; // 1
        for (int i = 0; i < n; i++) { // n+1
            int value =  Integer.parseInt(isbnList.get(i));// n
            A[i] = value; // n
            if (k < value) // n
                k = value; // n
        }
        int[] C = new int[k + 1]; // 1
        for (int i = 0; i <= k; i++) // k+2
            C[i] = 0; // k+1
        for (int i = 0; i < n; i++) // n+1
            C[A[i]] = C[A[i]] + 1; // n
        for (int i = 1; i <= k; i++) { // k+1
            C[i] = C[i] + C[i - 1]; // k
        }
        int[] B = new int[n]; // 1
        for (int i = n - 1; i >= 0; i--) { // n+1
            B[C[A[i]] - 1] = A[i]; // n
            C[A[i]] = C[A[i]] - 1; // n
        }
        for (int i = 0; i < B.length; i++) { // n+1
            System.out.println(B[i]);
        }		
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
