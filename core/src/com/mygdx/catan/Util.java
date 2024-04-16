package com.mygdx.catan;
import java.util.Random;
import java.util.random.*;

public class Util {

    public static final int[] dicepool = new int[]{2,3,3,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,9,9,9,9,10,10,10,11,11,12};

    public static int getRollDice(){
        Random r = new Random();
        int high = dicepool.length;
        return dicepool[r.nextInt(high)];
    }


}
