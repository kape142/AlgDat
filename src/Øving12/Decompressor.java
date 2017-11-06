package Øving12;

import java.io.*;
import java.util.ArrayList;

public class Decompressor {
    private static int bufferlength = 127;
    public static void decompress(String input, String output) throws Exception{
        File f = new File(input);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        byte[] data = new byte[length];
        innfil.readFully(data,0,length);
        ArrayList<Byte> decompressed = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int op = data[i];
            if(op == 0){
                continue;
            }
            i++;
            if (op > 0) {
                while(op%bufferlength==0){
                    i++;
                    op+=data[i];
                }
                for (int j = 0; j < op; j++) {
                    if(i+j>=data.length){
                        System.out.println("feil, "+i);
                    }else {
                        decompressed.add(data[i + j]);
                    }
                }
                i += op - 1;
            }
            if (op < 0) {
                int j = decompressed.size() + op;
                int maks = data[i];
                for (int k = 0; k < maks; k++) {
                    decompressed.add(decompressed.get(j + k));
                }
            }
        }
        byte[] out = new byte[decompressed.size()];
        for (int i = 0; i < decompressed.size(); i++) {
            out[i] = decompressed.get(i);
        }
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(out,0,out.length);
        utfil.close();
        innfil.close();
    }
}
