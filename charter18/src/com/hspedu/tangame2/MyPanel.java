package com.hspedu.tangame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero = null;
    Vector<Enemy> enemys = new Vector<>();
    int enemyNum = 3;


    public MyPanel(){
        hero = new Hero(100, 100, 0);
        for (int i = 0; i < enemyNum; i++){
            enemys.add(new Enemy((100 * (i + 1)),0,2));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);

        drawTank(hero.getX(), hero.getY(), g,hero.getDirect(), 0);

        if(hero.shot != null && hero.shot.loop == true){
            //System.out.println("绘制子弹");
            if(hero.shot.direct == 0 || hero.shot.direct == 2){
                g.draw3DRect(hero.shot.x, hero.shot.y, 1, 4, false);
            } else if (hero.shot.direct == 1 || hero.shot.direct == 3) {
                g.draw3DRect(hero.shot.x, hero.shot.y, 4, 1, false);
            }
        }

        for (int i = 0; i < enemys.size(); i++){
            Enemy enemy = enemys .get(i);
            drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 1);
        }
    }

    //方法画坦克
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //坦克类型
        switch (type){
            case 0 :
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        //坦克方向
        switch (direct){
            case 0://上
                g.fill3DRect(x, y, 10, 60 , false);
                g.fill3DRect(x + 30, y, 10, 60 , false);
                g.fill3DRect(x + 10, y + 10, 20, 40 , false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y+ 30, x + 20, y);
                break;
            case 1://右
                g.fill3DRect(x, y, 60, 10 , false);
                g.fill3DRect(x, y + 30, 60, 10 , false);
                g.fill3DRect(x + 10, y + 10, 40, 20 , false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y+ 20, x + 60, y + 20);
                break;
            case 2://下
                g.fill3DRect(x, y, 10, 60 , false);
                g.fill3DRect(x + 30, y, 10, 60 , false);
                g.fill3DRect(x + 10, y + 10, 20, 40 , false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y+ 30, x + 20, y + 60);
                break;
            case 3://左
                g.fill3DRect(x, y, 60, 10 , false);
                g.fill3DRect(x, y + 30, 60, 10 , false);
                g.fill3DRect(x + 10, y + 10, 40, 20 , false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y+ 20, x, y + 20);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirect(0);
            hero.setY(hero.getY() - 1);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirect(2);
            hero.setY(hero.getY() + 1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirect(3);
            hero.setX(hero.getX() - 1);
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirect(1);
            hero.setX(hero.getX() + 1);
        }

        //射击
        if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
