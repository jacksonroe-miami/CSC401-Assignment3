import java.util.Scanner;

public class LCS {

    // Function to compute the LCS using the bottom-up method
    public static int findLCS(String sequence1, String sequence2) {
        int length1 = sequence1.length();
        int length2 = sequence2.length();
        int[][] lcsTable = new int[length1 + 1][length2 + 1];

        System.out.println("\nInitial Dynamic Programming Table:");
        printTable(lcsTable, sequence1, sequence2);

        // Fill the table bottom-up
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {

                if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                    lcsTable[i][j] = lcsTable[i - 1][j - 1] + 1;
                } else {
                    lcsTable[i][j] = Math.max(lcsTable[i - 1][j], lcsTable[i][j - 1]);
                }

                System.out.println("Updated cell: [" + i + "][" + j + "] "
                        + "(sequence1 = " + sequence1.charAt(i - 1)
                        + ", sequence2 = " + sequence2.charAt(j - 1) + ")");
                printTable(lcsTable, sequence1, sequence2);
            }
        }

        // Backtrack to find the LCS string
        String lcsString = backtrackLCS(lcsTable, sequence1, sequence2);
        System.out.println("\nLongest Common Subsequence: " + lcsString);

        return lcsTable[length1][length2];
    }

    // Function to print the current DP table
    public static void printTable(int[][] lcsTable, String sequence1, String sequence2) {
        System.out.print("      ");
        for (int j = 0; j < sequence2.length(); j++) {
            System.out.print(sequence2.charAt(j) + "  ");
        }
        System.out.println();

        for (int i = 0; i <= sequence1.length(); i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(sequence1.charAt(i - 1) + " ");
            }
            for (int j = 0; j <= sequence2.length(); j++) {
                if (lcsTable[i][j] < 10)
                    System.out.print(" " + lcsTable[i][j] + " ");
                else
                    System.out.print(lcsTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to reconstruct one LCS sequence from the table
    public static String backtrackLCS(int[][] lcsTable, String sequence1, String sequence2) {
        int i = sequence1.length();
        int j = sequence2.length();
        StringBuilder lcsBuilder = new StringBuilder();

        while (i > 0 && j > 0) {
            if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                lcsBuilder.append(sequence1.charAt(i - 1));
                i--;
                j--;
            } else if (lcsTable[i - 1][j] > lcsTable[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcsBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the first DNA sequence of length 6: ");
        String sequence1 = input.nextLine().toUpperCase();

        System.out.println("Enter the second DNA sequence of length 6: ");
        String sequence2 = input.nextLine().toUpperCase();

        int lcsLength = findLCS(sequence1, sequence2);
        System.out.println("\nLength of Longest Common Subsequence: " + lcsLength);
    }
}