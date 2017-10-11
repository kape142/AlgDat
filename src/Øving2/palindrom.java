package Øving2;

/**
 * Created by KarlPeter on 24.08.2017.
 */
public class palindrom {
    public static void main(String[] args) {
        String ord = "abcdefghijklmnopqrstuvwxyzzyxwvutsrqponmlkjihgfedcba";
        char[] ordtabell = ord.toCharArray();
        System.out.print(ord +" is ");
        if(!checkPalindrome(ordtabell,0)){
            System.out.print("not ");
        }
        System.out.println("a palindrome.");
    }

    private static boolean checkPalindrome(char[] ordtabell, int index){
        if(index>=ordtabell.length/2){
            return true;
        }
        if(ordtabell[index]==ordtabell[ordtabell.length-index-1]){
            return checkPalindrome(ordtabell,index+1);
        }else{
            return false;
        }
    }
}
