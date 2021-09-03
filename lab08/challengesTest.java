import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class challengesTest {

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

    @Test
    public void testsmissing() {
        System.out.println("Running test before resize to ensure proper operation.");
        int[] testing = new int[8];
        int[] testing2 = new int[8];

        testing[0] = 0;
        testing[1] = 1;
        testing[2] = 2;
        testing[3] = 3;
        testing[4] = 5;
        testing[5] = 6;
        testing[6] = 7;
        testing[7] = 8;

        testing2[0] = 8;
        testing2[1] = 7;
        testing2[2] = 6;
        testing2[3] = 4;
        testing2[4] = 2;
        testing2[5] = 3;
        testing2[6] = 1;
        testing2[7] = 0;


        assertEquals(4, missingNumber(testing));
        assertEquals(5, missingNumber(testing2));

    }

    public static boolean isPermutation(String s1, String s2) {
        Map<Character, Integer> checkings1 = new HashMap<>();
        Map<Character, Integer> checkings2 = new HashMap<>();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (checkings1.containsKey(chars1[i])) {
                checkings1.put(chars1[i], checkings1.get(chars1[i]) + 1);
            }
            if (checkings2.containsKey(chars2[i])) {
                checkings2.put(chars2[i], checkings2.get(chars1[i]) + 1);
            }
            if (!checkings2.containsKey(chars2[i]) && !checkings1.containsKey(chars1[i])) {
                checkings1.put(chars1[i], 1);
                checkings2.put((chars2[i]), 1);
            }
        }
        return checkings1.equals(checkings2);
    }


    @Test
    public void testspermutation() {
        assertTrue(isPermutation("hello", "olleh"));
    }


}