package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class HomePage extends Canvas{
	StringProperty homeFileName;
	public String getHomeFileName() {
		return homeFileName.get();
	}

	public void setHomeFileName(String homeFileName) {
		this.homeFileName.set(homeFileName);
	}

	Image homeBackRound = null;
	private double w;
	private double h;
	
	public HomePage() {
		this.homeFileName = new SimpleStringProperty();
		this.w = this.getWidth();
		this.h= this.getHeight();
		try {
			this.homeBackRound = new Image(new FileInputStream("./resources/homePage.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
