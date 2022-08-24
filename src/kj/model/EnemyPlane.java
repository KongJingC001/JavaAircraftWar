package kj.model;

import javax.swing.ImageIcon;

import kj.view.CoreInterface;
import kj.view.PlaneInterface;

/**
 * description: 敌机
 * @author KONG
 */
public abstract class EnemyPlane extends Aircraft {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1231502673915315916L;

	/**
	 * 移动速度
	 */
	private final int speed;

	/**
	 * 掉落的分数
	 */
	private final int dropScore;

	/**
	 * 射击计时器
	 */
	private int timeCounter = 0;

	/**
	 * 发射间隔
	 */
	private final int intervalTime;

	/**
	 * 构造方法
	 * 
	 */
	public EnemyPlane(ImageIcon image, int HP, int speed, int dropScore, int intervalTime) {
		super(image, HP);
		this.speed = speed;
		this.dropScore = dropScore;
		this.intervalTime = intervalTime;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// 此单位为敌人
		setEnemy();
		// 设置刷新位置
		this.setLocation((int) (Math.random() * 9999) % (CoreInterface.WIDTH - this.getWidth()), -this.getHeight());
		// 设置进度条位置
		this.getHPProgressBar().setBounds(this.getX(), this.getY() + this.getHeight(), this.getWidth(), 10);
	}

	/**
	 * 敌机射击
	 */
	public abstract void shoot(PlaneInterface planeInterface);

	/**
	 * 是否能够发射子弹
	 */
	public boolean canShoot() {
		if (intervalTime == timeCounter) {
			// 归零计时器
			timeCounter = 0;
			return true;
		}
		// 计时器迭代
		timeCounter++;
		return false;
	}

	/**
	 * 设置首次发射时间
	 * 
	 */
	protected void setFirstShootTime(int time) {
		timeCounter = time;
	}

	/**
	 * 敌机的移动方法，如果需要更改移动方式，自行重写move()方法
	 */
	public void move() {
		// 移动敌机位置
		this.setY(this.getY() + this.getSpeed());
		// 同步设置血量条位置
		this.getHPProgressBar().setLocation(this.getX(), this.getY() + this.getHeight());
	}

	/**
	 * 得到敌机前进的速度
	 * 
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * 得到击杀该敌机的分数
	 * 
	 */
	public int getDropScore() {
		return dropScore;
	}

}
