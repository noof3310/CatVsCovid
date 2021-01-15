package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

public class HBoxForAlcohol extends HBox{
	
	private ImageView img;
	private Label label;

	public HBoxForAlcohol() {
		
		super();
		
		this.setPadding(new Insets(10));
		this.setSpacing(20);
		
		img = new ImageView(ClassLoader.getSystemResource("img/Alcohol.png").toString());
		img.setFitHeight(50);
		img.setPreserveRatio(true);
		
		label = new Label();
		label.setText("x " + GameController.getAlcoholCount());
		
		label.setTextFill(Color.BLACK);
		label.setFont(Font.font(50));
		label.setAlignment(Pos.CENTER);
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(img, label);

	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
