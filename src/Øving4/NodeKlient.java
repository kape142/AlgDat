package Øving4;

/**
 * Created by KarlPeter on 08.09.2017.
 */
public class NodeKlient {
    public static void main(String[] args) {
        int length = 20;
        int interval = 4;
        System.out.println("To avoid death you should be number "+calculatePlacement(length,interval));
    }

    private static int calculatePlacement(int length, int interval){
        Node hode = makeList(length);
        Node maks = hode.getPrev();
        Node node = hode.getPrev();
        if(interval%length!=0){
            interval%=length;
        }
        while(!node.getNext().getNext().equals(node.getNext())){
            for (int i = 0; i < interval; i++) {
                node=node.getNext();
                System.out.print(node.getValue()+" ");
                if(node==maks){
                    System.out.println();
                }
            }
            if (node == maks){
                maks = node.getPrev();
            }
            //System.out.print("x");
            deleteNode(node);
        }
        System.out.println(node.getNext().getValue());
        return(node.getNext().getValue());
    }

    private static Node makeList(int length){
        Node hode = new Node (1);
        hode.setNext(hode);
        hode.setPrev(hode);
        for (int i = 2; i <= length; i++) {
            addNode(hode,new Node(i));
        }
        //System.out.println(printNodes(hode,length+1));
        return hode;
    }
    private static void addNode(Node hode, Node ny){
        Node node = hode;
        while(ny.getValue()>node.getValue()) {
            node = node.getNext();
            if(node.equals(hode)){
                break;
            }
        }
        ny.setNext(node);
        ny.setPrev(node.getPrev());
        node.getPrev().setNext(ny);
        node.setPrev(ny);
    }

    private static void deleteNode(Node node){
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }
    static String printNodes(Node hode, int length){
        String s = "";
        Node print = hode;
        for (int i = 0; i < length; i++) {
            s+=print.toString();
            print = print.getNext();
        }
        return s;
    }
}

/*
n = lengde
m = mellomrom

totalt antall loops = (n-1)*m


while kjører n-1 ganger

for hvert whilehode:
1+1+1+1+1 = 5

for loop:

1+1+1+2 = 5

delete:
1+2*(1+1+1) = 7

5+7+5*m

(n-1)(12+5m)|
12n+5mn-12-5m = 5mn+12n-5m-12


worst case:
hvis m = n blir kompleksiteten n*n

best case:
hvis m = 1 blir kompleksiteten n
 */