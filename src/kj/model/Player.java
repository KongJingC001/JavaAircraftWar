package kj.model;

import javax.swing.JLabel;

import kj.util.Picture;
import kj.util.PropertiesUnit;
import kj.view.CoreInterface;

/**
 * description: 玩家飞机
 * @author KONG
 */
public final class Player extends Aircraft {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 307049364274425932L;

	/**
	 * 玩家的击杀数
	 */
	private int killNumber = 0;

	/**
	 * 玩家的总分数
	 */
	private int score = 0;

	/**
	 * 血量条边框
	 */
	private JLabel HPProgressBarBorder;

	/**
	 * 尾气
	 */
	private final JLabel planeJet;

	/**
	 * 构造方法
	 */
	public Player() {
		super(Picture.getImage("airPlane.png"), PropertiesUnit.getValue("Player.HP"));
		planeJet = new JLabel(Picture.getImage("jet1.png"));
		this.setLocation(CoreInterface.WIDTH / 2, CoreInterface.HEIGHT / 5 * 4);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// 设置玩家的生命条和边框
		this.getHPProgressBar().setBounds(17, 14, 120, 12);
		this.HPProgressBarBorder = new JLabel(Picture.getImage("playerHPProgressBar.png"));
		this.HPProgressBarBorder.setBounds(0, 0, this.HPProgressBarBorder.getIcon().getIconWidth(),
				this.HPProgressBarBorder.getIcon().getIconHeight());
		// 设置飞机喷气
		planeJet.setSize(planeJet.getIcon().getIconWidth(), planeJet.getIcon().getIconHeight());
	}

	/**
	 * 得到血量条边框
	 * 
	 */
	public JLabel getHPProgressBarBorder() {
		return this.HPProgressBarBorder;
	}

	/**
	 * 得到尾气放置组件
	 * 
	 */
	public JLabel getPlaneJet() {
		return planeJet;
	}

	/**
	 * 设置帧数
	 * 
	 */
	public void setJetFrame(int frame) {
		this.planeJet.setIcon(Picture.getImage("jet" + frame + ".png"));
	}

	/**
	 * 设置尾气位置
	 * 
	 */
	public void setJetLocation(int X, int Y) {
		this.planeJet.setLocation(X - this.planeJet.getIcon().getIconWidth() / 2, Y);
	}

	/**
	 * 删除尾气
	 */
	public void deleteJet() {
		this.planeJet.setVisible(false);
	}

	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
		// 增加杀敌数
		addKillNumber();
	}

	public int getKillNumber() {
		return killNumber;
	}

	private void addKillNumber() {
		this.killNumber++;
	}

}
