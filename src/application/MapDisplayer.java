package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * class holds integer array and paint the map according to the value of the
 * index.
 * 
 * 
 * 
 * @author Asaf_Eliasim
 * @author Hadar_Shemesh
 *
 */

public class MapDisplayer extends Canvas {

	private final int row = 14;
	private final int col = 16;
	int[][] mapData;
	private double hRow;
	private double wColumn;
	private List<String[]> listStringData;
	private GraphicsContext gc;

	@SuppressWarnings("resource")
	public int[][] convertFromFile(File choose) {
		this.listStringData = new ArrayList<>();
		int[][] tempArray = new int[this.row][this.col];
		Scanner scan = null;
		try {
			scan = new Scanner(choose);
			while (scan.hasNext()) {
				// value = scan.nextLine().split(",");
				listStringData.add(scan.next().split(","));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan.close();
		int k = 0;
		while (k != listStringData.size() - 1) {
			System.out.println(listStringData.size());
			// while(!listStringData.isEmpty()){
			for (int i = 0; i < this.row - 1; i++, k++) {
				int j = 0;
				for (String s : listStringData.get(i)) {
					tempArray[i][j++] = Integer.parseInt(s);
					// listStringData.remove(s);
				}

			}
		}
		return tempArray;
	}

	public void setMapArray(int[][] data) throws Exception {
		this.mapData = data;

		paintMap();
	}

	public void paintMap() {
		this.gc = getGraphicsContext2D();
		Image plan = null;
		try {
			plan = new Image(new FileInputStream("/.resources/airplane.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (this.mapData != null) {
			this.hRow = getHeight();
			this.wColumn = getWidth();
			double blockWidth = wColumn / mapData[0].length;
			double blockHight = hRow / mapData.length;
			for (int i = 0; i < mapData.length; i++) {
				for (int j = 0; j < mapData[i].length; j++) {
					switch (mapData[i][j]) {
					case 0:
						gc.setFill(Color.web("#f90000"));
						break;
					case 1:
						gc.setFill(Color.web("#f9462a"));
						break;
					case 2:
						gc.setFill(Color.web("#f46049"));
						break;
					case 3:
						gc.setFill(Color.web("#ed8c47"));
						break;
					case 4:
						gc.setFill(Color.web("#e8d64e"));
						break;
					case 5:
						gc.setFill(Color.web("#e4f791"));
						break;
					case 6:
						gc.setFill(Color.web("#d7f455"));
						break;
					case 7:
						gc.setFill(Color.web("#dee050"));
						break;
					case 8:
						gc.setFill(Color.web("#93bf2d"));
						break;
					case 9:
						gc.setFill(Color.web("#a2ce8e"));
						break;
					case 10:
						gc.setFill(Color.web("#9dd882"));
						break;
					case 11:
						gc.setFill(Color.web("#82c663"));
						break;
					case 12:
						gc.setFill(Color.web("#0aaf26"));
						break;
					case 13:
						gc.setFill(Color.web("#15a82e"));
						break;
					case 14:
						gc.setFill(Color.web("#099e22"));
						break;
					case 15:
						gc.setFill(Color.web("#06812b"));
						break;
					default:
						break;
					}
					gc.fillRect(j * blockWidth, i * blockHight, blockWidth, blockHight);
					gc.drawImage(plan, j * blockWidth - 2, i * blockHight - 2, 0, 0);
					gc.setFill(Color.BLACK);
					gc.fillText("" + mapData[i][j], j * blockWidth + 4, i * blockHight + blockHight - 4);
				}
			}
		}
	}
}
