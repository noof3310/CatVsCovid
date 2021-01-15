package entity;

import entity.base.Entity;
import logic.Direction;

public class Player extends Entity {
	
	private boolean wearingSuit;
	private boolean holdingLantern;

	public Player(int col, int row) {
		super(col, row);
		setWearingSuit(false);
		setHoldingLantern(false);
		setImg(ClassLoader.getSystemResource("img/PlayerDown.png").toString());
	}
	
	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
		switch(direction) {
		case LEFT:
			if(!wearingSuit) setImg(ClassLoader.getSystemResource("img/PlayerLeft.png").toString());
			else setImg(ClassLoader.getSystemResource("img/PlayerLeftWithSuit.png").toString());
			break;
		case RIGHT:
			if(!wearingSuit) setImg(ClassLoader.getSystemResource("img/PlayerRight.png").toString());
			else setImg(ClassLoader.getSystemResource("img/PlayerRightWithSuit.png").toString());
			break;
		case UP:
			if(!wearingSuit) setImg(ClassLoader.getSystemResource("img/PlayerUp.png").toString());
			else setImg(ClassLoader.getSystemResource("img/PlayerUpWithSuit.png").toString());
			break;
		case DOWN:
			if(!wearingSuit) setImg(ClassLoader.getSystemResource("img/PlayerDown.png").toString());
			else setImg(ClassLoader.getSystemResource("img/PlayerDownWithSuit.png").toString());
			break;
		default:
			break;
		}
	}

	public boolean isWearingSuit() {
		return wearingSuit;
	}

	public void setWearingSuit(boolean wearingSuit) {
		this.wearingSuit = wearingSuit;
	}

	public boolean isHoldingLantern() {
		return holdingLantern;
	}

	public void setHoldingLantern(boolean holdingLantern) {
		this.holdingLantern = holdingLantern;
	}
	
	
}
