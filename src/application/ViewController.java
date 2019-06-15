package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Scanner;

import interpreter.Interpreter.ParseException;
import interpreter.commands.ExecutionException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import viewmodel.ViewModel;


public class ViewController implements Observer{
	
	private ViewModel vm = null;
	DoubleProperty aileronVal, elevatorVal;
	//------------------------ FXML Objects----------------------------------------------// 
	@FXML
	private MapDisplayer MapDisplayer;
	@FXML
	AnchorPane Manual,Home,AutoPilot,MapPane,PopUpConnect;
	@FXML
	TextArea scriptPane;
	@FXML
	ImageView image;
	@FXML
	ConnectPopUpWindow cp;
	@FXML
	Circle joyStick;
	@FXML
	Slider throttle , rudder;
	@FXML
	Label boundry;
	
	
	public ViewController() {
		this.MapDisplayer = new MapDisplayer();
	}
	public void setViewModel(ViewModel v) {
		this.vm = v;
		this.aileronVal = new SimpleDoubleProperty();
		this.elevatorVal = new SimpleDoubleProperty();
		this.vm.throttle.bind(this.throttle.valueProperty());
		this.vm.rudder.bind(this.rudder.valueProperty());
		this.vm.aileron.bind(this.aileronVal);
		this.vm.elevator.bind(this.elevatorVal);
		this.vm.addObserver(this);
	}
	
	public void LoadScript() {
		FileChooser f = new FileChooser();
		f.setTitle("Select CSV File");
		f.setInitialDirectory(new File("./resources"));
		File chosen = f.showOpenDialog(null);
		if(chosen != null) 
			this.ShowText(chosen);		
	}
	public void loadMap() {
		FileChooser f = new FileChooser();
		f.setTitle("map");
		f.setInitialDirectory(new File("./resources"));
		File chosen = f.showOpenDialog(null);
		if(chosen != null) 
			this.ShowText(chosen);
		int[][] values = MapDisplayer.convertFromFile(chosen);
		try {
			MapDisplayer.setMapArray(values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void runScriptPressed() {
		String line = this.scriptPane.getText();
		this.vm.pilotText.setValue(line);
		try {
			this.vm.loadScript();
		} catch (EOFException | ExecutionException | ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		
	}
	public void ManualDisplay() {
		Manual.setVisible(true);
		MapPane.setVisible(false);
		AutoPilot.setVisible(false);
	}
	public void AutoDisplay() {
		Manual.setVisible(false);
		MapPane.setVisible(false);
		AutoPilot.setVisible(true);
	}
	public void HomeDisplay() {
		
		Manual.setVisible(true);
		MapPane.setVisible(false);
		AutoPilot.setVisible(false);
		
	}
	public void MapDisplay() {
		Manual.setVisible(false);
		MapPane.setVisible(true);
		AutoPilot.setVisible(false);
	}
	public void ConnectDisplay() {
		PopUpConnect.setVisible(true);
		Manual.setVisible(false);
		MapPane.setVisible(true);
		AutoPilot.setVisible(false);
	}
	public void ShowText(File f) {
		scriptPane.setWrapText(true);
		try {
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				scriptPane.appendText(s.nextLine() + "\n\r");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ConnectWindow() {
		cp= new ConnectPopUpWindow();
		try {
			cp.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void joyStickMove(MouseEvent ej) {
		if((ej.getX() < 100 && ej.getY() < 100) && (ej.getX() > -100 && ej.getY() > -100) ) {
		joyStick.setCenterX(ej.getX());
		joyStick.setCenterY(ej.getY());
		this.aileronVal.setValue(ej.getX()/100);
		this.elevatorVal.setValue(ej.getY()/(-100));
		this.vm.getAilronValue();
		this.vm.getElevatorValue();
		}
		else
			boundryError();
	}
	public void backToTheCenter() {
		joyStick.setCenterX(0);
		joyStick.setCenterY(0);
	}

	public void boundryError() {
		new Thread(()->{
			boundry.setVisible(true);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boundry.setVisible(false);
		}).start();
		
		Thread.interrupted();
	}
	/*
	public void moveAileronSlider(MouseEvent ej) {
		if(ej.getX() >= 11 && ej.getX() <= 330)
			this.rudder.setBlockIncrement(ej.getX());
		//System.out.println(ej.getX());
	}
	public void moveElevatorSlider(MouseEvent ej) {
		if(ej.getY() >= 0 && ej.getY() <= 100)
		{
			this.throttle.setBlockIncrement(ej.getY());
			System.out.println(ej.getY());
		}
	}
	*/
	public void throttleIsDrag(MouseEvent me) {
		try {
			this.vm.getThrottleValue();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rudderIsDrag(MouseEvent me) {
		try {
			this.vm.getRudderValue();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
