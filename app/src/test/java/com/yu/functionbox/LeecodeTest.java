package com.yu.functionbox;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yuw on 2018-03-08.
 * description
 */
public class LeecodeTest {
    @Test
    public void moveZeroes() throws Exception {
        int[] nums = {0, 1, 0, 3, 12};
        int count = 0;
        for (int zero : nums) {
            if (zero == 0) {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == 0) {
                    //change
                    System.arraycopy(nums, j + 1, nums, j, nums.length - 1 - j);
                    nums[nums.length - 1] = 0;
                    break;
                }
            }
        }
        System.out.print(Arrays.toString(nums));
    }

    @Test
    public void intersect() throws Exception {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = (int) list.get(k);
        }
        System.out.print(Arrays.toString(res));
    }

    @Test
    public void left() throws Exception {
        int num[] = {1, 2, 3, 4, 5, 6, 7};
        //6,7,1,2,3,4,5
        int k = 3;
        reserve(num, 0, num.length - 1);
        System.out.print(Arrays.toString(num));
        reserve(num, 0, k - 1);
        System.out.print(Arrays.toString(num));
        reserve(num, k, num.length - 1);
        System.out.print(Arrays.toString(num));
    }


    public void reserve(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void plusOne() throws Exception {
        int[] digits = {9, 9, 9};
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                break;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int j = 0; j < digits.length; j++) {
                newDigits[j + 1] = digits[j];
            }
            digits = newDigits;
        }
        System.out.print(Arrays.toString(digits));
    }

    //    public void add(int[] digits,int i){
