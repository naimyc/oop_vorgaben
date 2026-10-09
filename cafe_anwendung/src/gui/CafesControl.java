package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.CafeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.CafeViewer;

public class CafesControl {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblName 					= new Label("Name:");
    private Label lblOrt   		 			= new Label("Ort:");
    private Label lblBeschreibung  	 	 	= new Label("Beschreibung:");
    private Label lblMitBaeckerei   		= new Label("Mit Baeckerei:");
    private Label lblKaffeeprodukte  		= new Label("Kaffeeprodukte:");
    private TextField txtName 	 			= new TextField();
    private TextField txtOrt				= new TextField();
    private TextField txtBeschreibung		= new TextField();
    private TextField txtMitBaeckerei		= new TextField();
    private TextField txtKaffeeprodukte	 	= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ cafe
    private CafeModel cafe;
    
    public CafesControl(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Cafes");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblBeschreibung.setLayoutX(20);
    	lblBeschreibung.setLayoutY(170);
    	lblMitBaeckerei.setLayoutX(20);
    	lblMitBaeckerei.setLayoutY(210);
    	lblKaffeeprodukte.setLayoutX(20);
    	lblKaffeeprodukte.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblOrt, lblBeschreibung,
       		lblMitBaeckerei, lblKaffeeprodukte);
    
    	// Textfelder
     	txtName.setLayoutX(170);
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtBeschreibung.setLayoutX(170);
    	txtBeschreibung.setLayoutY(170);
    	txtBeschreibung.setPrefWidth(200);
      	txtMitBaeckerei.setLayoutX(170);
    	txtMitBaeckerei.setLayoutY(210);
    	txtMitBaeckerei.setPrefWidth(200);
    	txtKaffeeprodukte.setLayoutX(170);
    	txtKaffeeprodukte.setLayoutY(250);
    	txtKaffeeprodukte.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtName, txtOrt, txtBeschreibung,
     		txtMitBaeckerei, txtKaffeeprodukte);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeCafeAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeCafeAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeCafesInCsvDatei();
			}	
	    });
    }
    
    private void nehmeCafeAuf(){
    	try{ 
    		this.cafe = new CafeModel(
    			txtName.getText(), 
   	            txtOrt.getText(),
   	            txtBeschreibung.getText(),
   	        	txtMitBaeckerei.getText(),
    		    txtKaffeeprodukte.getText().split(";"));
    		zeigeInformationsfensterAn("Das Cafe wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    private void zeigeCafeAn(){
    	if(this.cafe != null){
    		txtAnzeige.setText(
    			this.cafe.gibCafeZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde keine Cafe aufgenommen!");
    	}
    }    
		  
    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Cafe.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.cafe = new CafeModel(zeile[0], 
      				zeile[1], 
      				zeile[2], 
      				zeile[3], 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Das Cafe wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	private void schreibeCafesInCsvDatei() {
		try {
			BufferedWriter aus = new BufferedWriter(new FileWriter("cafesAusgabe.csv"));
			aus.write(this.cafe.gibCafeZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Cafes wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new CafeViewer(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new CafeViewer(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
