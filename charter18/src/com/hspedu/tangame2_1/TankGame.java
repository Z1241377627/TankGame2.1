package com.hspedu.tangame2_1;

import javax.swing.*;

public class TankGame extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
    public TankGame(){
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
