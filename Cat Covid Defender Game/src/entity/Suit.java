package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Suit extends Entity implements Collectable {
	
	public Suit(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Suit.png").toString());
	}
	
	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.changePlayerSuit();
	}

}
