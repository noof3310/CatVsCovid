package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Medicine extends Entity implements Collectable {
	
	public Medicine(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Medicine.png").toString());
	}

	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.increaseHp(3);
	}

	

}
