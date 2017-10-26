/**
 * Created by KarlPeter on 26.10.2017.
 */
public class Øving10 {
    public static void main(String[] args) {
        String[] strings = {"Dette er en vanlig tekst","Dette er en tekst med 1 tall","1234","14/05/2007",
                "barebokstaver","123/22/2888","29/02","92/39/9999","123456789","1234567890","1111","10"};
        String[] regex = {".*\\d.*","(0?[1-9]|[12][0-9]|3[01])[/](0?[1-9]|1[012])[\\/\\-]\\d{4}",".{10}.*",
                ".*[^a-zA-Z].*","(1(1*))|((1(0*))|((1*)1))"};
        String test = "(http(s)?:\\/\\/)?(www\\.)?[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+(\\/.*)*";
        for (int i = 0; i < regex.length; i++) {
            System.out.println((i+1)+". regex");
            for (String s : strings) {
                if (s.matches(regex[i])) {
                    System.out.println(s);
                }
            }
            System.out.println();
        }

    }
}

// 11*|10*
// xy(x*y)*|(yx|y)y

// (a*b*)*b[ab]{2}   (a*b*)*b[ab][ab]    (a*b*)*b(a|b)(a|b)
// y*(xyy*)*

//(rs)* er ikke lik r*s*. rsrsrsrsrs vs. rrrrrrrrsssssss
//(rs)* ((rs)*)* er like. den siste er bare 0 til mange av den første (som allerede er null til mange



