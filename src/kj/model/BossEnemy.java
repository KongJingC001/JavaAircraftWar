package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;
import kj.view.PlaneInterface;

/**
 * description: Boss敌机
 * @author KONG
 */
public class BossEnemy extends EnemyPlane {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3988481739625012528L;

	/**
	 * 构造方法
	 */
	public BossEnemy() {
		super(Picture.getImage("BossEnemy.png"), PropertiesUnit.getValue("BossEnemy.HP"),
				PropertiesUnit.getValue("BossEnemy.speed"), PropertiesUnit.getValue("BossEnemy.dropScore"),
				PropertiesUnit.getValue("BossEnemy.intervalTime"));
		setEnemy();
	}

	@Override
	public void shoot(PlaneInterface planeInterface) {
		if (canShoot()) {
			final int speed = PropertiesUnit.getValue("CircleBullet.speed");
			final int count = PropertiesUnit.getValue("BossEnemy.bullet.count");
			// 创建向下子弹
			for (int i = 0; i < count; i++) {
				CircleBullet bullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(),
						(i - count / 2) * speed / 2, speed);
				// 添加到集合中
				this.getBulletList().add(bullet);
				// 添加子弹到寄存器中
				planeInterface.getBulletList().add(bullet);
				// 添加到面板上
				planeInterface.add(bullet);
			}
			// 创建水平子弹
			CircleBullet leftBullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(), speed, 0);
			CircleBullet rightBullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(), speed, 0);
			// 添加到集合中
			this.getBulletList().add(leftBullet);
			this.getBulletList().add(rightBullet);
			// 添加子弹到寄存器中
			planeInterface.getBulletList().add(leftBullet);
			planeInterface.getBulletList().add(rightBullet);
			// 添加到面板上
			planeInterface.add(leftBullet);
			planeInterface.add(rightBullet);
			// 创建向上子弹
			for (int i = 0; i < count; i++) {
				CircleBullet bullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(),
						(i - count / 2) * speed / 2, -speed);
				// 添加到集合中
				this.getBulletList().add(bullet);
				// 添加子弹到寄存器中
				planeInterface.getBulletList().add(bullet);
				// 添加到面板上
				planeInterface.add(bullet);
			}
		}
	}

}
