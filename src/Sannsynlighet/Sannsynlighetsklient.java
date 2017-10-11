package Sannsynlighet;

import javax.swing.*;
import java.awt.*;
import java.util.IllegalFormatCodePointException;

/**
 * Created by KarlPeter on 05.09.2017.
 *
 * Lag en Java-klasse som har metoder for å regne ut forventningen, variansen, standardavviket
 og fordelingsfunksjonen til en stokastisk variabel X, der X er gitt ved to like store
 tabeller/lister der den ene inneholder verdiene X kan ta og den andre inneholder
 sannsynligheten for hver av dem. La konstruktøren ha de to tabellene som inn-argumenter.
 Merk at fordelingsfunksjonen bør fungere som en (matematisk) funksjon skal, dvs. at den bør
 ta inn et reelt tall x og returnere F(x). Slik blir den mest mulig anvendelig. Den skal altså ikke
 gi en hel tabell som resultat, bare verdien for en bestemt x-verdi (som er inn-argument til
 funksjonen).

 b) Lag så et testprogram som gir inn sannsynlighetsfordelingen
 x 0 0.3 0.5 0.6 0.7 0.8 1.0 1.2 1.6
 P(X=x) 1/28 2/28 5/28 8/28 5/28 3/28 2/28 3/56 1/56
 og bruker klassen over til å regne ut forventningen, variansen og standardavviket til X, samt
 P(0.7≤X≤1.2). Klassen fra a) skal ikke endres på noen måte
 */
public class Sannsynlighetsklient {
    public static void main(String[] args) {
        double[] x = {0,0.3,0.5,0.6,0.7,0.8,1.0,1.2,1.6};
        double[] px = {1.0/28.0 ,2.0/28.0,5.0/28.0,8.0/28.0,5.0/28.0,3.0/28.0,2.0/28.0,3.0/56.0,1.0/56.0};
        StokastiskVariabel sv = new StokastiskVariabel(x, px);
        System.out.println("Forventning: \t"+sv.expectation());
        System.out.println("Varianse: \t\t"+sv.variance());
        System.out.println("Standardavvik: \t"+sv.standardDeviation());
        System.out.println("P(0.7<=x<=1.2): "+(sv.distributiveFunction(1.2)-sv.distributiveFunction(0.7)));
        //Vindu vindu = new Vindu();
    }
}

class Vindu extends JFrame{
    Vindu() {
        add(new Menu());
        pack();
        setVisible(true);
    }
}

class Menu extends JPanel{
    int length = 10;
    InputList il1;
    InputList il2;
    Menu(){
        setLayout(new BorderLayout());
        il1 = new InputList(length,"  x   \t");
        il2 = new InputList(length,"p(x)\t");
        add(il1,BorderLayout.NORTH);
        add(il2,BorderLayout.CENTER);
        add(new JButton(),BorderLayout.SOUTH);
    }
}

class InputList extends JPanel{
    JTextField[] tab;
    InputList(int length,String text){
        setLayout(new FlowLayout());
        JLabel l = new JLabel(text);
        l.setFont(new Font(Font.DIALOG,Font.PLAIN,30));
        add(l);
        tab = new JTextField[length];
        for (int i = 0; i < length; i++) {
            tab[i] = new Input();
            add(tab[i]);
        }
    }
    JTextField[] getTab(){
        return tab;
    }
}

class Input extends JTextField {
    Input() {
        super();
        construct();
    }

    Input(String text) {
        super(text);
        construct();
    }

    void construct() {
        setPreferredSize(new Dimension(80, 40));
        setFont(new Font(Font.DIALOG,Font.PLAIN,25));
    }
}

class MyButton extends JButton{
    MyButton(){
        super();
    }
}
