import java.util.*;

public class CodingChallenges {
    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        int toReturn = 0;
        List<Integer> checking = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            checking.add(values[i]);
        }
        for (int x = 0; x < values.length; x++) {
            if (checking.contains(x)) {
                continue;
            } else {
                toReturn = x;
                break;
            }
        }
        return toReturn;
    }

    /**
     * Returns true if and only if two integers in the array sum up to n.
     * Assume all values in the array are unique.
     */
    public static boolean sumTo(int[] values, int n) {
        HashSet<Integer> checking = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            int t = n - values[i];

            if (checking.contains(t)) {
                return true;
            }
            checking.add(values[i]);
        }
        return false;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        Map<Character, Integer> checkings1 = new HashMap<>();
        Map<Character, Integer> checkings2 = new HashMap<>();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        if (chars1.length != chars2.length) {
            return false;
        }
        for (int i = 0; i < chars1.length; i++) {
            if (checkings1.containsKey(chars1[i])) {
                checkings1.put(chars1[i], checkings1.get(chars1[i]) + 1);
            } else {
                checkings1.put(chars1[i], 1);
            }
        }
        for (int i = 0; i < chars2.length; i++) {
            if (checkings2.containsKey(chars2[i])) {
                checkings2.put(chars2[i], checkings2.get(chars1[i]) + 1);
            } else {
                checkings2.put((chars2[i]), 1);
            }
        }
        return checkings1.equals(checkings2);
    }
}