package ui;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import model.BookStoreManager;
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
	private TextField BooksNumberTxt;

	@FXML
	private Button nextButton;

	@FXML
	private TextField ShelveCodeTxt;

	@FXML
	private TextField PriceTxt;

	@FXML
	private TextField QuantityTxt;

	@FXML
	private TextField ClientsNumberTxt;

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

    }
   
    @FXML
    void addBI2(ActionEvent event) {

    }
    
    @FXML
    void addBI3(ActionEvent event) {
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

	@FXML
	void Return(ActionEvent event) throws IOException {

		loadBasicInfo1();

	}
}
