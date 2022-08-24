package kj.controller;

import kj.model.EnemyPlane;
import kj.model.Player;
import kj.util.Sound;
import kj.view.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * description: 主控制器
 * @author KONG
 */
public class MainController {

	/**
	 * 构造方法
	 * 
	 */
	public MainController() {}

	/**
	 * 启动界面
	 */
	public void start() {
		SwingUtilities.invokeLater(() -> {
			System.out.println("界面启动成功！");
			// 播放欢迎音乐
			Sound.loopSound("welcome.wav");
			// 创建欢迎界面
			createWelcomeInterface();
			// 用户选择后欢迎背景音乐
			Sound.stopSound("welcome.wav");
			// 用户继续游戏，加载游戏界面
			CoreInterface coreInterface = loadGameInterface();
			// 播放背景音乐
			Sound.loopSound("backgroundMusic.wav");
			// 启动界面
			coreInterface.start();
		});
	}

	/**
	 * 创建欢迎界面GUI
	 */
	private void createWelcomeInterface() {
		StartInterface startInterface = new StartInterface();
		int opt = startInterface.start();
		// 用户是否开始游戏
		if (opt != StartInterface.START_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * 加载游戏界面
	 */
	private CoreInterface loadGameInterface() {
		// 初始化玩家飞机
		Player player = new Player();
		// 初始化敌机集合
		ArrayList<EnemyPlane> enemyPlaneList = new ArrayList<>();
		// 加载背景面板
		BackgroundInterface backgroundInterface = new BackgroundInterface();
		// 创建飞机层
		PlaneInterface planeInterface = new PlaneInterface(player, enemyPlaneList);
		// 创建结点层
		NodeInterface nodeInterface = new NodeInterface(player);
		// 创建承载面板
		CarryInterface carryInterface = new CarryInterface(backgroundInterface, planeInterface, nodeInterface);
		// 创建核心面板JFrame
		return new CoreInterface(carryInterface);
	}

}
