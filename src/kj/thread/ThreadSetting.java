package kj.thread;

/**
 * description: 控制线程
 * @author KONG
 */
public final class ThreadSetting {
	
	/**
	 * 背景滚动线程
	 */
	public static boolean is_interrupt_RollBackground = false;
	
	/**
	 * 刷新线程
	 */
	public static boolean is_interrupt_RefreshThread = false;
	
	/**
	 * 刷新分数线程
	 */
	public static boolean is_interrupt_RefreshScore = false;
	
	/**
	 * 玩家射击线程
	 */
	public static boolean is_interrupt_PlayerShoot = false;
	
	/**
	 * 敌机射击线程
	 */
	public static boolean is_interrupt_EnemyShoot = false;
	
	/**
	 * 创建敌机线程
	 */
	public static boolean is_interrupt_CreateEnemy = false;
	
	/**
	 * 玩家移动线程
	 */
	public static boolean is_interrupt_player_motion = false;
	
	
	public static boolean is_interrupt_crash = false;
	/**
	 * 暂停线程
	 */
	public static void pause() {
		// 关闭背景滚动、分数刷新、玩家射击、玩家移动、碰撞检测
		is_interrupt_RollBackground = true;
		is_interrupt_RefreshScore = true;
		is_interrupt_PlayerShoot = true;
		is_interrupt_player_motion = true;
		is_interrupt_crash = true;
	}
	
	/**
	 * 开始线程
	 */
	public static void start() {
		is_interrupt_RollBackground = false;
		is_interrupt_RefreshThread = false;
		is_interrupt_RefreshScore = false;
		is_interrupt_PlayerShoot = false;
		is_interrupt_EnemyShoot = false;
		is_interrupt_CreateEnemy = false;
	}
	
	//不允许实例化
	private ThreadSetting() {}
}
