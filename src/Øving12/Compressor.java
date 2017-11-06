package Øving12;

import util.ArrayPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Compressor {
    static byte[] data;
    static ArrayList<Byte> compressed;
    private static int bufferlength = 127;

    public static void compress(String file, String output) throws Exception{
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        data = new byte[length];
        innfil.readFully(data,0,length);
        for(byte b : data){
            System.out.print(b+", ");
        }
        System.out.println();
        for(byte b : data){
            System.out.print((char)b);
        }
        System.out.println("\n"+data.length);
        compressed = new ArrayList<>();
        ArrayList<Byte> found = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(0);
        for (int i = 0; i < 4; i++) {
            compressed.add(data[i]);
        }
        int lost = 0;
        for (int i = 4; i < length; i++) {
            found.clear();
            found.add(data[i]);
            int lastPos = -1;
            int pos = foundInLibrary(found,i);
            int check = 0;
            while(pos>0){
                lastPos=pos;
                check++;
                if(i+check<length) {
                    found.add(data[i + check]);
                    pos = foundInLibrary(found,i);
                }else{
                    break;
                }
            }
            if(check>=5){
                compressed.add((byte)-(i-lastPos));
                compressed.add((byte)(check));
                positions.add(i-lost+positions.size()+2);
                lost+=check-2;
                i+=check-1;
            }else{
                compressed.add(data[i]);
            }
        }
        System.out.println(ArrayPrinter.printInLine(compressed));
        for(byte b : compressed){
            System.out.print((char)b);
        }
        /*ArrayList<Integer> positions = new ArrayList<>();
        positions.add(0);
        for (int i = 0; i < compressed.size(); i++) {
            byte b = compressed.get(i);
            if (b < 0) {
                positions.add(i+2+positions.size());
            }
        }*/
        byte[] out = new byte[compressed.size()+positions.size()];
        positions.add(out.length+2);
        int posScroll = 0;
        for (int i = 0; i < out.length; i++) {
            if(i == positions.get(posScroll)){
                int jump = positions.get(posScroll+1)-positions.get(posScroll)-3;
                if(jump>bufferlength){
                    out[i] = (byte) bufferlength;
                    posScroll++;
                    positions.set(posScroll,i+bufferlength);
                    for (int j = posScroll; j < positions.size(); j++) {
                        positions.set(j,positions.get(j)+1);
                    }
                }else {
                    out[i] = (byte) (jump);
                    posScroll++;
                }
            }else{
                out[i]=compressed.get(i-posScroll);
            }
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(out,0,out.length);
        System.out.println();
        for(byte b : out){
            System.out.print(b+", ");
        }
        System.out.println();
        utfil.close();
        innfil.close();
    }

    private static int foundInLibrary(ArrayList<Byte> find, int depth){
        byte[] library = new byte[min(depth,bufferlength)];
        for (int i = 0; i < min(depth,bufferlength); i++) {
            library[i] = data[i+max(0,depth-bufferlength)];
        }
        for (int i = library.length-find.size(); i >= 0; i--) {
            if(library[i]==find.get(0)){
                boolean foundall = true;
                for (int j = 0; j < find.size(); j++) {
                    if(library[j+i]!=find.get(j)){
                        foundall=false;
                        break;
                    }
                }
                if(foundall){
                    //System.out.println(ArrayPrinter.printInLine(find));
                    return i+max(0,depth-bufferlength);
                }
            }
        }
        return -1;
    }

    private static int min(int x, int y){
        return (x<y)?x:y;
    }

    private static int max(int x, int y){
        return (x>y)?x:y;
    }
}

