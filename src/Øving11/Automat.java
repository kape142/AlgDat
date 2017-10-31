package Øving11;

/**
 * Created by KarlPeter on 31.10.2017.
 */
class Automat {
    private char[] inputalfabet;
    private int[] aksepttilstander;
    private int[][] nesteTilstand;

    Automat(char[] inputalfabet, int[] aksepttilstander, int[][] nesteTilstand) {
        this.inputalfabet = inputalfabet;
        this.aksepttilstander = aksepttilstander;
        this.nesteTilstand = nesteTilstand;
    }
    boolean sjekkInput(String input){
        return sjekkInput(input.toCharArray());
    }

    boolean sjekkInput(char[] input){
        int tilstand = 0;
        for(char c : input){
            int s = 0;
            for (int j = 0; j < inputalfabet.length; j++) {
                if(c==inputalfabet[j]){
                    s=j;
                    break;
                }
            }
            tilstand = nesteTilstand[tilstand][s];

        }
        for(int i: aksepttilstander){
            if(tilstand == i){
                return true;
            }
        }
        return false;
    }
}
