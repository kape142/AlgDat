package Øving3;

/**
 * Created by KarlPeter on 30.08.2017.
 */
class Sorter {
    private int[] tab;
    private int swap = 0;
    int[] sort(int[] input, int swap, int oppgave){
        if(input==null || input.length<=0){
            return null;
        }
        this.swap=swap;
        this.tab = input;
        quicksort(0,tab.length-1, oppgave);
        return tab;
    }

    private void quicksort(int low, int high, int oppgave){
        int i = low;
        int j = high;
        int pivot = tab[low + (high-low)/2];
        if(low>0 && high<tab.length-1 && tab[low-1] == tab[high+1] && oppgave >=3){
            return;
        }
        if(high-low<swap && oppgave>=1){
            shellSort(low,high);
            return;
        }
        if(oppgave>=2) {
            if (low == 0 || high == tab.length - 1) {
                int min = tab.length;
                int maks = 0;
                for (int k = low; k < high; k++) {
                    maks = tab[k] > maks ? tab[k] : maks;
                    min = tab[k] < min ? tab[k] : min;
                }
                if (maks - min < high - low) {
                    countingSort(low, high);
                    return;
                }
            } else {
                if (tab[high+1] - tab[low-1] < high - low) {
                    countingSort(low, high);
                    return;
                }
            }
        }
        while (i <= j) {
            while (tab[i] < pivot) {
                i++;
            }
            while (tab[j] > pivot) {
                j--;
            }
            if (i <= j) {
                bytt(i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quicksort(low, j, oppgave);
        }
        if (i < high) {
            quicksort(i, high, oppgave);
        }
    }

    private void shellSort(int low, int high){
        int length = high-low;
        for(int gap = length;gap>0; gap/=2){
            for(int i = low+gap; i <= high; i++){
                int temp = tab[i];
                int j;
                for(j=i; j>=gap && tab[j-gap] > temp; j -= gap){
                    tab[j] = tab[j-gap];
                }
                tab[j]=temp;
            }
        }
    }

    private void countingSort(int low, int high){
        int maks = 0;
        for (int i = low; i < high; i++) {
            maks = tab[i]>maks?tab[i]:maks;
        }
        int[] count = new int[maks+1];
        for (int i = low; i < high; i++) {
            count[tab[i]]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i]+=count[i-1];
        }

        int counter = 0;
        for (int i = 0; i < count.length; i++) {
            while(counter<count[i] || counter == high-low){
                tab[low+counter]=i;
                counter++;
            }
        }
    }
    private void bytt(int i, int j){
        int help = tab[i];
        tab[i]=tab[j];
        tab[j]=help;
    }
}
