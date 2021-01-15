package gui;
import javafx.scene.layout.HBox;
import logic.GameController;
import logic.exception.MapCodeInvalidException;

public class GameRoot extends HBox {
	
	private SideMenuPane sideMenu;

	public GameRoot() {
		
		
		try {
			
			GameController.InitializeGameBoard("map/Map1.txt");
			
			sideMenu = new SideMenuPane();
			
			this.getChildren().addAll(GameController.getGamePane(),sideMenu);
			
		} catch (MapCodeInvalidException e) {
			System.out.println("Map Code Invalid.");
		}
		
	}
	
	public SideMenuPane getSideMenu() {
		return sideMenu;
	}
	
	
}