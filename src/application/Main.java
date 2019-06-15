package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.ViewModel;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			ViewModel vm = new ViewModel();
			FXMLLoader fxl = new FXMLLoader(getClass().getResource("View.fxml"));
			AnchorPane root = (AnchorPane)fxl.load();
			ViewController vc = fxl.getController();
			Scene scene = new Scene(root,700,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			vc.setViewModel(vm);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
