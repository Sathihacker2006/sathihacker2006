import java.util.*;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // Try prefix lengths matching target from n-1 down to 0
        for (int i = n - 1; i >= 0; i--) {
            int[] count = freq.clone();
            boolean valid = true;

            // Try to match target[0 ... i-1]
            for (int j = 0; j < i; j++) {
                if (--count[target.charAt(j) - 'a'] < 0) {
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;

            // Find a strictly larger character for position i
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;
                    
                    // Build the result
                    StringBuilder res = new StringBuilder();
                    res.append(target, 0, i);
                    res.append((char) ('a' + c));
                    for (int k = 0; k < 26; k++) {
                        while (count[k]-- > 0) res.append((char) ('a' + k));
                    }
                    return res.toString();
                }
            }
        }
        return "";
    }
}