package util;

import java.util.ArrayList;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class ArrayPrinter {
    public static String print(ArrayList<?> tab){
        String s = "";
        for(Object o : tab){
            s+=o.toString()+"\n";
        }
        return s;
    }

    public static String print(Object[] tab){
        String s = "";
        for(Object o : tab){
            s+=o.toString()+"\n";
        }
        return s;
    }

    public static String printInLine(Object[] tab){
        String s = "";
        for(Object o : tab){
            s+=o.toString()+ " ";
        }
        return s.substring(0,s.length()-2);
    }

    public static String printInLine(ArrayList<?> arrayList){
        if(arrayList.size()==0){
            return "";
        }
        String s = "";
        for(Object o : arrayList){
            s+=o.toString()+ " ";
        }
        return s.substring(0,s.length()-1);
    }
}
