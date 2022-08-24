package kj.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 工具类-加载声音及播放
 * @author KONG
 */
public final class Sound {

	/**
	 * 声音文件所在包名
	 */
	private static final String PACKAGE_NAME = "kj/source/sound/";

	/**
	 * 声音文件名
	 */
	private static final String[] sounds = { "backgroundMusic.wav", "hit.wav", "boom.wav", "selfHit.wav", "welcome.wav" };

	/**
	 * 存放声音实例集合
	 */
	private static final Map<String, AudioClip> soundMap;


	static {
		//预加载音乐
		soundMap = new HashMap<>();
		System.out.println("开始加载音乐文件");
		// 加载所有声音
		for (String sud : sounds) {
			loadSound(PACKAGE_NAME + sud);
			System.out.println("音乐文件：" + sud);
		}
		System.out.println("音乐文件加载完毕！");
	}

	/**
	 * 读取声音实例
	 * 
	 */
	private static AudioClip loadSound(String filePath) {
		if (soundMap.containsKey(filePath)) {
			return soundMap.get(filePath);
		}
		AudioClip audio = Applet.newAudioClip(Sound.class.getClassLoader().getResource(filePath));
		return soundMap.put(filePath, audio);
	}

	/**
	 * 通过名称获得声音实例
	 * 
	 */
	private static AudioClip getSound(String name) {
		return loadSound(PACKAGE_NAME + name);
	}

	/**
	 * 单次播放音乐
	 * 
	 */
	public static void playSound(String name) {
		getSound(name).play();
	}

	/**
	 * 循环播放音乐
	 * 
	 */
	public static void loopSound(String name) {
		getSound(name).loop();
	}

	/**
	 * 停止音乐
	 * 
	 */
	public static void stopSound(String name) {
		getSound(name).stop();
	}
}
