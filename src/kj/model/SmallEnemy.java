package kj.model;

import kj.util.Picture;
import kj.util.PropertiesUnit;
import kj.view.PlaneInterface;

/**
 * description: 小型敌机
 * @author KONG
 */
public class SmallEnemy extends EnemyPlane {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5759928025482992543L;

	/**
	 * 构造方法
	 */
	public SmallEnemy() {
		super(Picture.getImage("smallEnemy.png"), PropertiesUnit.getValue("SmallEnemy.HP"),
				PropertiesUnit.getValue("SmallEnemy.speed"), PropertiesUnit.getValue("SmallEnemy.dropScore"),
				PropertiesUnit.getValue("SmallEnemy.intervalTime"));
	}

	@Override
	public void shoot(PlaneInterface planeInterface) {
		// 小型飞机无法发射子弹
	}

}
