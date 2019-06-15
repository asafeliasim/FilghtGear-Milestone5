package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AsafMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\eclipse-workspace\\flightGearApplication\\resources\\myMap.csv");
		try {
			Scanner s = new Scanner(f);
			while(s.hasNext()) {
				String data = s.next();
				String [] val = data.split(",");
				System.out.println(val[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
