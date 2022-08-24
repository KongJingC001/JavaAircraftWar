package kj.view;

import javax.swing.JLayeredPane;

/**
 * description: 承载面板
 * @author KONG
 */
public class CarryInterface extends JLayeredPane {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8214737641720542644L;

	/**
	 * 背景面板
	 */
	private final BackgroundInterface backgroundInterface;

	/**
	 * 飞机层面板
	 */
	private final PlaneInterface planeInterface;

	/**
	 * 节点层
	 */
	private final NodeInterface nodeInterface;

	/**
	 * 构造方法
	 * 
	 */
	public CarryInterface(BackgroundInterface backgroundInterface, PlaneInterface planeInterface,
			NodeInterface nodeInterface) {
		this.backgroundInterface = backgroundInterface;
		this.planeInterface = planeInterface;
		this.nodeInterface = nodeInterface;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		System.out.println("承载面板初始化成功！");
		this.setSize(CoreInterface.WIDTH, CoreInterface.HEIGHT);
		this.setOpaque(false);
		addNode();
	}

	/**
	 * 添加节点
	 */
	private void addNode() {
		this.add(backgroundInterface, new Integer(0));
		this.add(planeInterface, new Integer(1));
		this.add(nodeInterface, new Integer(2));
	}

}
