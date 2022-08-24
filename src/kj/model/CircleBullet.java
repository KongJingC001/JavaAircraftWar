package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;

/**
 * description: 圆形子弹：基础敌机子弹
 * @author KONG
 */
public class CircleBullet extends Bullet {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1602063467418714412L;

	/**
	 * 构造方法
	 * 
	 */
	public CircleBullet(int X, int Y, int speed_X, int speed_Y) {
		super(Picture.getImage("CircleBullet.png"), X, Y, speed_X, speed_Y,
				PropertiesUnit.getValue("CircleBullet.harm"));
		// 敌方子弹
		this.setEnemy();
	}

}
