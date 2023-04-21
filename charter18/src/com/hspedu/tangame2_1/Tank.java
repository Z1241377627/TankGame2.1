package com.hspedu.tangame2_1;

public class Tank {
    private int x;
    private int y;
    private int direct;

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public void moveUp(){
        y -= 10;
    }
    public void moveRight(){
        x += 10;
    }
    public void moveDown(){
        y += 10;
    }
    public void moveLeft(){
        x -= 10;
    }

}
