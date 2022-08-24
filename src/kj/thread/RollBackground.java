package kj.thread;

import kj.view.BackgroundInterface;

/**
 * description: 控制背景滚动
 * @author KONG
 */
public class RollBackground extends Thread {

	/**
	 * 承载背景的面板
	 */
	private final BackgroundInterface backgroundInterface;

	/**
	 * 构造方法
	 * 
	 */
	public RollBackground(BackgroundInterface backgroundInterface) {
		this.backgroundInterface = backgroundInterface;
	}

	@Override
	public void run() {
		while (!ThreadSetting.is_interrupt_RollBackground) {
			roll();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println("背景出现异常！程序退出！");
				System.exit(0);
			}
		}
	}

	/**
	 * 滚动背景面板
	 */
	private void roll() {
		// 向下滚动
		backgroundInterface.getBackground1().setLocation(backgroundInterface.getBackground1().getX(),
				backgroundInterface.getBackground1().getY() + 1);
		backgroundInterface.getBackground2().setLocation(backgroundInterface.getBackground2().getX(),
				backgroundInterface.getBackground2().getY() + 1);
		// 如果超出边界，则重新设置
		if (backgroundInterface.getBackground1().getY() >= backgroundInterface.getHeight()) {
			backgroundInterface.getBackground1().setLocation(0,
					-(backgroundInterface.getBackground2().getIcon().getIconHeight() - 889)
							- backgroundInterface.getBackground2().getIcon().getIconHeight());
		}
		if (backgroundInterface.getBackground2().getY() >= backgroundInterface.getHeight()) {
			backgroundInterface.getBackground2().setLocation(0,
					-(backgroundInterface.getBackground1().getIcon().getIconHeight() - 889)
							- backgroundInterface.getBackground1().getIcon().getIconHeight());
		}
	}

}
