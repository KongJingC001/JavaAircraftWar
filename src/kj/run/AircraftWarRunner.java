package kj.run;

import kj.controller.MainController;


/**
 * description: 运行器
 * 2020-04-04 14:58:00
 * @author KONG
 */
public class AircraftWarRunner {

    /**
     * 唯一入口方法
     */
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.start();
    }

}
