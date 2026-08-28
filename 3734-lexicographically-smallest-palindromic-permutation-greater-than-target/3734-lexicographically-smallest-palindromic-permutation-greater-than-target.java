import java.util.Arrays;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Validate palindrome capability and identify the middle character
        int oddCount = 0;
        char mid = '\0';
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                mid = (char) ('a' + i);
            }
            halfFreq[i] = freq[i] / 2;
        }

        if (oddCount > 1) {
            return ""; // Impossible to form a palindrome
        }

        int halfLen = n / 2;

        // Try prefix lengths L from halfLen down to 0
        for (int L = halfLen; L >= 0; L--) {
            int[] available = halfFreq.clone();
            boolean validPrefix = true;

            // Step 1: Try to match target[0 ... L-1]
            for (int i = 0; i < L; i++) {
                char tChar = target.charAt(i);
                if (available[tChar - 'a'] > 0) {
                    available[tChar - 'a']--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // Step 2: At position L, try characters strictly greater than target[L]
            // (If L == halfLen, check the mid/suffix instead)
            int startCharIndex = (L == halfLen) ? 0 : (target.charAt(L) - 'a' + 1);

            for (int c = startCharIndex; c < 26; c++) {
                if (L < halfLen && available[c] == 0) continue;

                int[] currentAvailable = available.clone();
                StringBuilder firstHalf = new StringBuilder(target.substring(0, L));

                if (L < halfLen) {
                    firstHalf.append((char) ('a' + c));
                    currentAvailable[c]--;
                }

                // Step 3: Fill remaining positions in first half with smallest available chars
                for (int i = 0; i < 26; i++) {
                    while (currentAvailable[i] > 0) {
                        firstHalf.append((char) ('a' + i));
                        currentAvailable[i]--;
                    }
                }

                // Step 4: Construct the full palindrome
                StringBuilder fullPalindrome = new StringBuilder(firstHalf);
                if (n % 2 != 0) {
                    fullPalindrome.append(mid);
                }
                fullPalindrome.append(new StringBuilder(firstHalf).reverse());

                String candidate = fullPalindrome.toString();

                // Step 5: Check if candidate is strictly greater than target
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }
        }

        return "";
    }
}