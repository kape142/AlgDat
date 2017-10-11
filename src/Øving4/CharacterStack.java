package Øving4;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 11.09.2017.
 */
public class CharacterStack {
    private ArrayList<Character>  list;
    CharacterStack(){
        list = new ArrayList<>();
    }

    boolean empty(){
        return list.isEmpty();
    }

    char peek(){
        return list.get(list.size()-1);
    }

    void pop(){
        list.remove(list.size()-1);
    }

    void add(char c){
        list.add(c);
    }

    public String toString(){
        return list.toString();
    }
}


//CharacterStack stack = new CharacterStack();