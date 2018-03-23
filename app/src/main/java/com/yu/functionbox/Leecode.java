package com.yu.functionbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yuw on 2018-03-08.
 * description
 */

public class Leecode {
    /**
     * 向右旋转
     **/
    public void left() {
        int num[] = {1, 2, 3, 4, 5, 6, 7};
        //6,7,1,2,3,4,5
        int k = 3;
        reserve(num, 0, num.length - 1);
        reserve(num, 0, k - 1);
        reserve(num, k - 1, num.length - 1);
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

    /**
     * Contains Duplicate
     **/

    public boolean containsDuplicate(int[] nums) {
        Set set = new HashSet<Integer>();
        for (int i : nums) {
            if (!set.contains(i)) {//不包含就加入 包含就返回true
                set.add(i);
            } else {
                return true;
            }

        }
        return false;
    }

    /**
     * Single Number
     **/
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) res ^= num;
        return res;
    }

    /**
     * Intersection of Two Arrays II 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2]  返回 [2, 2]
     **/
    public int[] intersect(int[] nums1, int[] nums2) {
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
        return res;
    }

    /**
     * Plus One
     **/
    public int[] plusOne(int[] digits) {
        add(digits, digits.length - 1);
        return digits;
    }

    public void add(int[] digits, int i) {
        if (i == 0) {
            if (digits[i] + 1 == 10) {
                int[] newDigits = new int[digits.length + 1];
                newDigits[0] = 1;
                digits[0] = 0;
                for (int j = 0; j < digits.length; j++) {
                    newDigits[j + 1] = digits[j];
                }
                digits = newDigits;
                System.out.print(Arrays.toString(digits) + "return");
            } else {
                digits[i] = digits[i] + 1;
            }
        } else {
            if (digits[i] + 1 == 10) {
                digits[i] = 0;
                add(digits, i - 1);
            } else {
                digits[i] = digits[i] + 1;
            }
        }

    }
    /**Move Zeroes**/
    public void moveZeroes(int[] nums) {
        int count = 0;
        for(int zero:nums){
            if(zero ==0){
                count++;
            }
        }
        for(int i =0;i<count;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[j] == 0){
                    //change
                    for(int k = j;k<nums.length-1;j++){
                        nums[k] = nums[k+1];
                    }
                    nums[nums.length-1]=0;
                    break;
                }
            }
        }
    }
    /**rotate**/
    public void rotate(int[][] matrix) {
        int [] x1= {1,2,3};
        int [] x2= {4,5,6};
        int [] x3= {7,8,9};
        matrix[0]=x1;
        matrix[1]=x2;
        matrix[2]=x3;
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length ; i++){
            System.out.print(Arrays.toString(newMatrix[i]));
            for(int j = 0; j < matrix[0].length ; j++){
                newMatrix[i][j] = matrix[j][matrix.length - i];
            }
        }
        matrix = newMatrix;

    }

}
