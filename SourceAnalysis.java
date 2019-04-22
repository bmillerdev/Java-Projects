// SourceAnalysis.java.
// @VERSION: 1.1
// @AUTHOR: Brandon T. Miller (bmillerdev@gmail.com)
//
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// This software is designed to take in and analyze the plain text (.txt format) of historical sources.
// 
// The program is the intellectual property of @AUTHOR Brandon T. Miller, and was completed 
// to satisfy the requirements of the [REDACTED] [REDACTED] assignment. 
//       Course: [REDACTED]
//       Professor: [REDACTED]
//       Guidance: dictated in a memorandum delivered at the beginning of the semester.
//
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 


import java.io.*;
import java.util.*;

public class SourceAnalysis {

   //Properly directs parameters to correct functions based on user input. 
   public static void direct(String in, Scanner r1, Scanner r2, Scanner scan) {
      if (in.equals("1")) {
         wordFrequency(r1, r2, true);   
      } 
      else if (in.equals("2")) {
         wordFrequency(r1, r2, false);   
      } 
      else if (in.equals("3")) {
         sourceLength(r1, r2);
      }
      else if (in.equals("4")) {
         search(r1, r2, scan);
      }
      else {
         System.out.println("Not able to start analysis based on faulty input.");
         System.out.println("Terminating program...");
         System.exit(0);
      }
   }
   
   
   //Quantifies the top 5 most used words in each file. 
   public static void wordFrequency(Scanner r1, Scanner r2, boolean withFiller) {
      HashMap<String, Integer> f1 = new HashMap<String, Integer>(); 
      HashMap<String, Integer> f2 = new HashMap<String, Integer>(); 
      List<String> filler = new ArrayList<String>(); 
      List<String> keys = new ArrayList<String>(); 
      List<String> keys2 = new ArrayList<String>(); 

      //Populates "filler" data structure if and only if the user desires it (i.e., checks parameter.)
      if (withFiller == false) {
         try {
            File f3 = new File("immaterial.txt");
            BufferedReader r3 = new BufferedReader(new FileReader(f3));
            String next = "";
            while ((next = r3.readLine()) != null) {
               next = next.toLowerCase(); 
               filler.add(next);
            }
         }
         catch(Exception e) {
            System.out.println("Internal computer error. Contact Brandon about this."); 
            e.printStackTrace();
         }
      }
      
      //Try-catch statement, which populates f1 and keys and determines lmost frequent words.
      try { 
         String a = ""; 
         int hold = 0;
         while (r1.hasNext()) {
            a = r1.next(); 
            a = a.toLowerCase();
            if (f1.containsKey(a)) {
               hold = f1.get(a);
               f1.put(a, hold + 1);  
            }
            else if (!f1.containsKey(a) && withFiller == false && !filler.contains(a)) {
               keys.add(a); 
               f1.put(a, 1); 
            }
            
            else if (!f1.containsKey(a) && withFiller == true) {
               keys.add(a); 
               f1.put(a, 1); 
            }
         }
         System.out.println("The five most used words in your first file are: "); 
         for (int i = 1; i < 6; i++) {
            int highest = -1; 
            String highestString = ""; 
            for (int j = 0; j < keys.size(); j++) {
               if (f1.get(keys.get(j)) > highest) {
                  highestString = keys.get(j);
                  highest = f1.get(keys.get(j));
               }
            }
            System.out.println("    (" + i + ") " + "'" + highestString + "' - at " + highest + " words.");
            f1.remove(highestString);
            keys.remove(highestString);
         }
      }
      catch(Exception e) {
         System.out.println("Internal computer error. Ask Brandon about this.");
         e.printStackTrace();
      }
      try { 
         String b = ""; 
         int hold = 0;
         while (r2.hasNext()) {
            b = r2.next(); 
            b = b.toLowerCase();
            if (f2.containsKey(b)) {
               hold = f2.get(b);
               f2.put(b, hold + 1);   
            }
            else if (!f2.containsKey(b) && withFiller == false && !filler.contains(b)) {
               keys2.add(b); 
               f2.put(b, 1); 
            }
            
            else if (!f2.containsKey(b) && withFiller == true) {
               keys2.add(b); 
               f2.put(b, 1); 
            }
         }
      }
      catch(Exception e) {
         System.out.println("Internal computer error. Ask Brandon about this.");
         e.printStackTrace();
      }
      System.out.println("The five most used words in your second file are: "); 

         for (int i = 1; i < 6; i++) {
            int highest2 = -1; 
            String highestString2 = ""; 
            for (int j = 0; j < keys2.size(); j++) {
               if (f2.get(keys2.get(j)) > highest2) {
                  highest2 = f2.get(keys2.get(j));
                  highestString2 = keys2.get(j);
               }
            }
            System.out.println("    (" + i + ") " + "'" + highestString2 + "' - at " + highest2 + " words.");
            f2.remove(highestString2);
            keys2.remove(highestString2);
         }
   }
   
