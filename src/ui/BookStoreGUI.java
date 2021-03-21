package ui;
import java.io.File;
import java.io.IOException;

import exceptions.InvalidCharacterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import model.Book;
import model.BookStoreManager;
import model.Client;
import model.Progressitem;
import thread.ProgressLoadingThread;

public class BookStoreGUI {

	private BookStoreManager b;
	private Progressitem pi;
	private int s;
	Image logoi;

	@FXML
	private BorderPane basePane;
	@FXML
	private TextField checkersTxt;
	@FXML
	private TextField ShelvesTxt;
	@FXML
	private TextField SlotsTxt;
	@FXML
	private Button nextButton;
	@FXML
	private TextField ShelveCodeTxt;
	@FXML
	private TextField PriceTxt;
	@FXML
	private TextField QuantityTxt;
	@FXML
	private TextField IDTxt;
	@FXML
	private TextField BookCodeTxt;
	@FXML
	private Label percentprogresslabel;
	@FXML
	private Rectangle progressfigure;
	@FXML
	private ImageView logo;
	@FXML
	private TextField ISBNCodetxt;
	@FXML
	private TextField Pricetxt;
	@FXML
	private TextField TitleTxt;
	@FXML
	private TextField InitialChaptTxT;
	@FXML
	private TextField ReviewTxt;
	@FXML
	private TextField ShelveIndicatorTxt;
	@FXML
	private TableView<Client> ClientTable;
	@FXML
	private TableColumn<Client,String> ClientIdC; 
	@FXML
	private TableColumn<Client,Integer> PriorC;
    @FXML
    private TableColumn<?, ?> TitleC;
    @FXML
    private TableColumn<?, ?> PriceC;
    @FXML
    private TableColumn<?, ?> ShelveC;
    @FXML
    private TableColumn<?, ?> ISBNC;
    @FXML
    private TableView<Book> BooksWithoutSortTable;


	public BookStoreGUI(BookStoreManager bo) {
		b = bo;
		pi = new Progressitem(); 
		s= 0 ;
	}

	@FXML
	public void toLoadProgressFigure() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("progressBar.fxml"));
		fxmlLoader.setController(this);
		Parent progressPane = fxmlLoader.load();
		basePane.getChildren().clear();
		logoi = new Image(new File("C:\\Users\\Asus\\eclipse-workspace\\book-store\\images\\Logo.png").toURI().toString());
		logo.setImage(logoi);
		basePane.setCenter(progressPane); 	
		pi.setLoading(true);
		new ProgressLoadingThread(pi,this).start();
	}

	@FXML 
	void AddBI1(ActionEvent event) {

		String ind = ShelvesTxt.getText();
		int slots = Integer.parseInt(SlotsTxt.getText());
		try {
			b.addShelve(ind, slots);
		} catch (InvalidCharacterException e) {

			showAlertWhenCharacterisInvalid();
		}
		showAlertWhenShelveIsAdded();

	}

	@FXML
	void AddBIB(ActionEvent event) {

		int checkers = Integer.parseInt(checkersTxt.getText());
		showAlertWhenCheckersAreAdded();

	}

	@FXML
	void addBI2(ActionEvent event) throws InvalidCharacterException {	

		String isbncode = ISBNCodetxt.getText();
		double price = Double.parseDouble(Pricetxt.getText());
		String title = TitleTxt.getText();
		String ichapt = InitialChaptTxT.getText();
		String review = ReviewTxt.getText();
		String shelvI = ShelveIndicatorTxt.getText();
		int quantity = Integer.parseInt(QuantityTxt.getText());

		try {
			b.addBookPerShelve(title, ichapt, review, isbncode, price, shelvI, quantity);

		} catch (InvalidCharacterException e) {

			showAlertWhenCharacterisInvalid();
		}

		if(!b.addBookPerShelve(title, ichapt, review, isbncode, price, shelvI, quantity)) {

			showAlertWhenShelveIsInvalid();
		}else {
			showAlertWhenBookIsAdded();
		}
	}

	@FXML
	void selectClientToAddBook(MouseEvent event) {
		
		String s = ClientTable.getSelectionModel().getSelectedItem().getId();
	}

	@FXML
	void addBI3(ActionEvent event) {
		String id =IDTxt.getText();
		if(!b.addClient(id)) {
			showAlertWhenIdIsRepeated();
		}else {
			showAlertWhenClientIsAdded();
			updateClientsTable();
		}   	
	}

	@FXML
	void addBI3B(ActionEvent event) {

	}
	public void loadBasicInfo1() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBookMenu.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo1 = fxmlLoader.load();
		basePane.setCenter(basicinfo1);
		s= 1;  	
	}

	public void loadBasicInfo2() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BasicInfo2.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo2 = fxmlLoader.load();
		basePane.setCenter(basicinfo2);
		s= 2;   	
	}
	public void loadBasicInfo3() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BasicInfo3.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo3 = fxmlLoader.load();
		basePane.setCenter(basicinfo3);
		s= 3;

	}

	public void loadSimulation() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo3 = fxmlLoader.load();
		basePane.setCenter(basicinfo3);
		s = 4; 	
	} 

	@FXML
	void next(ActionEvent event) throws IOException {
		if(s == 1) {	
			loadBasicInfo2();
		}else if(s ==2) {
			loadBasicInfo3();
		}else if(s== 3) {
			loadSimulation();
		}else {
			loadBasicInfo1();	
		}
	}

	public void updateBar() {

		percentprogresslabel.setText((pi.getNumberOfProgress()/3)+"%");
		progressfigure.setWidth(pi.getNumberOfProgress());
		if(pi.isLoading()==false) {
			try {
				loadBasicInfo1();
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
	}

	public void updateClientsTable() {

		ClientIdC.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
		PriorC.setCellValueFactory(new PropertyValueFactory<Client,Integer>("priorityTime"));
		ObservableList <Client> oblist;
		oblist = FXCollections.observableList(b.getInitialClientsList());
		ClientTable.setItems(oblist);
	}

	@FXML
	void Return(ActionEvent event) throws IOException {

		loadBasicInfo1();

	}

	public void showAlertWhenBookEx() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("Not Existence ERROR");
		alert.setContentText("You are trying to add a book that is not available, please check it");
		alert.showAndWait();

	}
	public void showAlertWhenCharacterisInvalid() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("Invalid Character ERROR");
		alert.setContentText("The shelve Indicator must be a valid character, please check it");
		alert.showAndWait();

	}

	public void showAlertWhenShelveIsInvalid() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("Unexisting Shelve ERROR");
		alert.setContentText("That shelve doesn't exist, please check it");
		alert.showAndWait();

	}

	public void showAlertWhenBookIsAdded() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("BOOK ADDED");
		alert.setContentText("The Book was correctly added to the specified shelve");
		alert.showAndWait();

	}
	public void showAlertWhenShelveIsAdded() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("SHELVE ADDED");
		alert.setContentText("The Shelve was correctly added");
		alert.showAndWait();

	}
	public void showAlertWhenCheckersAreAdded() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("CHECKERS ADDED");
		alert.setContentText("checkers were added correctly");
		alert.showAndWait();

	}
	public void showAlertWhenIdIsRepeated() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("INVALID ID ERROR");
		alert.setContentText("That id is already used");
		alert.showAndWait();

	}
	public void showAlertWhenClientIsAdded() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("CLIENT ADDED");
		alert.setContentText("Client was added succesfully");
		alert.showAndWait();

	}

}
