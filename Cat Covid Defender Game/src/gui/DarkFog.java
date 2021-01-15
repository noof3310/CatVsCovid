package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;

public class DarkFog extends Canvas {
	
	private GraphicsContext gc;
	private Image img;
	private int col;
	private int row;
	
	public DarkFog(int col, int row) {
		
		super(2135, 1785);
		img = new Image(ClassLoader.getSystemResource("img/Shadow.png").toString());
		gc = this.getGraphicsContext2D();
		gc.drawImage(img, 0, 0);
		setCol(col);
		setRow(row);
		
	}
	
	public void followingPlayer() {
		setCol(GameController.getPlayer().getCol());
		setRow(GameController.getPlayer().getRow());
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
		setTranslateX(col*35-1050);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
		setTranslateY(row*35-875);
	}
	

}
