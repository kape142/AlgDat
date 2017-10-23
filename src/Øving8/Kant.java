package Øving8;

/**
 * Created by KarlPeter on 13.10.2017.
 */
class Kant {
    private int tilNode;
    private int lengde;

    Kant(int tilNode, int lengde) {
        this.tilNode = tilNode;
        this.lengde = lengde;
    }

    int getTilNode() {
        return tilNode;
    }

    int getLengde() {
        return lengde;
    }

    void setLengde(int lengde) {
        this.lengde = lengde;
    }
}
