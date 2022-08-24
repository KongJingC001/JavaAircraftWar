package kj.thread;

import javax.swing.JLabel;

import kj.util.Picture;
import kj.view.PlaneInterface;

/**
 * description: 播放动画
 * @author KONG
 */
public class PlayAnimation extends Thread {

	/**
	 * 播放动画的位置
	 */
	private final PlaneInterface planeInterface;

	/**
	 * 动画名
	 */
	private final String name;

	/**
	 * 播放动画的坐标
	 */
	private final int X;
	private final int Y;

	/**
	 * 动画帧数
	 */
	private final int frame;

	/**
	 * 动画图片
	 */
	private JLabel[] animations;

	/**
	 * 构造方法
	 * 
	 */
	public PlayAnimation(PlaneInterface planeInterface, String name, int X, int Y, int frame) {
		this.planeInterface = planeInterface;
		this.name = name;
		this.X = X;
		this.Y = Y;
		this.frame = frame;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		animations = new JLabel[frame];
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new JLabel(Picture.getImage(name + (i + 1) + ".png"));
			animations[i].setBounds(X, Y, animations[i].getIcon().getIconWidth(),
					animations[i].getIcon().getIconHeight());
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < frame; i++) {
			play(i);
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 播放单帧
	 * 
	 */
	private void play(int i) {
		planeInterface.add(animations[i]);
		try {
			sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		planeInterface.remove(animations[i]);
	}

}
