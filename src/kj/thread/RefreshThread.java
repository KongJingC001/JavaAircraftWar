package kj.thread;

import javax.swing.JOptionPane;

import kj.model.*;
import kj.util.PropertiesUnit;
import kj.view.PlaneInterface;

import java.util.ArrayList;

/**
 * description: 刷新线程
 *
 * @author KONG
 */
public class RefreshThread extends Thread {

    /**
     * 玩家
     */
    private final Player player;

    /**
     * 读取撞击伤害
     */
    private final int crashedHarm = PropertiesUnit.getValue("Crash.harm");

    /**
     * 承载飞机的面板
     */
    private final PlaneInterface planeInterface;


    /**
     * 大型敌机计数器
     */
    private int bigEnemyCounter = 0;

    /**
     * Boss敌机计数器
     */
    private int bossEnemyCounter = 0;

    /**
     * 刷新敌机计时器
     */
    private final int[] enemyTimeCounter = {0, 0};

    /**
     * 刷新间隔
     */
    private final int[] enemyTimeInterval = {100, 200};

    /**
     * Boss敌机
     */
    private BossEnemy bossEnemy;

    /**
     * 大型敌机刷新所需分数
     */
    private final int scoreBigEnemy = PropertiesUnit.getValue("BigEnemy.present.score");

    /**
     * Boss敌机刷新所需歼敌数
     */
    private final int numberBossEnemy = PropertiesUnit.getValue("BossEnemy.present.killNumber");

    /**
     * 计数器时间
     */
    private int playerBulletTimeCounter = 0;

    /**
     * 时间间隔
     */
    private final static int PLAYER_BULLET_TIME_INTERVAL = 16;

    /**
     * 待删除子弹寄存器
     */
    private final ArrayList<Bullet> deleteBulletList = new ArrayList<>();

    /**
     * 待删除敌机寄存器
     */
    private final ArrayList<EnemyPlane> deleteEnemyList = new ArrayList<>();

    /**
     * 构造方法
     */
    public RefreshThread(Player player, PlaneInterface planeInterface) {
        this.player = player;
        this.planeInterface = planeInterface;
    }

