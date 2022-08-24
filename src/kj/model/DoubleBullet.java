package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;

/**
 * description: 玩家的双发子弹
 * @author KONG
 */
public class DoubleBullet extends Bullet {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3229022057918326790L;

	/**
	 * 大号子弹
	 * 
	 */
	public DoubleBullet(int X, int Y) {
		super(Picture.getImage("bulletLevel_One.png"), X, Y, 0, PropertiesUnit.getValue("DoubleBullet.speed"),
				PropertiesUnit.getValue("DoubleBullet.harm"));
	}

}
