package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.exception.GameStatusNotFoundException;

public class GameFinishedCanvas extends Canvas {
	
	private GraphicsContext gc = this.getGraphicsContext2D();
	private Image image_Over;
	private Image image_Win;
	
	public GameFinishedCanvas(double width, double height, String status) throws GameStatusNotFoundException {
		
		setWidth(width);
		setHeight(height);
		
		//width = 1050,height = 875
		
		image_Over = new Image(ClassLoader.getSystemResource("img/Game_Over.png").toString());
		image_Win = new Image(ClassLoader.getSystemResource("img/Win_Pic.png").toString());
		
		switch(status) {
		case "Win":
			gc.drawImage(image_Win, 0, 0);
			break;
		case "Lose":
			gc.drawImage(image_Over, 0, 0);
			break;
		default:
			throw new GameStatusNotFoundException(status);
		}
		
	}
	
}
