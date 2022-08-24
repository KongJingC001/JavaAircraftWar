package kj.view;

import kj.util.Picture;

import javax.swing.JOptionPane;

/**
 * description: 游戏的欢迎菜单
 *
 * @author KONG
 */
public class StartInterface {

    /**
     * 用户选择进入游戏
     */
    public final static int START_OPTION = 0x0000001;

    /**
     * 用户选择取消
     */
    public final static int CANCEL_OPTION = 0x00000002;


    public StartInterface() {
        System.out.println("创建欢迎菜单！");
    }

    /**
     * 加载欢迎界面
     */
    public int start() {
        int opt = JOptionPane.showConfirmDialog(
                null,
                "开始游戏",
                "欢迎来到飞机大战！！！",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                Picture.getImage("welcome.png")
        );
        switch (opt) {
            case JOptionPane.OK_OPTION:
                System.out.println("用户进入游戏");
                opt = START_OPTION;
                break;
            case JOptionPane.CLOSED_OPTION:
            case JOptionPane.CANCEL_OPTION:
                System.out.println("用户取消游戏");
                opt = CANCEL_OPTION;
                break;
            default:
                System.out.println("用户未知错误");
                opt = Integer.MIN_VALUE;
                break;
        }
        return opt;
    }
}
