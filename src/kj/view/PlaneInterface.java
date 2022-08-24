package kj.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import kj.model.Bullet;
import kj.model.EnemyPlane;
import kj.model.Player;
import kj.thread.RefreshThread;
import kj.thread.ThreadSetting;
import kj.thread.PlayerJet;

/**
 * description: 飞机层
 * @author KONG
 */
public class PlaneInterface extends JPanel {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 629824114141827023L;

	/**
	 * 玩家飞机
	 */
	private final Player player;

	/**
	 * 敌机集合
	 */
	private final ArrayList<EnemyPlane> enemyPlaneList;

	/**
	 * 子弹集合，用于飞机爆炸时，子弹仍然自己存在
	 */
	private final ArrayList<Bullet> bulletList;

	/**
	 * 构造方法
	 */
	public PlaneInterface(Player player, ArrayList<EnemyPlane> enemyPlaneList) {
		System.out.println("飞机层初始化成功！");
		this.player = player;
		this.enemyPlaneList = enemyPlaneList;
		bulletList = new ArrayList<>();
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		this.setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
		this.setOpaque(false);
		this.setLayout(null);
		addPlane();
	}

	/**
	 * 添加飞机
	 */
	private void addPlane() {
		// 添加玩家
		this.add(player);
		// 设置玩家组件
		setPlayer();
		// 刷新线程
		RefreshThread refreshThread = new RefreshThread(player, this);
		refreshThread.start();
	}

	/**
	 * 监听玩家移动和射击
	 */
	private void setPlayer() {
		// 监听玩家位置
		this.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// 如果没有中断还可以移动
				if (!ThreadSetting.is_interrupt_player_motion) {
					// 普通移动
					player.setLocation(e.getX() - player.getWidth() / 2, e.getY() - player.getHeight() / 2);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// 如果没有中断还可以移动
				if (!ThreadSetting.is_interrupt_player_motion) {
					// 按压时移动
					player.setLocation(e.getX() - player.getWidth() / 2, e.getY() - player.getHeight() / 2);
				}
			}

		});
		// 设置玩家喷气
		PlayerJet playerJet = new PlayerJet(player);
		this.add(player.getPlaneJet());
		playerJet.start();
	}

	/**
	 * 得到玩家飞机
	 * 
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 敌机集合
	 * 
	 */
	public ArrayList<EnemyPlane> getEnemyPlaneList() {
		return enemyPlaneList;
	}

	/**
	 * 得到所有子弹集合
	 * 
	 */
	public ArrayList<Bullet> getBulletList() {
		return bulletList;
	}

}
