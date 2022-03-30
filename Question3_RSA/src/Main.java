import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int p,q,e, N, phi, d;
        Scanner scanner = new Scanner(System.in);

        // Get input from user
        System.out.print("p:");
        p = scanner.nextInt();
        System.out.print("q:");
        q = scanner.nextInt();
        System.out.print("e:");
        e = scanner.nextInt();

        // Calculate N and phi
        N = q*p;
        phi = (p-1)*(q-1);

        // Calculating d
        d = 1;
        while((e*d)%phi != (1%phi)){
            d++;
        }

        System.out.println(d);
    }
}
