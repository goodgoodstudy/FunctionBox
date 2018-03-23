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
        for(int zero:nums){
            if(zero == 0){
                count++;
            }
        }
        for(int i =0;i<count;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[j] == 0){
                    //change
                    System.arraycopy(nums, j + 1, nums, j, nums.length - 1 - j);
                    nums[nums.length-1]=0;
                    break;
                }
            }
        }
        System.out.print(Arrays.toString(nums));
    }

    @Test
    public void intersect() throws Exception {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List list = new ArrayList<>();
        for(int i = 0,j=0;i<nums1.length && j<nums2.length;){
            if(nums1[i]==nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }else{
                i++;
            }
        }
        int[] res = new int[list.size()];
        for(int k=0;k<list.size();k++){
            res[k] = (int) list.get(k);
        }
        System.out.print(Arrays.toString(res));
    }

    @Test
    public void left() throws Exception {
        int num[] = {1,2,3,4,5,6,7};
        //6,7,1,2,3,4,5
        int k=3;
        reserve(num,0,num.length-1);
        System.out.print(Arrays.toString(num));
        reserve(num,0,k-1);
        System.out.print(Arrays.toString(num));
        reserve(num,k,num.length-1);
        System.out.print(Arrays.toString(num));
    }


    public void reserve(int[] nums,int start,int end){
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void plusOne() throws Exception {
        int[] digits = {9,9,9};
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]<9){
                digits[i] +=1;
                break;
            }else{
                digits[i] =0;
            }
        }
        if(digits[0]==0){
            int[] newDigits = new int[digits.length+1];
            newDigits[0] = 1;
            for (int j=0;j<digits.length;j++){
                newDigits[j+1] = digits[j];
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
        int[] nums = {3,2,4};
        int target=6;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[i] +nums[j] == target && i!=j ){
                    int[] res = new int[2];
                    res[0]=i;
                    res[1]=j;
                    System.out.print(Arrays.toString(res));
                }
            }
        }
    }

    /**
     *  Valid Sudoku
     * @param board
     * @return
     */
    public void isValidSudoku(char[][] board) {
        isValid(board);
    }

    private boolean isValid(char[][] board){
        for(int col=0;col<board[0].length;col++){
            Set set = new HashSet<Character>();
            for(int row=0;row<board.length;row++){
                if(board[col][row] =='.'){
                    continue;
                }
                if(!set.contains(board[col][row])){
                    set.add(board[col][row]);
                }else{
                    return false;
                }
            }
        }

        for(int row=0;row<board.length;row++){
            Set set = new HashSet<Character>();
            for(int col=0;col<board[row].length;col++){
                if(board[col][row] =='.'){
                    continue;
                }
                if(!set.contains(board[col][row])){
                    set.add(board[col][row]);
                }else{
                    return false;
                }
            }
        }

        for(int i=0;i<3;i++){
            Set set = new HashSet<Character>();
            for(int row=i*3;row<i*3+3;row++){
                for(int col=0;col<3;col++){
                    if(board[col][row] =='.'){
                        continue;
                    }
                    if(!set.contains(board[col][row])){
                        set.add(board[col][row]);
                    }else{
                        return false;
                    }
                }
            }
            set = new HashSet<Character>();
            for(int row=i*3;row<i*3+3;row++){
                for(int col=3;col<6;col++){
                    if(board[col][row] =='.'){
                        continue;
                    }
                    if(!set.contains(board[col][row])){
                        set.add(board[col][row]);
                    }else{
                        return false;
                    }
                }
            }
            set = new HashSet<Character>();
            for(int row=i*3;row<i*3+3;row++){
                for(int col=6;col<9;col++){
                    if(board[col][row] =='.'){
                        continue;
                    }
                    if(!set.contains(board[col][row])){
                        set.add(board[col][row]);
                    }else{
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
        int [] x1= {1,2,3};
        int [] x2= {4,5,6};
        int [] x3= {7,8,9};
        matrix[0]=x1;
        matrix[1]=x2;
        matrix[2]=x3;

        int n = matrix.length;
        for(int i = 0; i < n/2; ++i)
            for(int j = i; j < n-1-i; ++j){
                int t = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = t;
            }

        System.out.print(Arrays.toString(matrix[2]));
    }

    @Test
    public void getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(System.currentTimeMillis()));
    }
}