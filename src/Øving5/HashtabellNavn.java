package Øving5;

import java.util.Arrays;

/**
 * Created by KarlPeter on 14.09.2017.
 */
class HashtabellNavn {
    private final int A = 1327217885;
    private int bits;
    private int elements=0;
    private int kollisjoner;
    private int m;
    private String[] navn;

    HashtabellNavn(int elements){
        int bits = (int)Math.ceil(Math.log(elements)/Math.log(2));
        navn = new String[(int) Math.pow(2,bits)];
        this.bits = bits;
        m = navn.length;
    }

    void add(String s){
        int key = convert(s);
        int place = h1(key);
        int i = 1;
        while(!(navn[place] == null)){
            System.out.println("Kollisjon! "+s+" prøvde å stjele plassen til "+navn[place]);
            kollisjoner++;
            place = h2(key,i);
            i++;
        }
        navn[place] = s;
        elements++;
    }

    private int convert(String s){
        int sum = 0;
        char[] tab = s.toCharArray();
        for(int i = 0;i<tab.length;i++){
            sum+=tab[i]*(i+1);
        }
        return sum;
    }

    private int h1(int num){
        return (m/2)+(num*A>>(32-bits));
    }

    private int h2(int num, int i){
        return ((num*2*i)+3)%m;
    }

    public String toString(){
        String s = "Lastfaktor: "+(double)elements/(double)m+"\n";
        s+="Kollisjoner: "+kollisjoner+"\n\n";
        s+=Arrays.toString(navn);
        return s;
    }
}
