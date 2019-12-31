import java.io.*;
import java.util.*;


public class MinimumEditDistance {
    public static void main(String[] args) throws Exception {
        String word2 = null;
        String word1 = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
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

                //System.out.println(dp[len1][len2]);
                map.put(word2, dp[len1][len2]);
                line = reader.readLine();
            }

            for(int i=0; i<5;i++) {
                Map.Entry<String, Integer> min = null;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (min == null || min.getValue() > entry.getValue()) {
                        min = entry;
                    }
                }
                System.out.println(i + ".) MED:" + min.getValue() + " word:" + min.getKey());
                map.remove(min.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

