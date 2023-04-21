package com.hspedu.tangame2_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero = null;
    Vector<Enemy> enemys = new Vector<>();
    int enemyNum =3;
    Vector<Bomb> bombs = new Vector<>();
    Image image1  = null;
    Image image2  = null;
    Image image3  = null;

    public MyPanel(){
        hero = new Hero(100, 100, 0);

        for (int i = 0; i < enemyNum; i++){
            Enemy enemy = new Enemy((100 * (i + 1)),0,2);
            new Thread(enemy).start();
            Shot show = new Shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirect());
            enemy.shots.add(show);
            new Thread(show).start();
            enemys.add(enemy);
        }

        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boob_3.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boob_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boob_1.gif"));
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
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < bombs.size(); i++){
            Bomb bomb = bombs.get(i);
            if (bomb.live > 6){
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }else if(bomb.live > 3){
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }else {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.livDown();
            if (bomb.live ==  0){
                bombs.remove(bomb);
            }
        }

        for (int i = 0; i < enemys.size(); i++) {
            Enemy enemy = enemys.get(i);
            if (enemy.loop == true) {
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 1);
                for (int j = 0; j < enemy.shots.size(); j++) {
                    Shot shot = enemy.shots.get(j);
                    if (shot.loop == true) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemy.shots.remove(shot);
                    }
                }
            }
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
            if (hero.getY() > 0) {
                hero.moveUp();
            }
            //hero.setY(hero.getY() - 1);
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
            //hero.setY(hero.getY() + 1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
            //hero.setX(hero.getX() - 1);
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
            //hero.setX(hero.getX() + 1);
        }

        //射击
        if (e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    public void hitTank(Shot s, Enemy enemy){
        switch (s.direct){
            case 0:
            case 2:
                if(s.x > enemy.getX() && s.x < enemy.getX() + 40 &&
                        s.y > enemy.getY() && s.y < enemy.getY() + 60){
                    s.loop = false;
                    enemy.loop = false;
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if(s.x > enemy.getX() && s.x < enemy.getX() + 60 &&
                        s.y > enemy.getY() && s.y < enemy.getY() + 40){
                    s.loop = false;
                    enemy.loop = false;
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
                break;
        }
        if (enemy.loop == false){
            enemys.remove(enemy);
        }
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

            if (hero.shot!= null && hero.shot.loop == true){
                for (int i = 0; i < enemys.size(); i++){
                    Enemy enemy = enemys.get(i);
                    hitTank(hero.shot, enemy);
                }
            }
            this.repaint();
        }
    }
}
