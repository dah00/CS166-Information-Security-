import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String x, y, z;
        int maj, x8, y10, z10, x18, y21, z22, nbits;
        String bitgenerated = "";
        Scanner scanner = new Scanner(System.in);
        // get x y z
        System.out.print("X: ");
        x = scanner.nextLine();
        System.out.print("Y: ");
        y = scanner.nextLine();
        System.out.print("Z: ");
        z = scanner.nextLine();

        System.out.print("How many bits would you like to generate: ");
        nbits = scanner.nextInt();

        for (int i=0; i<nbits; i++){
            // get maj
            x8 = Integer.parseInt(x.charAt(8) + "");
            y10 = Integer.parseInt(y.charAt(10) + "");
            z10 = Integer.parseInt(z.charAt(10) + "");
            maj = (x8+y10+z10 > 1)  ? 1 : 0;

           // System.out.println(x8 + " , " + y10 + " , " + z10 + " , maj: " + maj);

            // Shift registers that are supposed to shift
            if(x8 == maj){
                int[] arr = {13, 16, 17, 18};
                x = steps(arr, x);
            }
            if (y10 == maj){
                int[] arr = {20, 21};
                y = steps(arr, y);
            }
            if (z10 == maj){
                int[] arr = {7, 20, 21, 22};
                z = steps(arr, z);
            }

            // generate a keystream
            x18 = Integer.parseInt(x.charAt(18)+"");
            y21 = Integer.parseInt(y.charAt(21)+"");
            z22 = Integer.parseInt(z.charAt(22)+"");
            int tempint = x18 ^ y21 ^ z22;
            char tempchar = (char)(tempint + '0');
            bitgenerated = bitgenerated + tempchar;
        }



        // Outputs
        System.out.println("Updated registers");
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        System.out.println("Z: " + z);

        System.out.println("\nKeystream bit generated = " + bitgenerated);

    }

    // shifts the register
    private static String steps(int[] index, String str){
        int p = Integer.parseInt(str.charAt(index[0]) + "");
        for(int i=1; i<index.length; i++){
            int temp = Integer.parseInt(str.charAt(index[i]) + "");
            p = p ^ temp;
        }
        // adding the new value p to the beginning of the string and removing the last character of the string
        String pString = String.valueOf(p);
        str = pString.concat(str);
        str = str.substring(0, str.length()-1);
        return str;
    }
}
