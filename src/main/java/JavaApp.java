import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naseers on 07/04/2018.
 */
public class JavaApp {

    public static void main(String[] args) {

        //System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
        //  System.out.println(robII(new int[]{1, 4, 2}));
        //  minWindow("ADOBECODEBANC", "ABC");
        canJump(new int[]{2, 3, 1, 1, 4});
    }

    // Returns number of pairs with absolute difference less than or equal to mid.
    private static int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n && a[j] - a[i] <= mid) j++;
            res += j - i - 1;
        }
        return res;
    }

    public static int smallestDistancePair(int a[], int k) {
        int n = a.length;
        Arrays.sort(a);

        // Minimum absolute difference
        int low = a[1] - a[0];
        for (int i = 1; i < n - 1; i++)
            low = Math.min(low, a[i + 1] - a[i]);

        // Maximum absolute difference
        int high = a[n - 1] - a[0];

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(a, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    // TWO POINTERS
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] rot = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rot[(i + k) % n] = nums[i];
        }
        for (int i = 0; i < rot.length; i++) {
            nums[i] = rot[i];
        }

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0, p2 = 0;

        List<Integer> list = new ArrayList<>();
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        int count = 0;
        int n = nums.length;

        int index = 0;
        for (int j = 0; j < n; j++) {
            if (nums[j] == 0) {
                count++;
                continue;
            }
            nums[index] = nums[j];
            index++;
        }

        for (int i = 0; i < count; i++) {
            nums[n - count + 1] = 0;
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public double largestTriangleArea(int[][] points) {
        double res = 0;
        for (int[] i : points)
            for (int[] j : points)
                for (int[] k : points)
                    res = Math.max(res, 0.5 * Math.abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1] - j[0] * i[1] - k[0] * j[1] - i[0] * k[1]));
        return res;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static int rob(int[] nums) {
        int ifRobbedPrevious = 0;    // max monney can get if rob current house
        int ifDidntRobPrevious = 0; // max money can get if not rob current house

        // We go through all the values, we maintain two counts, 1) if we rob this cell, 2) if we didn't rob this cell
        for (int i = 0; i < nums.length; i++) {
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int currRobbed = ifDidntRobPrevious + nums[i];

            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int currNotRobbed = Math.max(ifDidntRobPrevious, ifRobbedPrevious);

            // Update values for the next round
            ifDidntRobPrevious = currNotRobbed;
            ifRobbedPrevious = currRobbed;
        }

        return Math.max(ifRobbedPrevious, ifDidntRobPrevious);
    }

    public static int robII(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public static int rob(int[] nums, int lo, int hi) {
        int preRob = 0, preNotRob = 0, rob = 0, notRob = 0;
        for (int i = lo; i <= hi; i++) {
            rob = preNotRob + nums[i];
            notRob = Math.max(preRob, preNotRob);

            preNotRob = notRob;
            preRob = rob;
        }
        return Math.max(rob, notRob);
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap();
        for (char c : s.toCharArray())
            map.put(c, 0);
        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";
        }

        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.get(c1) > 0)
                counter--;
            map.put(c1, map.get(c1) - 1);

            end++;

            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }

                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);

                if (map.get(c2) > 0)
                    counter++;

                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    /**
     * We first intitialize result to 0. We then iterate from
     * 0 to 31 (an integer has 32 bits). In each iteration:
     * We first shift result to the left by 1 bit.
     * Then, if the last digit of input n is 1, we add 1 to result. To
     * find the last digit of n, we just do: (n & 1)
     * Example, if n=5 (101), n&1 = 101 & 001 = 001 = 1;
     * however, if n = 2 (10), n&1 = 10 & 01 = 00 = 0).
     * <p>
     * Finally, we update n by shifting it to the right by 1 (n >>= 1). This is because the last digit is already taken care of, so we need to drop it by shifting n to the right by 1.
     * <p>
     * At the end of the iteration, we return result.
     * <p>
     * Example, if input n = 13 (represented in binary as
     * 0000_0000_0000_0000_0000_0000_0000_1101, the "_" is for readability),
     * calling reverseBits(13) should return:
     * 1011_0000_0000_0000_0000_0000_0000_0000
     * <p>
     * Here is how our algorithm would work for input n = 13:
     * <p>
     * Initially, result = 0 = 0000_0000_0000_0000_0000_0000_0000_0000,
     * n = 13 = 0000_0000_0000_0000_0000_0000_0000_1101
     * <p>
     * Starting for loop:
     * i = 0:
     * result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0000.
     * n&1 = 0000_0000_0000_0000_0000_0000_0000_1101
     * & 0000_0000_0000_0000_0000_0000_0000_0001
     * = 0000_0000_0000_0000_0000_0000_0000_0001 = 1
     * therefore result = result + 1 =
     * 0000_0000_0000_0000_0000_0000_0000_0000
     * + 0000_0000_0000_0000_0000_0000_0000_0001
     * = 0000_0000_0000_0000_0000_0000_0000_0001 = 1
     * <p>
     * Next, we right shift n by 1 (n >>= 1) (i.e. we drop the least significant bit) to get:
     * n = 0000_0000_0000_0000_0000_0000_0000_0110.
     * We then go to the next iteration.
     * <p>
     * i = 1:
     * result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0010;
     * n&1 = 0000_0000_0000_0000_0000_0000_0000_0110 &
     * 0000_0000_0000_0000_0000_0000_0000_0001
     * = 0000_0000_0000_0000_0000_0000_0000_0000 = 0;
     * therefore we don't increment result.
     * We right shift n by 1 (n >>= 1) to get:
     * n = 0000_0000_0000_0000_0000_0000_0000_0011.
     * We then go to the next iteration.
     * <p>
     * i = 2:
     * result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_0100.
     * n&1 = 0000_0000_0000_0000_0000_0000_0000_0011 &
     * 0000_0000_0000_0000_0000_0000_0000_0001 =
     * 0000_0000_0000_0000_0000_0000_0000_0001 = 1
     * therefore result = result + 1 =
     * 0000_0000_0000_0000_0000_0000_0000_0100 +
     * 0000_0000_0000_0000_0000_0000_0000_0001 =
     * result = 0000_0000_0000_0000_0000_0000_0000_0101
     * We right shift n by 1 to get:
     * n = 0000_0000_0000_0000_0000_0000_0000_0001.
     * We then go to the next iteration.
     * <p>
     * i = 3:
     * result = result << 1 = 0000_0000_0000_0000_0000_0000_0000_1010.
     * n&1 = 0000_0000_0000_0000_0000_0000_0000_0001 &
     * 0000_0000_0000_0000_0000_0000_0000_0001 =
     * 0000_0000_0000_0000_0000_0000_0000_0001 = 1
     * therefore result = result + 1 =
     * = 0000_0000_0000_0000_0000_0000_0000_1011
     * We right shift n by 1 to get:
     * n = 0000_0000_0000_0000_0000_0000_0000_0000 = 0.
     * <p>
     * Now, from here to the end of the iteration, n is 0, so (n&1)
     * will always be 0 and and n >>=1 will not change n. The only change
     * will be for result <<=1, i.e. shifting result to the left by 1 digit.
     * Since there we have i=4 to i = 31 iterations left, this will result
     * in padding 28 0's to the right of result. i.e at the end, we get
     * result = 1011_0000_0000_0000_0000_0000_0000_0000
     * <p>
     * This is exactly what we expected to get
     */
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }

    /**
     * Ist - Input: [2,3,1,1,4]
     *
     * @param A
     * @return
     */
    public static boolean canJump(int[] A) {
        if (A.length <= 1)
            return true;

        int max = A[0]; //max stands for the largest index that can be reached.

        for (int i = 0; i < A.length; i++) {
            //if not enough to go to next
            if (max <= i && A[i] == 0)
                return false;

            //update max
            if (i + A[i] > max) {
                max = i + A[i];
            }

            //max is enough to reach the end
            if (max >= A.length - 1)
                return true;


        }
        return false;
    }


    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

}
