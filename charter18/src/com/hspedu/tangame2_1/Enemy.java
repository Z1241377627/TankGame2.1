package com.hspedu.tangame2_1;

import java.util.Vector;

public class Enemy extends Tank implements Runnable{
    boolean loop = true;
    Vector<Shot> shots = new Vector<>();
    public Enemy(int x, int y, int direct) {
        super(x, y, direct);
    }

    @Override
    public void run() {
        while (true) {
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i <= 50; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i <= 50; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i <= 50; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i <= 50; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            setDirect((int) (Math.random() * 4));
            if (loop == false){
                break;
            }
        }
    }
}
