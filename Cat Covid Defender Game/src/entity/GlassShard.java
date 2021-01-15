package entity;

import entity.base.Entity;
import entity.base.HurtPlayerable;
import logic.GameController;

public class GlassShard extends Entity implements HurtPlayerable {
	
	public GlassShard(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/GlassShard.png").toString());
	}

	@Override
	public boolean hit() {
		if(!GameController.getPlayer().isWearingSuit()) {
			GameController.increaseHp(-1);	
			return true;
		}else return false;
	}

}
