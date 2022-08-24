package kj.model;

import javax.swing.ImageIcon;

import kj.util.Picture;
import kj.util.PropertiesUnit;

/**
 * description: 玩家的普通子弹
 * @author KONG
 */
public class NormalBullet extends Bullet {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7891978016422452789L;

	/**
	 * 加载图片
	 */
	private static final ImageIcon imageIcon = Picture.getImage("bulletNormal.png");
	/**
	 * 获取速度
	 */
	private static final int speed =	PropertiesUnit.getValue("NormalBullet.speed");
	/**
	 * 获取伤害
	 */
	private static final int harm = PropertiesUnit.getValue("NormalBullet.harm");
	
	/**
	 * 构造方法
	 * 
	 */
	public NormalBullet(int X, int Y) {
		super(imageIcon, X, Y, 0, speed, harm);
	}

}
