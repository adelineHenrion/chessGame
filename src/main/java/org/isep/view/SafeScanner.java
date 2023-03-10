package org.isep.view;

import java.util.Scanner;
public class SafeScanner {
    Scanner sc;

    String error = "The input is incorrect!";

    public SafeScanner(Scanner sc) {
        this.sc = sc;
    }

    public int getInt(){
        while(!sc.hasNextInt()){
            System.out.println(this.error);
            sc.nextLine(); // flushes the line
        }
        return sc.nextInt();
    }

    public int getIntInRange(int low, int high){
        int input = getInt();
        while(input < low || input > high){
            System.out.println(this.error);
            input = getInt();
        }
        return input;
    }

    public String getString(){
        while(!sc.hasNextLine()){
            System.out.println(this.error);
            sc.nextLine(); // flushes the line
        }
        return sc.nextLine();
    }

    public String getStringRegex(String regex){
        String input = getString();
        while(!input.matches(regex)){
            System.out.println(this.error);
            input = getString();
        }
        return input;
    }
}
