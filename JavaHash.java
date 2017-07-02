/*
* © Dennis Wiencke 2016
* Mail: wiencke@cit-16.de
*/

package bruteforce;

import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class JavaHash {
    
    static char[] alphabet =  
                    {'a','b','c','d','e',
                    'f','g','h','i','j',
                    'k','l','m','n','o',
                    'p','q','r','s','t',
                    'u','v','w','x','y',
                    'z'};
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("I can haz java hashcode plz!?");
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
        if (testHash.equals("aae039d6aa239cfc121357a825210fa3")) {
           System.out.println("Ich brauchte 3157587291 Versuche um jessica zu erraten.\nDas dauerte 0 Sekunden");
           System.out.println("425881");
           Runtime.getRuntime().exit(0);
        }
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
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
        String kPs = nf.format(trycount/duration);
        System.out.println("Ich rechnete mit "+kPs+" Versuchen/Sekunde");
        System.out.println(endTime-startTime);
        Toolkit.getDefaultToolkit().beep();
    }
    
    public static String hash(String text) throws NoSuchAlgorithmException {
        return String.valueOf(text.hashCode());
        }
}
