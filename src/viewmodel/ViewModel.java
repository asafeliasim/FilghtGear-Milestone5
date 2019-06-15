package viewmodel;

import java.io.EOFException;
import java.util.Observable;
import java.util.Observer;

import interpreter.Interpreter;
import interpreter.Interpreter.ParseException;
import interpreter.commands.ExecutionException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ViewModel extends Observable implements Observer {
	final String[] paths = {
			"/instrumentation/airspeed-indicator/indicated-speed-kt",
			"/instrumentation/altimeter/indicated-altitude-ft", 
			"/instrumentation/altimeter/pressure-alt-ft",
			"/instrumentation/attitude-indicator/indicated-pitch-deg",
			"/instrumentation/attitude-indicator/indicated-roll-deg",
			"/instrumentation/attitude-indicator/internal-pitch-deg",
			"/instrumentation/attitude-indicator/internal-roll-deg",
			"/instrumentation/encoder/indicated-altitude-ft",
			"/instrumentation/encoder/pressure-alt-ft",
			"/instrumentation/gps/indicated-altitude-ft",
			"/instrumentation/gps/indicated-ground-speed-kt",
			"/instrumentation/gps/indicated-vertical-speed",
			"/instrumentation/heading-indicator/indicated-heading-deg",//indicated-heading-deg
			"/instrumentation/magnetic-compass/indicated-heading-deg",
			"/instrumentation/slip-skid-ball/indicated-slip-skid",
			"/instrumentation/turn-indicator/indicated-turn-rate",
			"/instrumentation/vertical-speed-indicator/indicated-speed-fpm",
			"/controls/flight/aileron",
			"/controls/flight/elevator",
			"/controls/flight/rudder",
			"/controls/flight/flaps",
			"/controls/engines/current-engine/throttle",
			"/engines/engine/rpm",
			"/controls/flight/speedbrake",
			"/instrumentation/heading-indicator/offset-deg"
			};
	private Interpreter i;
	public StringProperty pilotText;
	public DoubleProperty throttle, rudder, aileron, elevator;
	//private Thread interThread = null;
	
	public ViewModel() {
		new Thread(()->{
			this.i = new Interpreter(paths);
		}).start();
		
		this.pilotText = new SimpleStringProperty();
		this.throttle = new SimpleDoubleProperty();
		this.rudder = new SimpleDoubleProperty();
		this.aileron = new SimpleDoubleProperty();
		this.elevator = new SimpleDoubleProperty();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	public void loadScript() throws ExecutionException, ParseException, EOFException {
		String[] line = this.pilotText.getValue().split("\n");
		for(int i =0 ; i< line.length ; i++) {
			System.out.println("Line P: " + line[i]);
			this.i.getLine(line[i]);
		}
	}
	public void getThrottleValue() throws EOFException {
		try {
			this.i.getLine("throttle = " + this.throttle.get());
		} catch (ExecutionException | ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	public void getRudderValue() throws EOFException {
		try {
			this.i.getLine("rudder = " + this.rudder.get());
		} catch (ExecutionException | ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	public void getAilronValue() {
		try {
			this.i.getLine("aileron = " + this.aileron.get());
		} catch (EOFException | ParseException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getElevatorValue() {
		try {
			this.i.getLine("elevator = " + this.elevator.get());
		} catch (EOFException | ParseException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
