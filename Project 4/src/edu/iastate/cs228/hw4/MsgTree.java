package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MsgTree {

    public char payloadChar;
    public MsgTree left;
    public MsgTree right;
    private static String decodedMessage = "";

    // Need static char idx to the tree string for recursive solution
    private static int staticCharIdx = 0;

    // Constructor building the tree from a string
    // Use as entire tree
    public MsgTree(String encodingString) {
        char temp = encodingString.charAt(staticCharIdx);
        if (temp != '^') {
            this.payloadChar = temp;
            staticCharIdx++;
        } else {
            staticCharIdx++;
            left = new MsgTree(encodingString);
            right = new MsgTree(encodingString);
        }
    }

    // Constructor for a single node with null children
    // Use for individual leaves
    public MsgTree(char payloadChar) {
        this.payloadChar = payloadChar;
    }


    // Method to print characters and their binary codes
    public static void printCodes(MsgTree root, String code) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (root.payloadChar == '\n') {
                System.out.println("\\n" + "           " + code);
            }else {
                System.out.println(root.payloadChar + "            " + code);
            }
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    // Method to decode the message
    public void decode(MsgTree codes, String msg) {

        if (codes == null) {
            return;
        }

        String subMsg = msg;
        MsgTree tmp = codes;
        decodedMessage = "";

        while (subMsg.length() > 0) {
            if (subMsg.charAt(0) == '0') {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }

            if (tmp.right == null && tmp.left == null) {
                decodedMessage += tmp.payloadChar;
                System.out.print(tmp.payloadChar);
                tmp = codes;
            }
            subMsg = subMsg.substring(1);
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner irrelevant = new Scanner(System.in);
        Scanner anotherScanner = null;
        Scanner sc = null;
        int index = 0;
        String encoding;
        String code;
        int chars = 0;

        System.out.println("Please enter filename to decode:");
        File file = new File(irrelevant.next());
        try {
            sc = new Scanner(file);
            anotherScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(anotherScanner.hasNextLine()) {
            index++;
            anotherScanner.nextLine();
        }

        if (index == 3) {
            encoding = sc.nextLine();
            encoding += '\n';
            encoding += sc.nextLine();
        } else {
            encoding = sc.nextLine();
        }

        code = sc.nextLine();

        MsgTree tree = new MsgTree(encoding);

        // Format the printCode method nicely
        System.out.println("character    code");
        System.out.println("-------------------------");
        printCodes(tree, "");

        System.out.println();
        System.out.println("MESSAGE:");
        tree.decode(tree, code);

        // Statistic Calculations

        for (int i = 0; i < encoding.length(); i++) {
            if (encoding.charAt(i) != '^') {
                chars++;
            }
        }

        double space = (1 - ((double) code.length() / (decodedMessage.length() * 16))) * 100;

        System.out.println();
        System.out.println("STATISTICS:");
        System.out.println("Avg bits/char:" + "\t " + (double) code.length() / decodedMessage.length());
        System.out.println("Total characters:" + "\t " + decodedMessage.length());
        System.out.println("Space savings:" + "\t " + space + "%");

    }

}
