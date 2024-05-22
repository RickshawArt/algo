package Stack.java.thread.demo.printABC;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/12 0:03
 */
public class LengthOfLongestSubstring {

    public int execute(String s) {
        int left = 0, maxLength = 0;
        Map<Character, Integer> dictionary = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (dictionary.containsKey(current)) {
                left = Math.max(left, dictionary.get(current)) + 1;
            }
            dictionary.put(current, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        String s = "pwwkew";
        int execute = lengthOfLongestSubstring.execute(s);
        System.out.println("execute = " + execute);
    }

}
