package Øving11;

/**
 * Created by KarlPeter on 31.10.2017.
 */
public class Klient {
    public static void main(String[] args) {
        char[] inputalfabet = {'0','1'};
        int[] aksepttilstander = {2};
        int[][] nesteTilstand = {{1,3},{1,2},{2,3},{3,3}};
        String[] inputs = {"","010","111","010110","001000"};

        Automat automat = new Automat(inputalfabet,aksepttilstander,nesteTilstand);

        for(String s : inputs) {
            System.out.println(s + ": "+automat.sjekkInput(s));
        }

        System.out.println("\n");

        char[] inputalfabet2 = {'a','b'};
        int[] aksepttilstander2 = {3};
        int[][] nesteTilstand2 = {{1,2},{4,3},{3,4},{3,3},{4,4}};
        String[] inputs2 = {"abbb","aaab","babab"};

        Automat automat2 = new Automat(inputalfabet2,aksepttilstander2,nesteTilstand2);

        for(String s : inputs2) {
            System.out.println(s + ": "+automat2.sjekkInput(s));
        }
    }
}
