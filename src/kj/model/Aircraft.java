package kj.model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

/**
 * description: 飞机类
 * @author KONG
 */
public abstract class Aircraft extends FlyingObject {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -358065639760550879L;

	/**
	 * 生命值
	 */
	private int HP;
	/**
	 * 生命显示条
	 */
	private JProgressBar HPProgressBar;
	/**
	 * 子弹集合
	 */
	private final ArrayList<Bullet> bulletList;

	/**
	 * 构造方法
	 * 
	 */
	public Aircraft(ImageIcon image, int HP) {
		super(image);
		this.HP = HP;
		bulletList = new ArrayList<>();
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.HPProgressBar = new JProgressBar(0, this.HP);
		this.HPProgressBar.setStringPainted(true);
		this.HPProgressBar.setValue(this.HP);
		this.HPProgressBar.setBorderPainted(false);
		this.HPProgressBar.setForeground(Color.RED);
	}

	/**
	 * 被击中
	 */
	public int hited(int harm) {
		this.setHP(this.getHP() - harm);
		return this.getHP();
	}

	/**
	 * 获得飞机血量
	 * 
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * 设置飞机血量
	 * 
	 */
	public void setHP(int HP) {
		this.HP = HP;
		this.HPProgressBar.setValue(HP);
	}

	/**
	 * 该飞机的子弹集合
	 * 
	 */
	public synchronized ArrayList<Bullet> getBulletList() {
		return bulletList;
	}

	/**
	 * 飞机生命条
	 * 
	 */
	public synchronized JProgressBar getHPProgressBar() {
		return HPProgressBar;
	}

}
