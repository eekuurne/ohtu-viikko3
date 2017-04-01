import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("anna kertoja ");
        int kertoja = scanner.nextInt();

        System.out.println("anna kerrottava ");
        int kerrottavaLuku = scanner.nextInt();
        Multiplier kerrottava = new Multiplier(kerrottavaLuku);
        
        System.out.println("luku kertaa kolme on "+kerrottava.multipliedBy(kertoja) );
    }
}
