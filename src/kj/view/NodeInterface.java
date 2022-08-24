package kj.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kj.model.Player;
import kj.thread.RefreshScore;
import kj.util.Picture;

/**
 * description: 节点层
 * @author KONG
 */
public class NodeInterface extends JPanel {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6547616783307622997L;

	/**
	 * 分数面板集合
	 */
	private final ArrayList<JLabel> scorePanelList;

	/**
	 * 玩家
	 */
	private final Player player;

	/**
	 * 构造方法
	 * 
	 */
	public NodeInterface(Player player) {
		scorePanelList = new ArrayList<>();
		this.player = player;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		addNode();
	}

	/**
	 * 添加节点
	 */
	private void addNode() {
		// 添加血量条
		this.add(player.getHPProgressBar());
		this.add(player.getHPProgressBarBorder());
		monitorScore();
	}

	/**
	 * 监视分数
	 */
	private void monitorScore() {
		// 添加分数面板
		for (int i = 0; i < 9; i++) {
			JLabel label = new JLabel(Picture.getImage("0.png"));
			label.setBounds(250 + i * 21, 6, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
			// 添加到集合中
			scorePanelList.add(label);
		}
		// 添加到节点面板上
		for (JLabel jLabel : scorePanelList) {
			// 先设置所有面板不显示，当有分数之后开始显示
			jLabel.setVisible(false);
			this.add(jLabel);
		}
		// 启动监视分数线程
		RefreshScore refreshScore = new RefreshScore(this, player);
		refreshScore.start();
	}

	public ArrayList<JLabel> getScorePanelList() {
		return scorePanelList;
	}

}
