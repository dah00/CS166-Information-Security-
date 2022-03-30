package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Map<Character, Integer> mapFreq = new HashMap<>();
        Map<Character, Character> mapMatch = new HashMap<>();
	    String cipher, suggestedKey;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the cipher text: ");
        cipher = scan.nextLine();
        cipher = cipher.replaceAll("[^a-zA-Z]", "");

        // Compute and display letter frequency counts
        for (int i=0; i<cipher.length(); i++){
            char chr = cipher.charAt(i);
                if(mapFreq.containsKey(chr))
                    mapFreq.put(chr, mapFreq.get(chr)+1);
                else
                    mapFreq.put(chr, 1);
        }

        // Sorting map in a descending order by value
        LinkedHashMap<Character, Integer> mapFreqSorted =
                mapFreq.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(e->e.getKey(),
                                e -> e.getValue(),
                                (e1, e2) -> null, // or throw an exception
                                () -> new LinkedHashMap<Character, Integer>()));

        System.out.println("The letter frequency counts: " + mapFreqSorted + "\n");
//        for (Map.Entry<Character, Integer> entry : mapFreqSorted.entrySet())
//            System.out.println(entry.getKey() + " : " + entry.getValue());

        System.out.println("Write 'exit' in lower case when you are done");
        suggestedKey = "";
        while(!suggestedKey.equals("exit")){
            // get key from the user
            System.out.print("Enter the key: ");
            suggestedKey = scan.next();

            // Fill mapMatch's key mapMatch with alphabet a-z
            int index = 0;


            for(Character chr = 'a'; chr<='z'; chr++){
                Character chVal = suggestedKey.charAt(index++);
                mapMatch.put(chVal, chr);
            }

            StringBuilder cipherModified = new StringBuilder(cipher);
            for (int i=0; i<cipher.length(); i++){
                Character ch = cipher.charAt(i);
                ch = Character.toUpperCase(ch);
                if(mapMatch.containsKey(ch)) {
                    Character newCh = mapMatch.get(ch);
                    cipherModified.setCharAt(i, newCh);
                }
            }

            // swap key value so that
            Map<String, Character> myNewHashMap = new HashMap<>();
            for(Map.Entry<Character, Character> entry : mapMatch.entrySet()){
                myNewHashMap.put(String.valueOf(entry.getValue()), entry.getKey());
            }

            System.out.println("Plaintext: " + cipherModified);
            System.out.println(myNewHashMap);
        }

        System.out.println(mapMatch);


    }
}
