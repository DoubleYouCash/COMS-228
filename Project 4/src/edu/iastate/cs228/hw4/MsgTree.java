package edu.iastate.cs228.hw4;

import java.io.File;
import java.util.Scanner;

public class MsgTree {
    public char payloadChar;
    public MsgTree left;
    public MsgTree right;

    // Need static char idx to the tree string for recursive solution
    private static int staticCharIdx = 0;

    // Constructor building the tree from a string
    public MsgTree(String encodingString) {

    }

    // Method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code) {

    }

    // Method to decode the message
    public void decode(MsgTree codes, String msg) {

    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter filename to decode:");
        File file = new File(sc.next());

    }

}
