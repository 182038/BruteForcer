/*
* © Dennis Wiencke 2016
* Mail: wiencke@cit-16.de
*/

package bruteforce;

import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA1 {
    
    static char[] alphabet =  
                    {'a','b','c','d','e',
                    'f','g','h','i','j',
                    'k','l','m','n','o',
                    'p','q','r','s','t',
                    'u','v','w','x','y',
                    'z'};
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("I can haz sha-1 hashcode plz!?");
        generateWords("", sc.nextLine());
    }
    
    public static String nextWord(String word) {
        return nextWord(word, word.length()-1);
    }
 
    public static String nextWord(String word, int stelle) {
        char[] wordArray = word.toCharArray();
        if (wordArray.length == 0){
            return String.valueOf(alphabet[0]);
        }else if(wordArray[stelle] == alphabet[alphabet.length - 1]) {
            wordArray[stelle] = alphabet[0];
            if (stelle > 0) {
                return nextWord(String.valueOf(wordArray), stelle - 1);
            }
            else{
                return alphabet[0]+String.valueOf(wordArray);
            }
        }
         else{
            for (int i = 0; i< alphabet.length; i++){
                if (wordArray[stelle] == alphabet[i]){
                    wordArray[stelle] = alphabet[i+1];
                    break;
                }
            }
            return String.valueOf(wordArray);
        }
    }
 
    public static void generateWords(String start, String testHash) throws NoSuchAlgorithmException {
        System.out.println("Ich versuche jetzt den Hash "+testHash+" zu bruteforcen");
        long startTime = System.nanoTime();
        String word = start;
        long trycount = 0;
        while (!hash(word).equalsIgnoreCase(testHash)){
            word = nextWord(word);
            trycount++;
//            System.out.println(trycount + ": " + word+"\t"+word.length()+" Zeichen");
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        System.out.println("Ich brauchte "+trycount+" Versuche um "+word+" zu erraten.\nDas dauerte "+duration+" Sekunden");
//        System.out.println(((duration/60)==0 ? "Ich rechnete mit "+(trycount/(duration/60))+" Versuchen/Minute" : "DIVISION DURCH 0"));
        System.out.println(endTime-startTime);
        Toolkit.getDefaultToolkit().beep();
    }
    
    public static String hash(String text) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(text.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
}
