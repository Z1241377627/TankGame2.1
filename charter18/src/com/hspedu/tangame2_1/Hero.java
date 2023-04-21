package com.hspedu.tangame2_1;

public class Hero extends Tank {
    Shot shot = null;;
    public Hero(int x, int y, int direct) {
        super(x, y, direct);
    }

    public void shotEnemyTank(){
        switch (getDirect()){
            case 0:
                shot = new Shot(getX() + 20, getY(), getDirect());
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, getDirect());
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, getDirect());
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, getDirect());
                break;
        }
        Thread thread = new Thread(shot);
        thread.start();
    }
}
