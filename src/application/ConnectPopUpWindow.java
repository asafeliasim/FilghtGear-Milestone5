package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ConnectPopUpWindow extends Application {

	//MyInterpreter mi;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button connect = new Button("OK");
		connect.setTranslateX(110);
    	connect.setTranslateY(70);
		
    	Button cancel = new Button("Cancel");
		cancel.setTranslateX(-110);
		cancel.setTranslateY(70);
        
		Label LabelPort = new Label("Port: ");
		LabelPort.setTranslateX(-100);
		LabelPort.setTranslateY(-40);
		
		Label LabelIp = new Label("IP: ");
		LabelIp.setTranslateX(-100);
		LabelIp.setTranslateY(-80);
		
		Label LabelListing = new Label("Listing Port:");
		LabelListing.setTranslateX(-115);
		LabelListing.setTranslateY(0);
		
		TextField portText = new TextField();
		portText.setPromptText("Type Port here");
		portText.setMaxSize(180, 10);
		portText.setTranslateX(10);
		portText.setTranslateY(-40);
		
		TextField ipText = new TextField();
		ipText.setPromptText("Type IP here");
		ipText.setMaxSize(180, 10);
		ipText.setTranslateX(10);
		ipText.setTranslateY(-80);
		
		TextField listingText = new TextField();
		listingText.setPromptText("Wait for simulator port");
		listingText.setMaxSize(180, 10);
		listingText.setTranslateX(10);
		listingText.setTranslateY(0);
		
		Label pError = new Label("Invaild Port, please try again");
		pError.setVisible(false);
		pError.setTranslateX(0);
		pError.setTranslateY(50);
		
		Label IpError = new Label("Invaild IP, please try again");
		IpError.setVisible(false);
		IpError.setTranslateX(0);
		IpError.setTranslateY(30);
		
		
		connect.setOnAction(new EventHandler<ActionEvent>() {
            @SuppressWarnings("static-access")
			@Override
            public void handle(ActionEvent event) {
            	if(Integer.parseInt(portText.getText()) < 0 || Double.parseDouble(portText.getText()) > 65000 )
            		pError.setVisible(true);
            	if(portText.getText().equals(""))
            		pError.setVisible(true);
            	if(ipText.getText().equals(""))
            		IpError.setVisible(true);
            	
            	String openServer = new String("openDataServer"+" "+portText.getText()+""+10);
            	String connect = new String("connect"+" "+ ipText.getText()+" "+ portText.getText());
            	String[] args = new String[2];
            	args[0] = connect;
            	args[1]= openServer;
            	
            //	mi.interpret(args);
            }
		});
          
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
			
		});
        StackPane root = new StackPane();
        root.getChildren().add(connect);
        root.getChildren().add(cancel);
        root.getChildren().add(LabelPort);
        root.getChildren().add(LabelIp);
        root.getChildren().add(LabelListing);
        root.getChildren().add(portText);
        root.getChildren().add(ipText);
        root.getChildren().add(listingText);
        root.getChildren().add(pError);
        root.getChildren().add(IpError);
        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Connect to server");
        primaryStage.setScene(scene);
        
        primaryStage.show();

      
	}
	public void openDataServer() {
     	
    }
	
}
