package gui;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GamePane extends Pane{

	public GamePane() {
		
		setPrefSize(1050, 875);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		
	}

}
