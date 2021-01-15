package entity;

import entity.base.Entity;
import entity.base.Impassable;

public class Door extends Entity implements Impassable {
	
	public Door(int col, int row) {
		super(col, row);
		setImg(ClassLoader.getSystemResource("img/Door.png").toString());
	}

}