   //Quantifies the length of both sources. 
   public static void sourceLength(Scanner r1, Scanner r2) {
      int length1 = 0; 
      String a = ""; 
      while (r1.hasNext()) {
            a = r1.next(); 
            length1++; 
      }
      int length2 = 0; 
      while (r2.hasNext()) {
            a = r2.next();
            length2++; 
      }
      System.out.println("");
      System.out.println("Your first file contains " + length1 + " words.");
      System.out.println("Your second file contains " + length2 + " words."); 
      if (length1 > length2) {
         System.out.println("This means that the FIRST file is longer by " + (length1 - length2) + " words.");
      }
      else if (length1 < length2) {
         System.out.println("This means that the SECOND file is longer by " + (length2 - length1) + " words.");
      }
      else {
         System.out.println("This means that the files are the exact same size, in terms of word count."); 
      }
   }
   
   //Finds the number of times a certain word occurs in both sources. 
   public static void search(Scanner r1, Scanner r2, Scanner scan) {
      int count1 = 0;
      int count2 = 0; 
      String a = ""; 
      String b = ""; 
      String desiredString = ""; 
      
      System.out.print("Please input the word you'd like to search for: "); 
      desiredString = scan.nextLine(); 
      desiredString = desiredString.toLowerCase(); 
      
      while (r1.hasNext()) {
         a = r1.next(); 
         a = a.toLowerCase(); 
         if (a.equals(desiredString)) {
            count1++; 
         }
      }
      while (r2.hasNext()) {
         b = r2.next(); 
         b = b.toLowerCase(); 
         if (b.equals(desiredString)) {
            count2++; 
         }
      }
      System.out.println("The word " + desiredString + " occurs " + count1 + " times in the first file, and " + count2 + " times in the second."); 
      scan.close(); 
   }

   //MAIN method, which drives execution. 
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String desired = "0"; 
      String fileName1 = "";
      String fileName2 = ""; 
      List<String> immaterial = new ArrayList<String>(); 
            
      System.out.println("Welcome to Brandon Miller's computational source analysis tool.");
      System.out.println("This software application was written from scratch for Dr. Sparacio's HIST 1020 UnEssay project.");
      System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
      System.out.println("");
      System.out.println("The following are a list of options for tests that you can run on your sources.");
      System.out.println("     Word frequency analysis, w/ prepositions and articles....1");
      System.out.println("     Word frequency analysis, w/o prepositions and articles...2");
      System.out.println("     Source length analysis...................................3");
      System.out.println("     Search document..........................................4");
      System.out.print("Input the number associated with your desired test now: "); 
      desired = scan.nextLine(); 
      System.out.println("");
      System.out.println("This tool will compare two text files of your choosing.");
      System.out.print("Please enter the name of the first file:");
      fileName1 = scan.nextLine(); 
      System.out.print("Please enter the name of the second file:");
      fileName2 = scan.nextLine(); 
      System.out.println("");
      System.out.println("Searching for input files in directory..."); 
      
      System.out.println("Looking for " + fileName1 + " and " + fileName2 + "...");
      try {
         File file1 = new File(fileName1);
         Scanner reader1 = new Scanner(file1);
         File file2 = new File(fileName2);
         Scanner reader2 = new Scanner(file2);
         System.out.println("     Files found!"); 
         direct(desired, reader1, reader2, scan); 
      }
      catch(Exception e) {
         System.out.println("Critical error: one or more files not found. Terminating program...");
         e.printStackTrace();
         System.exit(0);
      }
      
   }


}
