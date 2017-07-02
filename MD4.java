/*
* © Dennis Wiencke 2016
* This program is written to bruteforce MD5 hashes
* It only works with hashes from words containing only chars from char[] alphabet
* In all other cases it will run endless
*/

package bruteforce;

import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD4 {
    
    //Initialize possible chars
    static char[] alphabet =  
                    {'a','b','c','d','e',
                    'f','g','h','i','j',
                    'k','l','m','n','o',
                    'p','q','r','s','t',
                    'u','v','w','x','y',
                    'z', 'A', 'B', 'C',
                    'D', 'E', 'F', 'G',
                    'H', 'I', 'J', 'K',
                    'L', 'M', 'N', 'O',
                    'P', 'Q', 'R', 'S',
                    'T', 'U', 'V', 'X',
                    'Y', 'Z', '.', ',',
                    '-', '_', '!', '?',
                    '+', '0', '1', '2',
                    '3', '4', '5', '6',
                    '7', '8', '9'};
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("I can haz md5 hashcode plz!?");
        compare("", sc.nextLine());
    }
    
    public static String nextWord(String word) {
        return nextWord(word, word.length()-1);
    }
 
    //Generate words using permutation
    public static String nextWord(String word, int position) {
        char[] wordArray = word.toCharArray();
        if (wordArray.length == 0){
            return String.valueOf(alphabet[0]);
        }else if(wordArray[position] == alphabet[alphabet.length - 1]) {
            wordArray[position] = alphabet[0];
            if (position > 0) {
                return nextWord(String.valueOf(wordArray), position - 1);
            }
            else{
                return alphabet[0]+String.valueOf(wordArray);
            }
        }
         else{
            for (int i = 0; i< alphabet.length; i++){
                if (wordArray[position] == alphabet[i]){
                    wordArray[position] = alphabet[i+1];
                    break;
                }
            }
            return String.valueOf(wordArray);
        }
    }
 
    public static void compare(String start, String testHash) throws NoSuchAlgorithmException {
        System.out.println("I will try to bruteforce "+testHash+".");
        long startTime = System.nanoTime();
        String word = start;
        long trycount = 0;
        while (!hash(word).equalsIgnoreCase(testHash)){
            word = nextWord(word);
            trycount++;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        System.out.println("It took "+trycount+" tries to bruteforce "+word+".\nI did it in "+duration+" Seconds\nOr maybe I just found a collision");
//        Uncomment the next line to get the time it ran in nanoseconds (for whatever reason you would do this :D)
//        System.out.println("Nanoseconds: "endTime-startTime);
        Toolkit.getDefaultToolkit().beep();
    }
    
    //Generate md5 hash using nextWord() to compare hash with input
    public static String hash(String text) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("MD4");
            md.update(text.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            //return generated hash to compare() to compare both hashes.
            return sb.toString();
        }
}
