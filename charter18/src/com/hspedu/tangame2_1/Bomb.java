package com.hspedu.tangame2_1;

public class Bomb {
    int x;
    int y;
    int live = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean loop = true;

    public void livDown(){
        if (live > 0){
            live--;
        }else {
            loop = false;
        }
    }
}
