package entity;

import entity.base.Entity;
import entity.base.Impassable;

public class Wall extends Entity implements Impassable {

	public Wall(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Wall.png").toString());
	}

}
