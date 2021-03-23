package ui;
import java.io.File;
import java.io.IOException;
import java.util.List;
import exceptions.EmptyQueueException;
import exceptions.InvalidCharacterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	private TextField QuantityTxt;
	@FXML
	private TextField IDTxt;
	@FXML
	private TextField BookCodeTxt1;
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
	private TableColumn<Client, String> ClientC;
	@FXML
	private TableColumn<Client,List<String>> BooksWSC;
	@FXML
	private TableView<Client> BooksWithoutSortTable;
	@FXML
	private Label clientSelectedIdLabel;
	private String saveId;
	@FXML
	private TableView<Client> SimulationTable;
	@FXML
	private TableColumn<Client, String> ReportC;
	@FXML
	private CheckBox insertionSortCheckBox;
	@FXML
	private CheckBox heapSortCheckBox;
	@FXML
	private CheckBox countingSortCheckBox;
	@FXML
	private TextField clientToSort;
	@FXML
	private Label Report;

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
		cleanBasicInfo1Text();

	}

	@FXML
	void AddBIB(ActionEvent event) {

		int checkers = Integer.parseInt(checkersTxt.getText());
		b.setCashiers(checkers);
		showAlertWhenCheckersAreAdded();
		cleanCashersText();

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
		boolean operation = false;

		try {
			operation = b.addBookPerShelve(title, ichapt, review, isbncode, price, shelvI, quantity);

		} catch (InvalidCharacterException e) {

			showAlertWhenCharacterisInvalid();
		}

		if(operation == false) {

			showAlertWhenShelveIsInvalid();
		}else {
			showAlertWhenBookIsAdded();
		}
		cleanBasicInfo2Text();
	}

	@FXML
	void selectClientToAddBook(MouseEvent event) {

		if(ClientTable.getSelectionModel().getSelectedItem() != null) {
			String s = ClientTable.getSelectionModel().getSelectedItem().getId();
			clientSelectedIdLabel.setText(s);
			saveId = s;
		}
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
		cleanBasicInfo3IDText();
	}

	public void updateTableWithInitialBooks() {

		ObservableList <Client> ob;
		ob = FXCollections.observableList(b.getClientsList());
		BooksWithoutSortTable.setItems(ob);
		ClientC.setCellValueFactory(new PropertyValueFactory<Client,String>("id"));
		BooksWSC.setCellValueFactory(new PropertyValueFactory<Client,List<String>>("clientBooksList"));

	}

	@FXML
	void addBI3B(ActionEvent event) throws InvalidCharacterException {

		String s = BookCodeTxt1.getText();
		String id = saveId;
		Client c = b.searchClient(id);
		String info = "";

		try {
			info = b.addAndCheckBooksToClientBookList(c, s);

		} catch (InvalidCharacterException e) {
			showAlertWhenCharacterisInvalid();
		}
		if(info.equalsIgnoreCase("")) { 

			BooksWithoutSortTable.refresh();
			showAlertWhenInitialBookAddedToClient();
			updateTableWithInitialBooks();

		}else {
			showAlertWhenBookIsNotAvailable();
		}
		cleanBasicInfo3BookCodeText();
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
	public void loadBasicInfo3() throws IOException, InvalidCharacterException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BasicInfo3.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo3 = fxmlLoader.load();
		basePane.setCenter(basicinfo3);
		s= 3;
	}

	public void updateClients() throws InvalidCharacterException {

		List<Client> c = b.clientCountingSort(b.getClientsList());
		b.setClientsList(c);

	}

	public void loadSimulation() throws IOException, InvalidCharacterException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Show.fxml"));
		fxmlLoader.setController(this);
		Parent basicinfo3 = fxmlLoader.load();
		basePane.setCenter(basicinfo3);
		s = 4;

	} 

	public void booksToBag() throws InvalidCharacterException {

		for(int i = 0; i<b.getClientsList().size();i++) {

			b.booksToBag(b.getClientsList().get(i));
		}
	}

	@FXML
	void next(ActionEvent event) throws IOException, InvalidCharacterException {
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

		ObservableList <Client> oblist;
		oblist = FXCollections.observableList(b.getClientsList());
		ClientTable.setItems(oblist);
		ClientIdC.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
		PriorC.setCellValueFactory(new PropertyValueFactory<Client,Integer>("priorityTime"));

	} 

	@FXML
	void sortByCounting(ActionEvent event) throws InvalidCharacterException {
		if(countingSortCheckBox.isSelected()) {

			Client c = b.searchClient(clientToSort.getText());
			sortByCountingISBN(c);
			heapSortCheckBox.setDisable(true);
			insertionSortCheckBox.setDisable(true); 

		}else {
			heapSortCheckBox.setDisable(false);
			countingSortCheckBox.setDisable(false);		
		}
	}

	public void sortByCountingISBN(Client c) throws InvalidCharacterException {
		c.setClientBooksList(b.countingSort(c.getClientBooksList()));


	}

	public void sortByInsertionISBN(Client c) throws InvalidCharacterException {
		c.setClientBooksList(b.insertionSort(c.getClientBooksList()));
	}
	public void sortByHeapISBN(Client c) throws InvalidCharacterException {
		c.setClientBooksList(b.heapSort(c.getClientBooksList()));

	}

	@FXML
	void sortByHeap(ActionEvent event) throws InvalidCharacterException {
		if(heapSortCheckBox.isSelected()) {

			Client c = b.searchClient(clientToSort.getText());
			sortByHeapISBN(c);
			countingSortCheckBox.setDisable(true);
			insertionSortCheckBox.setDisable(true);
		}else {

			insertionSortCheckBox.setDisable(false);
			countingSortCheckBox.setDisable(false);
		}
	}

	@FXML
	void sortByInsertion(ActionEvent event) throws InvalidCharacterException {
		if(insertionSortCheckBox.isSelected()) {
			Client c = b.searchClient(clientToSort.getText());
			sortByInsertionISBN(c);
			countingSortCheckBox.setDisable(true);
			heapSortCheckBox.setDisable(true);
		}else {

			heapSortCheckBox.setDisable(false);
			countingSortCheckBox.setDisable(false);
		}
	}

	@FXML
	void Simulate(ActionEvent event) throws EmptyQueueException, CloneNotSupportedException, InvalidCharacterException {
		booksToBag();
		b.clientsToQueue(b.clientCountingSort(b.getClientsList()));
		b.payBooks();
		String s = b.finalReport(); 
		Report.setText(s);

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
	public void showAlertWhenInitialBookAddedToClient() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("CODE ADDED");
		alert.setContentText("Book code was succesfully added to client Initial list");
		alert.showAndWait();

	}
	public void showAlertWhenBookIsNotAvailable() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("Unexisting Book ERROR");
		alert.setContentText("The book you are trying to add to your list is not available or doesn't even exist on the bookstore, check it");
		alert.showAndWait();
	}

	public void cleanCashersText() {

		checkersTxt.clear();
	}

	public void cleanBasicInfo1Text() {

		ShelvesTxt.clear();
		SlotsTxt.clear();
	}

	public void cleanBasicInfo2Text() {

		ShelveIndicatorTxt.clear();
		Pricetxt.clear();
		QuantityTxt.clear();
		ISBNCodetxt.clear();
		TitleTxt.clear();
		InitialChaptTxT.clear();
		ReviewTxt.clear();
	}

	public void cleanBasicInfo3IDText() {

		IDTxt.clear();

	}

	public void cleanBasicInfo3BookCodeText() {

		BookCodeTxt1.clear();
	}
}
