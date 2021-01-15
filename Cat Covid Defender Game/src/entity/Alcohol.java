package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Alcohol extends Entity implements Collectable {
	
	public Alcohol(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Alcohol.png").toString());
	}

	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.setAlcoholCount(GameController.getAlcoholCount()+1);
	}

	
	
}