//        if(i==0){
//            if(digits[i]+1 == 10){
//                int[] newDigits = new int[digits.length+1];
//                newDigits[0]=1;
//                digits[0]=0;
//                for(int j=0;j<digits.length;j++){
//                    newDigits[j+1] = digits[j];
//                }
//                digits = newDigits;
//                System.out.print(Arrays.toString(digits)+"return");
//            }else{
//                digits[i] = digits[i]+1;
//            }
//        }else{
//            if(digits[i]+1 == 10){
//                digits[i] = 0;
//                add(digits,i-1);
//            }else{
//                digits[i] = digits[i]+1;
//            }
//        }
//
//    }
    @Test
    public void twoSum() {
        int[] nums = {3, 2, 4};
        int target = 6;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    int[] res = new int[2];
                    res[0] = i;
                    res[1] = j;
                    System.out.print(Arrays.toString(res));
                }
            }
        }
    }

    /**
     * Valid Sudoku
     *
     * @param board
     * @return
     */
    public void isValidSudoku(char[][] board) {
        isValid(board);
    }

    private boolean isValid(char[][] board) {
        for (int col = 0; col < board[0].length; col++) {
            Set set = new HashSet<Character>();
            for (int row = 0; row < board.length; row++) {
                if (board[col][row] == '.') {
                    continue;
                }
                if (!set.contains(board[col][row])) {
                    set.add(board[col][row]);
                } else {
                    return false;
                }
            }
        }

        for (int row = 0; row < board.length; row++) {
            Set set = new HashSet<Character>();
            for (int col = 0; col < board[row].length; col++) {
                if (board[col][row] == '.') {
                    continue;
                }
                if (!set.contains(board[col][row])) {
                    set.add(board[col][row]);
                } else {
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            Set set = new HashSet<Character>();
            for (int row = i * 3; row < i * 3 + 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[col][row] == '.') {
                        continue;
                    }
                    if (!set.contains(board[col][row])) {
                        set.add(board[col][row]);
                    } else {
                        return false;
                    }
                }
            }
            set = new HashSet<Character>();
            for (int row = i * 3; row < i * 3 + 3; row++) {
                for (int col = 3; col < 6; col++) {
                    if (board[col][row] == '.') {
                        continue;
                    }
                    if (!set.contains(board[col][row])) {
                        set.add(board[col][row]);
                    } else {
                        return false;
                    }
                }
            }
            set = new HashSet<Character>();
            for (int row = i * 3; row < i * 3 + 3; row++) {
                for (int col = 6; col < 9; col++) {
                    if (board[col][row] == '.') {
                        continue;
                    }
                    if (!set.contains(board[col][row])) {
                        set.add(board[col][row]);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    @Test
    public void rotate() {
        int[][] matrix = new int[3][3];
        int[] x1 = {1, 2, 3};
        int[] x2 = {4, 5, 6};
        int[] x3 = {7, 8, 9};
        matrix[0] = x1;
        matrix[1] = x2;
        matrix[2] = x3;

        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i)
            for (int j = i; j < n - 1 - i; ++j) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = t;
            }

        System.out.print(Arrays.toString(matrix[2]));
    }

    @Test
    public void getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(System.currentTimeMillis()));
    }


    @Test
    public void reverseString() {
        String Str = "hello";
        char[] chars = Str.toCharArray();
        reserve(chars, 0, chars.length - 1);
        System.out.print(String.valueOf(chars));
    }

    public void reserve(char[] nums, int start, int end) {
        while (start < end) {
            char temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void reserveInt() {
        int ore = 1534236469;
        long num = ore;
        long res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            num = num / 10;
        }
        System.out.print("res " + res + "\n");
        if (res < Integer.MAX_VALUE && res > Integer.MIN_VALUE) {
            System.out.print((int) res);
        } else {
            System.out.print(0);
        }
    }

    @Test
    public void firstUniqChar() {
        String s = "loveleetcode";
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (isUniq(chars, i)) {
                System.out.print(i);
                break;
            }
        }
    }

    public boolean isUniq(char[] chars, int index) {
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chars[index]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void isAnagram() {
        String s = "anagram";
        String t = "nagaram";
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        if (chars1.length != chars2.length) {
            System.out.print("false");
        }
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                System.out.print("false");
            }
        }
        System.out.print("true");
    }


    @Test
    public void isPalindrome() {
        String s = "ab";
        if (("").equals(s) || s == null) {
            System.out.print("true");
        }
        char[] chars = s.toCharArray();
        List list = new ArrayList();
        for (char temp : chars) {
            if ('z' >= temp && temp >= 'a' || 'Z' >= temp && temp >= 'A' || temp >= '0' && temp <= '9') {
                list.add(temp);
                System.out.print(temp);
            }
        }
        for (int i = 0; i < list.size() / 2; i++) {
            String x = list.get(i).toString();
            String y = list.get(list.size() - i - 1).toString();
            if (!x.equalsIgnoreCase(y)) {
                System.out.print("false");
                break;
            }
        }
        System.out.print("true");
    }

    @Test
    public void myAtoi() {
        String str = " 234- feff";
        char[] chars = str.toCharArray();
        if (str.length() <= 0) {
            System.out.print("false");
        }
        int result = 0;
        int i = 0;
        int sign = 1;
        while (' ' == chars[i]) {
            if (' ' == chars[i]) {
                i++;
            }
        }

        if ('+' == chars[i]) {
            sign = 1;
        } else if ('-' == chars[i]) {
            sign = -1;
        }
        for (int j = i; j < chars.length; j++) {
            if ('0' <= chars[j] && '9' >= chars[j]) {
                result = result * 10 + chars[j] - '0';
                System.out.print("\n" + result);
                if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
                    System.out.print(sign < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
                }
            } else {
                break;
            }
        }
        result = result * sign;
        System.out.print(result);
    }

    @Test
    public void strStr() {
        String haystack = "api";
        String needle = "pi";
        if (needle.isEmpty() || haystack.equals(needle)) {
            System.out.print("\n" + "return true");
        }
        char[] chars1 = haystack.toCharArray();
        char[] chars2 = needle.toCharArray();
        if (chars1.length > 0 && chars2.length > 0) {
            for (int i = 0; i <= chars1.length - chars2.length; i++) {
                if (chars1[i] == chars2[0]) {
                    int j = 1;
                    while (j <= chars2.length) {
                        if (j == chars2.length) {
                            System.out.print("\n" + "return true");
                            break;
                        }
                        System.out.print("\n" + chars1[i + j] + "---" + chars2[j]);
                        if (chars1[i + j] == chars2[j]) {
                            j++;
                        } else {
                            break;
                        }
                    }

                }

            }
        }
        System.out.print("\n" + "return false");
    }

    @Test
    public void countAndSay() {
        int n = 5;
        String orin = "1";

        for (int i = 1; i < 6; i++) {
            char[] chars = orin.toCharArray();
            int count = 1;
            String str = "";
            for (int j = 0; j < chars.length; j++) {
                if (j != chars.length - 1 && chars.length > 1 && chars[j] == chars[j + 1]) {
                    count++;
                    System.out.print("\n count" + count);
                } else {
                    String temp = String.valueOf(count) + chars[j];
                    str = str + temp;
                    System.out.print("\n chars  " + chars[j]);
                    System.out.print("\n temp  " + temp);
                    System.out.print("\n str  " + str);
                    count = 1;
                }
                orin = str;
            }
            System.out.print("\n----------" + orin);
        }
    }

    @Test
    public void longestCommonPrefix() {
        String[] strs = {"c", "c"};
        char[] chars1 = strs[0].toCharArray();
        int n = 0;
        String res = "";
        while (n < chars1.length) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() > n) {
                    char[] temp = strs[i].toCharArray();
                    if (temp[n] != chars1[n]) {
                        System.out.print("break");
                        System.out.print(res);
                        break;
                    }
                }else{
                    System.out.print("break");
                    System.out.print(res);
                }
            }
            res += String.valueOf(chars1[n]);
            n++;
        }
        System.out.print(res);
    }



    public class ListNode{
        int val;
        ListNode next;

        public ListNode(int x){
            val=x;
        }
    }


    ListNode newHead;

    @Test
    public void initListNode() {
        ListNode head0 = new ListNode(0);
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        head0.next = head1;
        head1.next = head2;
        head2.next = head3;

        reverseList(head0);
    }

    public ListNode reverseList(ListNode head) {
        reverse(head);
        return newHead;
    }

    private ListNode reverse(ListNode n){
        if( n == null || n.next == null){
            newHead = n;
        } else {
            System.out.print(n.val+"\n");
            ListNode prev = reverseList(n.next);
            System.out.print("prev.next = "+n.val+"\n");
            prev.next = n;
        }
        System.out.print("return n"+n.val+"\n");
        return n;
    }

}