package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

public class RpsRunner {
    public static void main(String[] args) {

        System.out.println("----START----");
        Game game = new Game();
        game.start();
        System.out.println("----CLOSE----");
    }
}