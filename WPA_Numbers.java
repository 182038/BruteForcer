/*
* © Dennis Wiencke 2016
* This program is written to bruteforce MD5 hashes
* It only works with hashes from words containing only chars from char[] alphabet
* In all other cases it will run endless
*/

package bruteforce;

import java.awt.Toolkit;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class WPA_Numbers {
    
    static int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    static BigInteger target;
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What number do you want to match?");
        target = new BigInteger(sc.nextLine());
        BigInteger now = new BigInteger("00000000000000000000");
        BigInteger step = new BigInteger("00000000000000000001");
        loopIt(now, step);
    }

    private static void loopIt(BigInteger now, BigInteger step) {
        while(now.compareTo(target) == -1) {
            now = now.add(step);
//            if(now.toString().length() > 20) break;
//            System.out.println(now.toString());
        }
        System.out.println("Matched target");
    }
}
