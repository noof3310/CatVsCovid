package application;
	
import gui.GameRoot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import logic.GameController;
import logic.Direction;

public class Main extends Application {
	
	private GameRoot gameRoot;

	public void start(Stage PrimaryStage) {
		
		gameRoot = new GameRoot();
		
		Scene gameScene = new Scene(gameRoot);
		
		PrimaryStage.setTitle("Cat VS Corona");
		PrimaryStage.setScene(gameScene);
		
		PrimaryStage.sizeToScene();
		PrimaryStage.setResizable(false);
		PrimaryStage.show();
		
		addEventListener(gameScene, PrimaryStage); 
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void addEventListener(Scene s, Stage PrimaryStage) {
		
		s.setOnKeyPressed(e -> {
			KeyCode keycode = e.getCode();
			
			switch(keycode) {
			
			case LEFT:
				GameController.movePlayer(Direction.LEFT);
				break;
			case RIGHT:
				GameController.movePlayer(Direction.RIGHT);
				break;
			case UP:
				GameController.movePlayer(Direction.UP);
				break;
			case DOWN:
				GameController.movePlayer(Direction.DOWN);
				break;
			case R:
				start(PrimaryStage);
				break;
			case ESCAPE:
				Platform.exit();
				System.exit(0);
				break;				
			default:
				System.out.println("Invalid Key.");
				break;
			}
			
		});
	
	}
	
}