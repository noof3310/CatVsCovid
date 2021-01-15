package gui;


import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameController;

public class SideMenuPane extends VBox {
	
	//about color here
	//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
	
	private RestartInstructionLabel restartLabel;
	private ExitInstructionLabel exitLabel;
	private ProgressBar hpBar;
	private Text hpText;
	private HBoxForAlcohol alcoholBox;
	private HBoxForKey keyBox;
	
	public SideMenuPane() {
		
		setAlignment(Pos.TOP_CENTER);
		setPadding(new Insets(20));
		setPrefWidth(250);
		setSpacing(30);
		
		setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
		restartLabel = new RestartInstructionLabel();
		exitLabel = new ExitInstructionLabel();
		
		hpBar = new ProgressBar();
		hpBar.setProgress(1);
		hpBar.prefWidth(500);
		hpBar.prefHeight(500);
		
		hpText = new Text();
		hpText.setFont(new Font(30));
		
		alcoholBox = new HBoxForAlcohol();
		keyBox = new HBoxForKey();
		
		this.getChildren().addAll(restartLabel, exitLabel, hpBar, hpText, alcoholBox, keyBox);
		
		AnimationTimer t = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				update();				
			}
		};
		
		t.start();
		
	}
	
	private void update() {
		hpBar.setProgress(GameController.getHp()/GameController.getMaxHp());
		hpText.setText("HP: " +Integer.toString((int)GameController.getHp())+"/"+(Integer.toString((int)GameController.getMaxHp())));
		alcoholBox.getLabel().setText("x " + GameController.getAlcoholCount());
		keyBox.getLabel().setText("x " + GameController.getKeyCount());
	}

}
