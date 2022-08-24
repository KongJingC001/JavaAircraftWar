package kj.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kj.thread.RollBackground;
import kj.util.Picture;

/**
 * description: 背景面板
 * @author KONG
 */
public class BackgroundInterface extends JPanel {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5485849719889847141L;

	/**
	 * 承载图片的容器
	 */
	private JLabel background1, background2;

	/**
	 * 构造方法
	 */
	public BackgroundInterface() {
		System.out.println("背景面板初始化成功！");
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		setBackground();
		setRoller();
		addNode();
	}

	/**
	 * 设置背景组件
	 */
	private void setBackground() {
		background1 = new JLabel(Picture.getImage("background.png"));
		background2 = new JLabel(Picture.getImage("background.png"));
	}

	/**
	 * 设置滚动器
	 */
	private void setRoller() {
		// 设置初始位置
		background1.setBounds(0, -(background1.getIcon().getIconHeight() - 889), background1.getIcon().getIconWidth(),
				background1.getIcon().getIconHeight());
		background2.setBounds(0, -(background1.getIcon().getIconHeight() - 889) - background2.getIcon().getIconHeight(),
				background2.getIcon().getIconWidth(), background2.getIcon().getIconHeight());

		// 开始滚动
		RollBackground rollBackground = new RollBackground(this);
		rollBackground.start();
	}

	/**
	 * 添加节点
	 */
	private void addNode() {
		this.add(background1);
		this.add(background2);
	}

	public JLabel getBackground1() {
		return background1;
	}

	public JLabel getBackground2() {
		return background2;
	}
}
