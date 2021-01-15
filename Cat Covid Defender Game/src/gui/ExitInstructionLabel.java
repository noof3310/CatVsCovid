package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ExitInstructionLabel extends Label {

	public ExitInstructionLabel() {
		super("Press Esc to Exit");
		this.setTextFill(Color.GHOSTWHITE);
		this.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));
		this.setFont(Font.font(25));
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
	}

}
