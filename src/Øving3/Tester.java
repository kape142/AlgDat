package Øving3;

import java.util.Random;

/**
 * Created by KarlPeter on 30.08.2017.
 */
public class Tester {
    private static int tests = 300;
    private static int opt = 20;
    private static int size = 100000;
    private static double spread = 1;
    private static boolean show = false;
    private static Sorter sorter = new Sorter();
    private static Random random = new Random(41);
    public static void main(String[] args) {
        oppgave1();
        oppgave2();
        oppgave3();
    }

    private static void oppgave3(){
        System.out.println("Oppgave 3:\n");
        spread = 1;
        int med = testOppgave(opt,3);
        int uten = testOppgave(opt,2);
        System.out.println("With spread numbers from 0 to size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");
        spread = 10;
        med = testOppgave(opt,3);
        uten = testOppgave(opt,2);
        System.out.println("With spread numbers from 0 to ten times the size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");
        spread = 0.1;
        med = testOppgave(opt,3);
        uten = testOppgave(opt,2);
        System.out.println("With spread numbers from 0 to ten percent of the size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");

    }

    private static void oppgave2(){
        System.out.println("Oppgave 2:\n");
        show=false;
        spread = 1;
        int med = testOppgave(opt,2);
        int uten = testOppgave(opt,1);
        System.out.println("With spread numbers from 0 to size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");
        spread = 10;
        med = testOppgave(opt,2);
        uten = testOppgave(opt,1);
        System.out.println("With spread numbers from 0 to ten times the size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");
        spread = 0.1;
        med = testOppgave(opt,2);
        uten = testOppgave(opt,1);
        System.out.println("With spread numbers from 0 to ten percent of the size of the table:");
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");
    }

    private static void oppgave1(){
        System.out.println("Oppgave 1: \n");
        show=true;
        opt = optimizeOppgave1(size);
        System.out.println("The optimal swapping point is "+opt);
        int med = testOppgave(opt,1);
        int uten = testOppgave(opt,0);
        System.out.println("Old version used \t"+uten+ " ns");
        System.out.println("New version used\t"+med+" ns");
        System.out.println("The new version saved "+(uten-med)+" ns");
        System.out.println("");

    }
    private static int optimizeOppgave1(int size){
        int oppdeling = 2;
        int optimize=0;
        int start=0;
        for(int gap = size/oppdeling; gap>=1;gap/=oppdeling){
            boolean betterThanQuick = false;
            int min = testOppgave(0, 1);
            int index = 0;
            for (int i = 0; i < oppdeling; i++) {
                int test = testOppgave(start+gap/2+gap*i,1);
                if (test < min){
                    min=test;
                    index = i;
                    betterThanQuick = true;
                }
            }
            optimize=start+gap/2+gap*index;
            start+=gap*index;
            if(!betterThanQuick){
                start=0;
            }
        }
        return optimize;
    }
    private static int testOppgave(int swap, int oppgave){
        long totaltime = 0;
        boolean validation = true;
        for (int i = 0; i < tests; i++) {
            int[] tab = generateTable(size);
            long t1 = System.nanoTime();
            int[] sortertTab = sorter.sort(tab, swap, oppgave);
            long t2 = System.nanoTime();
            totaltime+=t2-t1;
            if(!(validateSort(sortertTab))){
                validation=false;
            }
            if (!validation) {
                System.out.println("FEIL!");
                throw new RuntimeException();
            }
        }
        long avgtime = totaltime/tests;
        if(show) {
            System.out.println("swapping at " + swap);
            System.out.println(avgtime);
            System.out.println(validation ? "passed" : "failed");
            System.out.println();
        }
        return (int) avgtime;
    }

    private static boolean validateSort(int[] tab){
        for (int i = 1; i < tab.length; i++) {
            if(tab[i]<tab[i-1]){
                return false;
            }
        }
        return true;
    }

    private static int[] generateTable(int size){
        double dcap = (double)size * spread;
        int cap = (int)dcap;
        int[] tab = new int[size];
        for (int j = 0; j < size; j++) {
            tab[j]= random.nextInt(cap);
        }
        return tab;
    }
}