    @Override
    public void run() {
        while (!ThreadSetting.is_interrupt_RefreshThread) {
            try {
                if (!ThreadSetting.is_interrupt_CreateEnemy)
                    createEnemy();
                if (!ThreadSetting.is_interrupt_EnemyShoot)
                    enemyShoot();
                if (!ThreadSetting.is_interrupt_PlayerShoot)
                    playerShoot();
                if (!ThreadSetting.is_interrupt_crash) {
                    crash();
                }
                refresh();
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建敌机
     */
    private void createEnemy() {
        // 当前地图没有Boss才可以刷新小型、中型、大型敌机，包括Boss
        if (!planeInterface.getEnemyPlaneList().contains(bossEnemy)) {
            for (int i = 0; i < enemyTimeInterval.length; i++) {
                if (enemyTimeCounter[i] == enemyTimeInterval[i]) {
                    // 创建不同类型的敌机
                    if (i == 0)
                        addEnemy(new SmallEnemy());
                    else if (i == 1)
                        addEnemy(new MiddleEnemy());
                    enemyTimeCounter[i] = 0;
                } else {
                    enemyTimeCounter[i]++;
                }
            }
            // 获取当前大型敌机出现过的数量，分数达到限度，刷新一个大型敌机
            int bigEnemyCount = planeInterface.getPlayer().getScore() / scoreBigEnemy;
            // 需要刷一个大型敌机
            if (bigEnemyCount > bigEnemyCounter) {
                addEnemy(new BigEnemy());
                // 迭代当前计数器
                bigEnemyCounter++;
            }
            // 获取当前大型敌机出现过的数量
            // 击敌数达到限度，出现Boss，此时暂停其它飞机的刷新
            int bossEnemyCount = planeInterface.getPlayer().getKillNumber() / numberBossEnemy;
            if (bossEnemyCount > bossEnemyCounter) {
                // 创建一个Boss
                bossEnemy = new BossEnemy();
                addEnemy(bossEnemy);
                bossEnemyCounter++;
            }
        } // 关闭if
    } // 关闭createEnemy()方法

    /**
     * 添加敌机
     */
    private void addEnemy(EnemyPlane enemyPlane) {
        // 添加到集合中
        planeInterface.getEnemyPlaneList().add(enemyPlane);
        // 为敌机添加血量条
        planeInterface.add(enemyPlane.getHPProgressBar());
        // 添加敌机到飞机层面板
        planeInterface.add(enemyPlane);
    }


    /**
     * 敌机射击
     */
    private void enemyShoot() {
        for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
            enemyPlane.shoot(planeInterface);
        }
    }

    /**
     * 处理子弹刷新和移动
     */
    private void playerShoot() {
        if (playerBulletTimeCounter == PLAYER_BULLET_TIME_INTERVAL) {
            createPlayerBullet();
            playerBulletTimeCounter = 0;
        } else {
            playerBulletTimeCounter += 1;
        }
    }

    /**
     * 创建子弹
     */
    private void createPlayerBullet() {
        Bullet bullet;
        // 更改高级子弹
        if (player.getScore() >= 1000 || player.getKillNumber() >= 150) {
            bullet = new DoubleBullet(player.getMiddleX(), player.getY());
        } else {
            bullet = new NormalBullet(player.getMiddleX(), player.getY());
        }
        // 添加子弹到飞机集合中
        player.getBulletList().add(bullet);
        // 添加子弹到寄存器中
        planeInterface.getBulletList().add(bullet);
        // 添加子弹到面板上
        planeInterface.add(bullet);
    }


    /**
     * 敌机移动
     */
    private void enemyMove() {
        for (int i = 0; i < planeInterface.getEnemyPlaneList().size(); i++) {
            planeInterface.getEnemyPlaneList().get(i).move();
        }
    }

    /**
     * 碰撞检测
     */
    private void crash() {
        // 玩家子弹和敌机碰撞检测
        for (Bullet bullet : player.getBulletList()) {
            for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
                // 发生碰撞
                if (bullet.isCrashed(enemyPlane)) {
                    // 播放击中声效
                    bullet.playDeadAction(planeInterface, "hit.wav", "attack", 4);
                    // 敌机被击中
                    int surplusHP = enemyPlane.hited(bullet.getHarm());
                    // 敌机剩余血量不充足
                    if (surplusHP <= 0) {
                        enemyDispose(enemyPlane);
                        break;
                    }
                    // 先从面板删除该子弹
                    planeInterface.remove(bullet);
                    // 添加到待删除寄存器中
                    deleteBulletList.add(bullet);
                }
            }
        }
        // 敌机子弹和玩家检测
        for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
            for (Bullet bullet : enemyPlane.getBulletList()) {
                if (bullet.isCrashed(player)) {
                    // 玩家被击中，播放被击中音效
                    bullet.playDeadAction(planeInterface,
                            "selfHit.wav", "attack", 4);
                    // 扣除玩家血量
                    int surplusHP = player
                            .hited(bullet.getHarm());
                    // 判断玩家是否被击毁
                    if (surplusHP <= 0) {
                        gameOver();
                    }
                    // 先从面板删除该子弹
                    planeInterface.remove(bullet);
                    // 添加到待删除寄存器中
                    deleteBulletList.add(bullet);
                }
            }
        }
        // 玩家和敌机碰撞检测
        for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
            if (enemyPlane.isCrashed(player)) {
                // 敌机直接爆炸
                enemyDispose(enemyPlane);
                // 扣除玩家血量
                int surplusHP = player.hited(crashedHarm);
                // 判断玩家是否被击毁
                if (surplusHP <= 0) {
                    gameOver();
                }
            }
        }
    }


    /**
     * 刷新
     */
    private void refresh() {
        enemyMove();
        bulletMotion();
        monitorOutOfBounds();
        deleteObject();
    }

    /**
     * 所有子弹移动：包括我方和敌机
     */
    private void bulletMotion() {
        for (Bullet bullet : planeInterface.getBulletList()) {
            bullet.move();
        }
    }

    /**
     * 监视是否越界
     */
    private void monitorOutOfBounds() {
        // 监视敌机是否越界
        for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
            if (enemyPlane.isOutOfBounds()) {
                // 从面板中删除血量条
                planeInterface.remove(enemyPlane.getHPProgressBar());
                // 先从面板中删除该敌机
                planeInterface.remove(enemyPlane);
                // 添加到删除寄存器
                deleteEnemyList.add(enemyPlane);
            }
        }
        // 监视玩家子弹是否越界
        for (Bullet bullet : player.getBulletList()) {
            if (bullet.isOutOfBounds()) {
                // 先从面板上删除子弹
                planeInterface.remove(bullet);
                // 添加到删除寄存器
                deleteBulletList.add(bullet);
            }
        }
        // 监视敌机子弹是否越界
        for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
            for (Bullet bullet : enemyPlane.getBulletList()) {
                if (bullet.isOutOfBounds()) {
                    // 先从面板上删除子弹
                    planeInterface.remove(bullet);
                    // 添加到删除寄存器
                    deleteBulletList.add(bullet);
                }
            }
        }
    }


    /**
     * 从面板寄存器中删除
     */
    private void deleteObject() {
        // 1.销毁玩家子弹和敌机子弹
        for (Bullet bullet : deleteBulletList) {
            // 从面板寄存器中删除子弹
            planeInterface.getBulletList().remove(bullet);
            // 从玩家的子弹集合中删除该子弹
            player.getBulletList().remove(bullet);
            // 从所有敌机的子弹集合中试图删除该子弹
            for (EnemyPlane enemyPlane : planeInterface.getEnemyPlaneList()) {
                enemyPlane.getBulletList().remove(bullet);
            }
        }
        // 2.销毁敌机
        for (EnemyPlane enemyPlane : deleteEnemyList) {
            // 从面板寄存器中删除
            planeInterface.getEnemyPlaneList().remove(enemyPlane);
        }
        // 所有销毁完毕，清空寄存器
        deleteBulletList.clear();
        deleteEnemyList.clear();
    }

    /**
     * 敌机坠毁
     */
    private void enemyDispose(EnemyPlane enemyPlane) {
        // 给玩家增加分数
        player.addScore(enemyPlane.getDropScore());
        // 播放敌机坠毁动画
        enemyPlane.playDeadAction(planeInterface, "boom.wav", "Explosion", 7);
        // 从面板中删除血量条
        planeInterface.remove(enemyPlane.getHPProgressBar());
        // 先从面板中删除该敌机
        planeInterface.remove(enemyPlane);
        // 添加到寄存器中
        deleteEnemyList.add(enemyPlane);
    }

    /**
     * 玩家被击毁，游戏结束
     */
    private void gameOver() {
        // 停止部分线程
        ThreadSetting.pause();
        // 删除玩家飞机尾气
        player.deleteJet();
        // 玩家飞机爆炸，删除玩家
        planeInterface.remove(player);
        // 显示玩家被击落
        JOptionPane.showMessageDialog(planeInterface,
                "游戏结束，你已经被击落！\n杀敌数：" + player.getKillNumber() + "！\n分数：" + player.getScore() + "分！\n", "请退出游戏，重新开始",
                JOptionPane.INFORMATION_MESSAGE);
        player.playDeadAction(planeInterface, "boom.wav", "Explosion", 7);
    }

}
