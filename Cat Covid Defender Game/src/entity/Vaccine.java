package entity;

import entity.base.Collectable;
import entity.base.Entity;
import logic.GameController;

public class Vaccine extends Entity implements Collectable {
	
	public Vaccine(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Vaccine.png").toString());
	}

	@Override
	public void collect() {
		GameController.removeEntity(this);
		GameController.setGame("Win");
	}

}
