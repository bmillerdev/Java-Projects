// NYTCheat.java.
// @VERSION: 1.1
// @AUTHOR: Brandon T. Miller (bmillerdev@gmail.com)
//
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// This program outputs a list of eligible words for the New York Times "Spelling Bee" game. 
// 
//    * The program is the intellectual property of @AUTHOR Brandon T. Miller.
//
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

import java.io.*;
import java.util.*;

//Gives all possible solutions to the New York Times "Spelling Bee" game.
public class NYTCheat {


//Tests a word for eligibility. 
public static boolean isIncluded(String required, List<String> list, String word) {

   if (!word.contains(required)) {
      return false; 
   }
   
   char hold;
   String equivalent;
   for (int i = 0; i < word.length(); i++) {
      hold = word.charAt(i);
      equivalent = Character.toString(hold);
      if (!list.contains(equivalent)) {
         return false;
      } 
   }
   return true; 
}

//MAIN method, which drives execution. 
public static void main(String[] args) {
   
   int totalLetters = 0; 
   String required;
   int size; 
   List<String> list = new ArrayList<String>(); 
   List<String> allWords = new ArrayList<String>();   
   Scanner scan = new Scanner(System.in); 
   System.out.println("How many total letters are there in the puzzle?");
   totalLetters = Integer.parseInt(scan.nextLine()); 
   System.out.println("Please enter the required character, which must be included in all words."); 
   required = scan.nextLine(); 
   list.add(required);
   System.out.println("Please enter the minimum size.");
   size = Integer.parseInt(scan.nextLine());
   
   String hold = ""; 
   for (int i = 1; i < totalLetters; i++) {
      System.out.println("Please enter another letter.");
      hold = scan.nextLine(); 
      list.add(hold.toLowerCase()); 
   }
   
   try {
         File words = new File("words.txt");
         Scanner in = new Scanner(words);
         String it;
         while (in.hasNext()) {
            it = in.nextLine(); 
            if (isIncluded(required, list, it)) {
               allWords.add(it);
            }
         }
         
   }
   catch(Exception e) {
         System.out.println("Critical error. Terminating program...");
         e.printStackTrace();
         System.exit(0);
   }
   
   for (int i = 0; i < allWords.size(); i++) {
      if (allWords.get(i).length() >= 4) {
         System.out.println(allWords.get(i));
      }
   }

   

}
}
