package Øving4;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

public class KodeTester {
    private static ArrayList<Character> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        list.add("{".toCharArray()[0]);
        list.add("}".toCharArray()[0]);
        list.add("[".toCharArray()[0]);
        list.add("]".toCharArray()[0]);
        list.add("(".toCharArray()[0]);
        list.add(")".toCharArray()[0]);

        String filnavn = "src\\Øving4\\KodeTester.java";
        FileReader l = new FileReader(filnavn);
        BufferedReader leser = new BufferedReader(l);
        StringBuilder kode = new StringBuilder();
        leser.lines()
                .map(String::toCharArray)
                .map(KodeTester::findSymbols)
                .forEach(kode::append);
        Stack<Character> stack = new Stack<>();
        char[] tab = kode.toString().toCharArray();
        for(char a : tab){
            if(!stack.empty() && bracketInverter(a)==stack.peek()){
                    stack.pop();
            }else{
                stack.push(a);
            }
        }
        if(stack.empty()){
            System.out.println("Pass");
        }else{
            System.out.println("Fail");
            System.out.println(stack.toString());
        }
        System.out.println(kode.toString());
    }

    private static char bracketInverter(char c){
        int place = list.indexOf(c);
        return list.get(place%2==0?place+1:place-1);
    }

    private static char[] findSymbols(char[] tab){
        char[] newTab = new char[tab.length];
        int used = 0;
        for (char aTab : tab) {
            if (list.contains(aTab)) {
                newTab[used] = aTab;
                used++;
            }
        }
        char[] returnTab = new char[used];
        System.arraycopy(newTab, 0, returnTab, 0, returnTab.length);
        return returnTab;
    }
}
