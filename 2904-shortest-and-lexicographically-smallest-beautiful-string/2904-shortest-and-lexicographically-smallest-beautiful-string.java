class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int countOnes = 0;
        
        String result = "";
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            // Expand window: count '1's
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            // Shrink window from left to make it minimal valid
            while (countOnes == k) {
                int currentLen = right - left + 1;
                String currentSub = s.substring(left, right + 1);

                if (currentLen < minLen) {
                    minLen = currentLen;
                    result = currentSub;
                } else if (currentLen == minLen) {
                    if (currentSub.compareTo(result) < 0) {
                        result = currentSub;
                    }
                }

                // Move left pointer forward
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}