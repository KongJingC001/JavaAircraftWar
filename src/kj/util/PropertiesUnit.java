package kj.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * description: 配置器
 * @author KONG
 */
public class PropertiesUnit {

	/**
	 * Properties对象
	 */
	private static final Properties properties;

	static {
		// 预加载信息
		System.out.println("正在加载配置文件");
		properties = new Properties();
		try {
			String filePath = new File(".").getAbsolutePath().replace(".", "") + "Aircraft.properties";
			properties.load(Files.newInputStream(Paths.get(filePath)));
			System.out.println("成功加载配置文件" + filePath);
			//properties.load(PropertiesUnit.class.getClassLoader().getResourceAsStream(currentDir));
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "程序读取出现异常，游戏文件被删除或遭到非法修改！\n无法正常游戏！即将退出!", "错误",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}

	private static String get(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 得到所需的值
	 * 
	 */
	public static int getValue(String key) {
		int value = Integer.MIN_VALUE;
		try {
			value = Integer.parseInt(get(key));
			// System.out.println("KEY:" + key + " | VALUE:" + value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "程序读取出现异常，游戏文件被删除或遭到非法修改！\n无法正常游戏！即将退出!", "错误",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		return value;
	}

}
