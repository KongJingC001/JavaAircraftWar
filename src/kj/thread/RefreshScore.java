package kj.thread;

import kj.model.Player;
import kj.util.Picture;
import kj.view.NodeInterface;

/**
 * description: 刷新玩家分数
 * @author KONG
 */
public class RefreshScore extends Thread {

	/**
	 * 放置节点的界面
	 */
	private final NodeInterface nodeInterface;

	/**
	 * 玩家
	 */
	private final Player player;

	/**
	 * 构造方法
	 * 
	 */
	public RefreshScore(NodeInterface nodeInterface, Player player) {
		this.nodeInterface = nodeInterface;
		this.player = player;
	}

	@Override
	public void run() {
		while (!ThreadSetting.is_interrupt_RefreshScore) {
			refresh();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 刷新
	 */
	private void refresh() {
		// 捕获玩家分数
		int scoreNum = player.getScore();
		String scoreStr = String.valueOf(scoreNum);
		// 设置玩家分数图片
		for (int i = 0; i < scoreStr.length(); i++) {
			// 获取当前位的数字
			int num = scoreStr.charAt(scoreStr.length() - 1 - i) - '0';
			// 设置当前位的分数
			nodeInterface.getScorePanelList().get(nodeInterface.getScorePanelList().size() - 1 - i)
					.setIcon(Picture.getImage(num + ".png"));
			// 显示该位置面板
			nodeInterface.getScorePanelList().get(nodeInterface.getScorePanelList().size() - 1 - i).setVisible(true);
		}
	}

}
