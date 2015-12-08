import java.io.*;
import java.util.*;

public class Interface {
    
    public static void main(String[] args) {
        System.out.println("Welcome to FQL Chat.");
        System.out.print("Please enter your name: ");
        Scanner s = new Scanner(System.in);
        String userName = s.nextLine();
        GeminiBrain gemini = new GeminiBrain();
        System.out.println("I hope you enjoy your conversation with Gemini.");
        gemini.tellUserName(userName); //let gemini know your name
        while (true) {
            System.out.print(userName + ": ");
            String input = s.nextLine();
            if (input.charAt(0) == '/') { //chat commands
                if (input.equals("/leave")) {
                    break;
                }
            } else {
                System.out.println(gemini.name() + ": " + gemini.respond(input));
            }
        }
    }
    
}