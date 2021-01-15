package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Lantern extends Entity implements Collectable {
	
	public Lantern(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Lantern.png").toString());
	}

	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.getPlayer().setHoldingLantern(true);
	}

	
}
