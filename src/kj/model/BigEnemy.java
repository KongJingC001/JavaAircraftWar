package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;
import kj.view.PlaneInterface;

/**
 * description: 大型敌机
 * @author KONG
 */
public class BigEnemy extends EnemyPlane {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5551115731778454130L;

	/**
	 * 计时器
	 */
	private int timer = 0;

	/**
	 * 停留时间
	 */
	private final static int STAY_TIME = 800;

	/**
	 * 构造方法
	 */
	public BigEnemy() {
		super(Picture.getImage("BigEnemy.png"), PropertiesUnit.getValue("BigEnemy.HP"),
				PropertiesUnit.getValue("BigEnemy.speed"), PropertiesUnit.getValue("BigEnemy.dropScore"),
				PropertiesUnit.getValue("BigEnemy.intervalTime"));
		setEnemy();
	}

	@Override
	public void shoot(PlaneInterface planeInterface) {
		if (canShoot()) {
			final int speed = PropertiesUnit.getValue("CircleBullet.speed");
			final int count = PropertiesUnit.getValue("BigEnemy.bullet.count");
			// 创建子弹
			for (int i = 0; i < count; i++) {
				CircleBullet bullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(), (i - count / 2) * speed / 2, speed);
				// 添加到集合中
				this.getBulletList().add(bullet);
				// 添加子弹到寄存器中
				planeInterface.getBulletList().add(bullet);
				// 添加到面板上
				planeInterface.add(bullet);
			}
		}
	}

	@Override
	public void move() {
		if (timer++ < STAY_TIME) {
			if (this.getTerminalY() <= 400) {
				this.setY(this.getY() + 1);
			}
		} else {
			this.setY(this.getY() - 1);
		}
		this.getHPProgressBar().setLocation(this.getX(), this.getY() + this.getHeight());
	}
}
