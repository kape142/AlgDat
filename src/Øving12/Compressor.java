package Øving12;

import java.io.*;
import java.util.ArrayList;

class Compressor {
    private static byte[] data;
    private static int byteMaxValue = 127;

    static void compress(String file, String output) throws Exception{
        File f = new File(file);
        DataInputStream innfil = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int length = (int) f.length(); //finner bytelengden av fila
        data = new byte[length];
        innfil.readFully(data,0,length); //lagrer alt innholdet i data[]
        ArrayList<Byte> compressed = new ArrayList<>();
        // et tall for hvor mange indekser bakover dataen ligger, og hvor mange den skal lese fremover fra det punktet
        ArrayList<Byte> found = new ArrayList<>(); //midlertidig, inneholder en liste over tegn i rekkfølge som skal
        // sees etter tidligere i data-tabellen
        ArrayList<Integer> positions = new ArrayList<>(); //inneholder en liste over posisjoner der det skjer
        // komprimering
        positions.add(0); //brukes for å få riktig avstand fra starten til første komprimering
        for (int i = 0; i < 4; i++) {
            compressed.add(data[i]); //de første fire kan uansett ikke komprimeres, p.g.a den komprimerer bare hvis
            // minst 5 byte er like
        }
        int lost = 0; //hvor mange bytes som er komprimert vekk. brukes for å beregne relativ posisjon i den
        // komprimerte fila
        for (int i = 4; i < length; i++) {
            found.clear(); //tømmer tabellen over bytes i rekkefølge som skal sjekkes. skjer hver gang en
            // komprimering er blitt lagt til eller en byte er bekreftet at ikke kan komprimeres
            found.add(data[i]); //starter med neste bokstav
            int lastPos = -1;
            int pos = foundInLibrary(found, i); //finner første posisjon til alle bytesene i found, i library.
            int check = 0; //hvor mange bytes som matcher i library (kan kanskje erstattes med found.length?)
            while (pos > 0) {
                lastPos = pos; //lagrer forrige posisjon der bytesene ble funnet, så de kan settes inn for komprimering
                // når den kommer til en bytestreng som ikke finnes
                check++;
                if (i + check < length) {
                    found.add(data[i + check]); //legger til neste byte for sjekking
                    pos = foundInLibrary(found, i);
                } else {
                    break;
                }
            }
            if (check >= 5) { // hvis den fant minst 5 bytes som finnes tidligere i data[]
                compressed.add((byte) -(i - lastPos)); //legger inn en negativ byte som peker bakover på hvor dataen
                // finnes i data[]
                compressed.add((byte) (check)); //hvor mange bytes som skal leses fra det punktet
                positions.add(i - lost + positions.size() + 2); //lagrer posisjonen til denne komprimeringen for bruk i
                // beregning av hvor mange bytes som må leses vanlig uten komprimering
                lost += check - 2; //legger inn hvor mange bytes som er mista i komprimering: antall bytes som matcher,
                // minus to bytes til referanser
                i += check - 1; //hopper videre i data[] til neste byte som ikke er komprimert
            } else {
                compressed.add(data[i]);
            }
        }
        byte[] out = new byte[compressed.size()+positions.size()]; //den endelige komprimerte fila. det som mangler
        // er å legge inn tall for hvor mange bytes som må leses vanlig uten komprimering
        positions.add(out.length+2); // legger til en posisjon til slutt for å beregne hvor mange bytes som leses i
        // siste bolk
        int posScroll = 0; //variabel for å loope gjennom posisjonene med komprimering
        for (int i = 0; i < out.length; i++) { //looper gjennom og lager den ferdige tabellen
            if(i == positions.get(posScroll)){ //hvis denne posisjonen inneholder en komprimering
                int jump = positions.get(posScroll+1)-positions.get(posScroll)-3; //hvor mange bytes til neste
                // komprimering
                if(jump> byteMaxValue){ //hvis tallet for antall bytes ikke kan lagres i en byte
                    out[i] = (byte) byteMaxValue; //legger inn maksverdi i tabellen
                    //legger inn en ny posisjon 127 etter denne byten så det kan beregnes der hvor lang
                    // neste rekke med ukomprimerbare bytes er
                    posScroll++;
                    positions.add(positions.get(positions.size()-1));
                    for (int j = positions.size()-2; j > posScroll; j--) {
                        positions.set(j,positions.get(j-1)+1);
                    }
                    positions.set(posScroll,i+ byteMaxValue +1);
                }else {
                    out[i] = (byte) (jump); // legger inn hvor mange bytes til neste komprimering
                    posScroll++;
                }
            }else{
                out[i]= compressed.get(i-posScroll); //legger inn en verdi fra compressed-tabellen
            }
        }
        //skriver til fil
        DataOutputStream utfil = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
        utfil.write(out,0,out.length);
        utfil.close();
        innfil.close();
    }

    private static int foundInLibrary(ArrayList<Byte> find, int depth){ //finner ut hvor i de siste 127 bytesene man
        // kan finne en rekke med bytes (find)
        byte[] library = new byte[min(depth, byteMaxValue)];
        for (int i = 0; i < min(depth, byteMaxValue); i++) {
            library[i] = data[i+max(0,depth- byteMaxValue)];
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
                    return i+max(0,depth- byteMaxValue);
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

