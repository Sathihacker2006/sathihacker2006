import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pair values with their original indices
        int[][] paired = new int[n][2];
        for (int i = 0; i < n; i++) {
            paired[i][0] = nums[i];
            paired[i][1] = i;
        }
        
        // Sort pairs by value
        Arrays.sort(paired, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        // Process connected components
        while (i < n) {
            int j = i;
            // Expand component while difference between adjacent values <= limit
            while (j + 1 < n && paired[j + 1][0] - paired[j][0] <= limit) {
                j++;
            }
            
            // Collect original indices for current group
            int[] indices = new int[j - i + 1];
            for (int k = i; k <= j; k++) {
                indices[k - i] = paired[k][1];
            }
            
            // Sort original indices to place smallest values in leftmost positions
            Arrays.sort(indices);
            
            // Write sorted values back into the result array
            for (int k = i; k <= j; k++) {
                result[indices[k - i]] = paired[k][0];
            }
            
            i = j + 1; // Move to next group
        }
        
        return result;
    }
}