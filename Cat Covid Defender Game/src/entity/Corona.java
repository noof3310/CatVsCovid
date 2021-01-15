package entity;

import entity.base.Entity;
import entity.base.HurtPlayerable;
import logic.Direction;
import logic.GameController;

public class Corona extends Entity implements HurtPlayerable {
	
	private boolean alive;
	
	public Corona(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/CoronaLeft.png").toString());
		setAlive(true);
	}
	
	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
		switch(direction) {
		case LEFT:
			setImg(ClassLoader.getSystemResource("img/CoronaLeft.png").toString());
			break;
		case RIGHT:
			setImg(ClassLoader.getSystemResource("img/CoronaRight.png").toString());
			break;
		default:
			break;
		}
	}

	@Override
	public boolean hit() {
		
		setAlive(false);
		GameController.removeEntity(this);

		if(!GameController.useAlcohol()) {
			GameController.increaseHp(-3);
			return true;
		}else return false;

	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
	
	
	
}
