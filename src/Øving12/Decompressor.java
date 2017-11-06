package Øving12;

import java.io.*;
import java.util.ArrayList;

class Decompressor {
    static void decompress(String input, String output) throws Exception{
        File f = new File(input);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length();
        byte[] data = new byte[length];
        innfil.readFully(data,0,length);
        ArrayList<Byte> decompressed = new ArrayList<>();
        //hittil samme som compressor
        for (int i = 0; i < length; i++) { //looper gjennom hele fila
            int op = data[i]; //finner "op-kode"
            if(op == 0){//skal skal lese inn null bytes ren tekst, går bare videre
                continue;
            }
            i++;
            if (op > 0) {//skal lese inn "op" antall bytes, legger de inn i decompressed-tabellen
                for (int j = 0; j < op; j++) {
                    if(i+j>=data.length){ //bare en veldig simpel måte å unngå krasj mens koden ikke er helt ferdig
                        System.out.println("feil, "+i);
                    }else {
                        decompressed.add(data[i + j]);
                    }
                }
                i += op - 1; //hopper videre så mange som den la inn
            }
            if (op < 0) { //skal legge inn komprimert data
                int j = decompressed.size() + op; //finner posisjonen der dataen som skal kopieres ligger
                int maks = data[i]; //hvor mange bytes som skal kopieres
                for (int k = 0; k < maks; k++) { //kopierer data
                    decompressed.add(decompressed.get(j + k));
                }
            }
        }
        //skriver ut
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
