package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Key extends Entity implements Collectable {
	
	public Key(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Key.png").toString());
	}

	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.setKeyCount(GameController.getKeyCount()+1);
	}

	
}
