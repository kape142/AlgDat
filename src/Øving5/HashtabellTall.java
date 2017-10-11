package Øving5;

import java.util.Arrays;

/**
 * Created by KarlPeter on 14.09.2017.
*/
class HashtabellTall {
    private int m;
    private int[] tall;
    private int kollisjoner;

    HashtabellTall(int length){
        tall = new int[length];
        m = length;
        kollisjoner=0;
    }

    void add(int n){
        int place = h1(n);
        int i = 1;
        while(!(tall[place] == 0)){
            kollisjoner++;
            place = h2(n,i);
            i++;
        }
        tall[place] = n;
    }

    private int h1(int n){
        return n%m;
    }

    private int h2(int n, int i){
        return n%(m-i)+i;
    }

    public int getKollisjoner(){
        return kollisjoner;
    }
}