package kj.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.ImageIcon;

/**
 * description: 工具类-加载图片
 * @author KONG
 */
public final class Picture {

	/**
	 * 图片文件所在包名
	 */
	private static final String PACKAGE_NAME = "kj/source/image/";

	/**
	 * 图片文件名
	 */
	private static final String[] pictures = { "0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png",
			"8.png", "9.png", "MiddleEnemy.png", "playerHPProgressBar.png", "bulletNormal.png", "smallEnemy.png",
			"icon.png", "background.png", "airPlane.png", "attack1.png", "attack2.png", "attack3.png", "attack4.png",
			"Explosion1.png", "Explosion2.png", "Explosion3.png", "Explosion4.png", "Explosion5.png", "Explosion6.png",
			"Explosion7.png", "jet1.png", "jet2.png", "CircleBullet.png", "bulletLevel_One.png", "BigEnemy.png",
			"BossEnemy.png", "welcome.png" };

	/**
	 * 存放图片实例集合
	 */
	private static final Map<String, ImageIcon> pictureMap;

	static {
		// 利用静态代码块进行初始化
		pictureMap = new HashMap<>();
		System.out.println("开始加载图片");
		// 加载所有图片
		for (int i = 0; i < pictures.length; i++) {
			loadImage(PACKAGE_NAME + pictures[i]);
			System.out.println("第" + (i+1) + "个图片文件：" + pictures[i]);
		}
		System.out.println("图片加载完毕");
	}

	/**
	 * 读取图片实例
	 * 
	 */
	private static ImageIcon loadImage(String filePath) {
		if (pictureMap.containsKey(filePath)) {
			return pictureMap.get(filePath);
		}
		ImageIcon pic = new ImageIcon(Objects.requireNonNull(Picture.class.getClassLoader().getResource(filePath)));
		return pictureMap.put(filePath, pic);
	}

	/**
	 * 通过名称获得图片实例
	 * 
	 */
	public static ImageIcon getImage(String name) {
		return loadImage(PACKAGE_NAME + name);
	}
}
