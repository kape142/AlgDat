package Øving2;

/**
 * Created by KarlPeter on 24.08.2017.
 */
public class Main {
    public static void main(String[] args) {
        int[] eksponenter = {10,100,1000,10000};
        double tall = 1.001;
        long[][] tider = new long[eksponenter.length][3];
        long t1;
        long t2;
        //init
        power1(1.001,10);
        power2(1.001,10);
        Math.pow(1.001,10);
        for(int i = 0; i < eksponenter.length; i++){
            t1 = System.nanoTime();
            power1(tall, eksponenter[i]);
            t2 = System.nanoTime();
            tider[i][0] = t2-t1;
            t1 = System.nanoTime();
            power2(tall, eksponenter[i]);
            t2 = System.nanoTime();
            tider[i][1] = t2-t1;
            t1 = System.nanoTime();
            Math.pow(tall,eksponenter[i]);
            t2 = System.nanoTime();
            tider[i][2] = t2-t1;
        }

        for (int i = 0; i < tider[0].length; i++) {
            System.out.println("Oppgave "+(i+1)+":\n");
            for (int j = 0; j < tider.length; j++) {
                System.out.println("" + tall + " opphøyd i " + eksponenter[j] + " tok " + findSpaces
                        (eksponenter[tider.length-1-j], 1)+
                        tider[j][i]
                        + " " +
                        "nanosekunder");
            }
            for (int j = 0; j < tider.length - 1; j++) {
                System.out.println("forholdet mellom " + eksponenter[j+1] + " og " + eksponenter[j] + " er " +
                        findSpaces(eksponenter[tider.length-j-1], 2)+ (double)tider[j+1][i]/(double)tider[j][i]);
            }
        }
    }

    private static double power1(double x, int n){
        if(n<=0){
            return 1.0;
        }else{
            return x* power1(x, n-1);
        }

    }

    private static double power2(double x, int n){
        if(n<=0){
            return 1.0;
        }else if(n%2==0){
            return power2(x*x, n/2);
        }else{
            return x* power2(x*x, (n-1)/2);
        }
    }


    private static String findSpaces(int tall, int ganger){
        String s = "";
        for (int i = 0; i < ganger; i++) {
            for (int j = 0; j < Math.log10((double) tall); j++) {
                s += " ";
            }
        }
        return s;
    }
    private static double findmiliseconds(long nanotime){
        long temp = nanotime/1000;
        System.out.println(nanotime);
        return (double)temp/1000;
    }
}
