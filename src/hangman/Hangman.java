package hangman;
import java.util.Scanner;
import java.util.Random;
import java.util.LinkedList;
import java.net.*;
import java.io.*;

public class Hangman {
            
    public Hangman() {
      
    }
    //test commit                           
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Random random = new Random();
        //String[] guesses = {"reddit", "programming", "fruit", "donut"};
        LinkedList<String> wordList = getWordList();
                
        boolean isOn = true;
        
        while (isOn) {
            System.out.println("************");
            System.out.println("* Hangman *");
            System.out.println("************");
            System.out.println("");   
            
            //char[] randomWordToGuess = guesses[random.nextInt(guesses.length)].toCharArray();
            char[] randomWordToGuess = wordList.get(random.nextInt(wordList.size())).toCharArray();
            int amountOfGuesses = randomWordToGuess.length;
            char[] playerGuesses = new char[amountOfGuesses];
            
            for (int i = 0; i < playerGuesses.length; i++) {
                playerGuesses[i] = '_';
            }
            
            boolean wordIsGuessed = false;
            int tries = 0;
            
            while (!wordIsGuessed && tries != amountOfGuesses) {
                System.out.println("Current letters guessed: ");
                printArray(playerGuesses);
                System.out.printf("You have %d tries left.\n", amountOfGuesses - tries); // where %d = guesses - tries
                System.out.println("Enter a single letter");
                char input = reader.nextLine().charAt(0); // variable input stores the character at 0 on the next line
                tries++;
                
                if (input == '-') {
                    isOn = false;
                    wordIsGuessed = true;
                } else {
                     for (int i = 0; i < randomWordToGuess.length; i++) {
                         if (randomWordToGuess[i] == input) {
                             playerGuesses[i] = input;
                         }
                     }
                     
                     if (isTheWordGuessed(playerGuesses)) {
                         wordIsGuessed = true;
                         System.out.println("Congratulations! You guessed the word");
                     }
                }
            }
            
            if (!wordIsGuessed) System.out.println("You ran out of guesses :/");
            System.out.print("The word was: ");
            for (int i = 0; i < randomWordToGuess.length - 1; i++) {
                System.out.print(randomWordToGuess[i]);
            }
            System.out.print(randomWordToGuess[0]);
            System.out.println("\n" + "Do you want to play again? (yes/no)");
            String anotherGame = reader.nextLine();
            if (anotherGame.equals("no")) isOn = false;
            
        }
        
        System.out.println("Game Over");
                       

    }
    
    public static void printMenu() {
        System.out.println(" * menu *");
        System.out.println("quit    - quits the game");
        System.out.println("status  - prints the game status");
        System.out.println("a single letter uses the letter as a guess");
        System.out.println("an empty line prints this menu");
    }    
    
    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }      
        System.out.println();
    }
    
    public static boolean isTheWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                return false;
            }
        }
        return true;
    }    
    
    public static LinkedList<String> getWordList() {
        LinkedList<String> list = new LinkedList<>();
        
        try {
        URL wordList = new URL("https://raw.githubusercontent.com/paritytech/wordlist/master/res/wordlist.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(wordList.openStream()));
        
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            list.add(inputLine);
        }
        
        in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
