package com.hspedu.tangame2;

public class Shot implements Runnable{
    int x = 0;
    int y = 0;
    int direct = 0;
    boolean loop = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct) {
                case 0:
                    y--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    x--;
                    break;
            }
            System.out.println("x = " + x + ", y = " + y);
            if(!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)){
                loop = false;
            }
        }
    }
}
