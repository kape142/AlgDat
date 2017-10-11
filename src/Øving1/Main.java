package Øving1;

import java.util.Random;

/**
 * Created by KarlPeter on 21.08.2017.
 */
public class Main {
        private static int length = 10000;
        private static Random random = new Random();
        private static int[] tab = new int[length];
    public static void main(String[] args) {
        for (int i = 0; i < length; i++) {
            tab[i]=-5+random.nextInt(11);
        }
        long startTime = System.nanoTime();
        int best = findBestTimes();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        int val = 10;
        for (int i = 0; i < tab.length; i++) {
            val+=tab[i];
            System.out.print("["+val+"]");
        }
        System.out.println();
        System.out.println(best);
        System.out.println(duration);
        long l = duration/1000000L;
        System.out.println((double)l/1000);
    }

    private static int findBestTimes(){
        int start = 10;
        int[] val = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            start+=tab[i];
            val[i]=start;
        }
        int maxmax = -6;
        for (int i = 0; i < val.length; i++) {
            int buy = val[i];
            int max = -6;
            for(int j = i+1; j < val.length; j++){
                max = (val[j]-buy>max)?val[j]-buy:max;
            }
            maxmax = (max>maxmax)?max:maxmax;
        }
        return maxmax;
    }
}
