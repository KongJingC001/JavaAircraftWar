package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;
import kj.view.PlaneInterface;

/**
 * description: 中型敌机
 * @author KONG
 */
public class MiddleEnemy extends EnemyPlane {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4545562797107973094L;

	/**
	 * 构造方法
	 */
	public MiddleEnemy() {
		super(Picture.getImage("MiddleEnemy.png"), PropertiesUnit.getValue("MiddleEnemy.HP"),
				PropertiesUnit.getValue("MiddleEnemy.speed"), PropertiesUnit.getValue("MiddleEnemy.dropScore"),
				PropertiesUnit.getValue("MiddleEnemy.intervalTime"));
		// 设置起始发射时间
		setFirstShootTime(PropertiesUnit.getValue("MiddleEnemy.shoot.firstTime"));
	}

	@Override
	public void shoot(PlaneInterface planeInterface) {
		if (canShoot()) {
			final int speed = PropertiesUnit.getValue("CircleBullet.speed");
			final int count = PropertiesUnit.getValue("MiddleEnemy.bullet.count");
			// 创建子弹
			for (int i = 0; i < count; i++) {
				CircleBullet bullet = new CircleBullet(this.getMiddleX(), this.getTerminalY(),
						(i - count / 2) * speed / 4, speed);
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
