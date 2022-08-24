package kj.thread;

import kj.model.Player;

/**
 * description: 玩家飞机喷气
 * @author KONG
 */
public class PlayerJet extends Thread {

	/**
	 * 玩家飞机
	 */
	private final Player player;

	/**
	 * 当前播放的帧数
	 */
	private int frame = 1;

	/**
	 * 构造方法
	 * 
	 */
	public PlayerJet(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		// 玩家存活时喷气
		while (player.getHP() > 0) {
			setJet();
			// 改变当前帧数
			frame = frame == 1 ? 2 : 1;
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置喷气
	 */
	private void setJet() {
		player.setJetFrame(frame);
		player.setJetLocation(player.getMiddleX(), player.getTerminalY());
	}
}
