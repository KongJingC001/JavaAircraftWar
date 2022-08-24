package kj.view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import kj.util.Picture;

/**
 * description: 核心面板
 * @author KONG
 */
public class CoreInterface extends JFrame {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6093400824874332396L;

	/**
	 * 窗口宽度
	 */
	public static final int WIDTH = 500;
	/**
	 * 窗口高度
	 */
	public static final int HEIGHT = 889;

	/**
	 * 承载面板
	 */
	private final CarryInterface carryInterface;

	/**
	 * 构造方法
	 */
	public CoreInterface(CarryInterface carryInterface) {
		this.carryInterface = carryInterface;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		setBlankCursor();
		this.setIconImage(Picture.getImage("icon.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("飞机大战");
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		addNode();
	}

	/**
	 * 将光标删除
	 */
	private void setBlankCursor() {
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		// Set the blank cursor to the JFrame.
		this.getContentPane().setCursor(blankCursor);
	}

	/**
	 * 显示节点
	 */
	private void addNode() {
		this.getContentPane().add(carryInterface);
	}

	/**
	 * 启动界面
	 */
	public void start() {
		this.setVisible(true);
	}

}
