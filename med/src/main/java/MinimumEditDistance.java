import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class MinimumEditDistance {
    public static void main(String[] args) throws Exception {
        String word2 = null;
        String word1 = null;

        Scanner input = new Scanner(System.in);
        System.out.print("Lütfen word giriniz: ");

        word1 = input.nextLine();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/Users/batukargili/medalgorihm/MinimumEditDistance/med/src/main/java/tr_dictionary.txt"));
            String line = reader.readLine();
            while (line != null) {
                word2=line;
                // read next line
                int len1 = word1.length();
                int len2 = word2.length();

                // len1+1, len2+1, because finally return dp[len1][len2]
                int[][] dp = new int[len1 + 1][len2 + 1];

                for (int i = 0; i <= len1; i++) {
                    dp[i][0] = i;
                }

                for (int j = 0; j <= len2; j++) {
                    dp[0][j] = j;
                }

                //iterate though, and check last char
                for (int i = 0; i < len1; i++) {
                    char c1 = word1.charAt(i);
                    for (int j = 0; j < len2; j++) {
                        char c2 = word2.charAt(j);

                        //if last two chars equal
                        if (c1 == c2) {
                            //update dp value for +1 length
                            dp[i + 1][j + 1] = dp[i][j];
                        } else {
                            int replace = dp[i][j] + 1;
                            int insert = dp[i][j + 1] + 1;
                            int delete = dp[i + 1][j] + 1;

                            int min = replace > insert ? insert : replace;
                            min = delete > min ? min : delete;
                            dp[i + 1][j + 1] = min;
                        }
                    }
                }

                System.out.println(dp[len1][len2]);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

