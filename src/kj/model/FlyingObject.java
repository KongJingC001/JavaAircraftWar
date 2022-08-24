package kj.model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import kj.thread.PlayAnimation;
import kj.util.Sound;
import kj.view.CoreInterface;
import kj.view.PlaneInterface;

/**
 * description: 飞行物
 * @author KONG
 */
public abstract class FlyingObject extends JLabel {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7712848607301784551L;

	/**
	 * 是否为敌方单位，默认为非敌方
	 */
	private boolean isEnemy = false;

	/**
	 * 构造函数
	 */
	public FlyingObject(ImageIcon image) {
		this.setBodyImage(image);
		this.setSize(image.getIconWidth(), image.getIconHeight());
	}

	/**
	 * 两个飞行物是否发生碰撞
	 * 
	 */
	public boolean isCrashed(FlyingObject flyingObject) {
		// 横坐标判断
		if (this.getTerminalX() < flyingObject.getX() || this.getX() > flyingObject.getTerminalX()) {
			return false;
		}
		// 纵坐标判断
		if (this.getTerminalY() < flyingObject.getY() || this.getY() > flyingObject.getTerminalY()) {
			return false;
		}
		return true;
	}

	/**
	 * 播放单位消亡动画和音乐
	 * 
	 */
	public void playDeadAction(PlaneInterface planeInterface, String musicName, String animation, int frame) {
		// 播放音效
		Sound.playSound(musicName);
		// 播放动画
		PlayAnimation playAnimation = new PlayAnimation(planeInterface, animation, this.getX(), this.getY(), frame);
		playAnimation.start();
	}

	/**
	 * 设置图片
	 * 
	 */
	public void setBodyImage(ImageIcon image) {
		this.setIcon(image);
	}

	/**
	 * 判断是否越界
	 * 
	 */
	public boolean isOutOfBounds() {
		if (this.isEnemy) {
			return this.getY() > CoreInterface.HEIGHT;
		} else {
			return this.getTerminalY() < 0;
		}
	}

	@Override
	public int getX() {
		return super.getX();
	}

	/**
	 * 得到中点X坐标
	 * 
	 */
	public int getMiddleX() {
		return this.getX() + this.getWidth() / 2;
	}

	/**
	 * 得到终点X坐标
	 * 
	 */
	public int getTerminalX() {
		return this.getX() + this.getWidth();
	}

	/**
	 * 设置横坐标
	 * 
	 */
	public void setX(int X) {
		this.setLocation(X, this.getY());
	}

	@Override
	public int getY() {
		return super.getY();
	}

	/**
	 * 得到中点Y坐标
	 * 
	 */
	public int getMiddleY() {
		return this.getY() + this.getHeight() / 2;
	}

	/**
	 * 得到终点Y坐标
	 * 
	 */
	public int getTerminalY() {
		return this.getY() + this.getHeight();
	}

	/**
	 * 设置纵坐标
	 * 
	 */
	public void setY(int Y) {
		this.setLocation(this.getX(), Y);
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	public boolean isEnemey() {
		return isEnemy;
	}

	public void setEnemy() {
		this.isEnemy = true;
	}

}
