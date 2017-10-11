package Øving7;

import java.util.Objects;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by KarlPeter on 04.10.2017.
 */
public class SkandinaviaKlient {
    public static void main(String[] args) throws Exception{
        BFS bfs = new BFS();
        String filnavn = "src\\Øving7\\tekstfiler\\L7Skandinavia.txt";
        int rot = -1;
        while(rot==-1) {
            try {
                String rotNodeLest = showInputDialog("Velg en rotnode");
                int temp = Integer.parseInt(rotNodeLest);
                if(temp<0){
                    throw new NumberFormatException();
                }
                rot=temp;
            } catch (NumberFormatException e) {
                showMessageDialog(null,"blease input number over 0");
            }
        }
        bfs.findShortestRoutes(filnavn, rot);
        String lest = showInputDialog("Distanse til: ");
        while(!(lest.equals("-1"))){
            try {
                int temp = Integer.parseInt(lest);
                if(temp<0){
                    throw new NumberFormatException();
                }
                showMessageDialog(null,"Det er "+bfs.getDistanceTo(temp)+" veikryss mellom stedene");
                lest = showInputDialog("Distanse til: ");
            } catch (NumberFormatException e) {
                showMessageDialog(null,"blease input number over 0");
                lest = showInputDialog("Distanse til: ");
            }
        }
    }
}