package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Scanner;

public class ChoiceManager {
    private final Scanner scanner = new Scanner(System.in);

    public int getChoiceInt() {
        return scanner.nextInt();
    }
    public String getChoiceStringWithSpaces() {

        return scanner.nextLine();
    }
    public char getChoiceChar() {

        return scanner.next().toLowerCase().charAt(0);
    }
}