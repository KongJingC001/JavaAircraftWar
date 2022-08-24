package kj.model;

import javax.swing.ImageIcon;

/**
 * description: 子弹类
 * @author KONG
 */
public abstract class Bullet extends FlyingObject {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4145902976797711295L;

	/**
	 * 子弹的速度分量
	 */
	private final int speed_X, speed_Y;

	/**
	 * 子弹的
	 */
	private final int harm;

	/**
	 * 构造方法
	 * 
	 */
	public Bullet(ImageIcon image, int X, int Y, int speed_X, int speed_Y, int harm) {
		super(image);
		this.setLocation(X - this.getWidth() / 2, Y - this.getHeight());
		this.speed_X = speed_X;
		this.speed_Y = speed_Y;
		this.harm = harm;
	}

	/**
	 * 子弹飞行
	 */
	public void move() {
		this.setLocation(this.getX() + this.speed_X, this.getY() + this.speed_Y);
	}

	public int getHarm() {
		return harm;
	}

}
