package com.mingguo.avarua.casual.account.test.common.mess.demo;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mingguo.wu on 2015/11/18.
 */
public class MainTest {

    //使用一个cache 缓存以每个节点为根节点的rob方法返回值，减少计算量
    Map<TreeNode, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("请正确输入参数！！！");
            System.exit(0);
        }
        int day;
        day = Integer.parseInt(args[0]);
        System.out.println("The day is :  " + day + "!!!");
    }

    @Test
    public void test01() { //expect "bd1dadsa"
        char[] ss = "aaaabccccccd1dadsadddd".toCharArray();
        char[] result = new char[ss.length];
        int index = 0;
        for(int i=0, j=1; i<ss.length-1; ) {
            if(ss[j] == ss[i]) {
                i = j + 1;
                j = j + 2;
                continue;
            } else {
                result[index++] = ss[i++];
                j=i+1;
            }
        }
        System.out.println(result);
    }

    @Test
    public void test02() {// expect "abcd1dadsad"
        char[] ss = "aaaabccccccd1dadsadddd".toCharArray();
        char[] result = new char[100];
        int index = 0;
        for(int i=0, j=1; i<ss.length-1; ) {
            if(j == ss.length-1) {
                result[index++] = ss[i++];
                j++;
                break;
            }
            if(ss[j] == ss[i]) {
                i = j;
                ++j;
            } else {
                result[index++] = ss[i++];
                j=i+1;
            }
        }
        System.out.println(result);
    }

    @Test
    public void test03() {

        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        right.left = left;
        root.right = right;
        TreeNode leftRight = new TreeNode(3);
        TreeNode rightRight = new TreeNode(1);
        left.right = leftRight;
        right.right = rightRight;

        System.out.println(rob(root));
        for(TreeNode node : cache.keySet()) {
            System.out.println("node: " + node.val + ", rob:" + cache.get(node));
        }



    }


    public int rob(TreeNode root) {
        //如果当前节点为空 直接返回0
        if(null == root){
            return 0;
        }
        //首先查看缓存中有没有这个节点的rob方法返回值
        if(null != cache.get(root)){
            return cache.get(root);
        }
        //计算当前节点左孩子的rob方法返回值
        int maxLeft = rob(root.left);
        //计算当前节点右孩子的rob方法返回值
        int maxRight = rob(root.right);
        int maxLeftLeft = 0;
        int maxLeftRight = 0;
        //如果当前节点有左孩子
        if(null != root.left){
            //计算其左孩子的左孩子的rob值
            maxLeftLeft = rob(root.left.left);
            //计算其左孩子的右孩子的rob值
            maxLeftRight = rob(root.left.right);
        }
        //ddddddd
        int maxRightLeft = 0;
        int maxRightRight = 0;
        //如果当前节点有右孩子
        if(null != root.right){
            //计算其右孩子的左孩子的rob值
            maxRightLeft = rob(root.right.left);
            //计算其右孩子的右孩子的rob值
            maxRightRight = rob(root.right.right);
        }
        //不偷当前节点能偷到的财物的最大值
        int notIncludeCurrentNodeMax = maxLeft + maxRight;
        //偷当前节点能偷到的财物的最大值
        int includeCurrentNodeMax = maxLeftLeft + maxLeftRight + maxRightLeft + maxRightRight + root.val;
        //以其中的较大值作为当前节点的rob方法返回值
        int res = notIncludeCurrentNodeMax > includeCurrentNodeMax ? notIncludeCurrentNodeMax : includeCurrentNodeMax;
        //缓存当前节点的rob方法返回值
        cache.put(root, res);
        return res;
    }

    @Data
    class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int x) {
            this.val = x;
        }
    }

}
