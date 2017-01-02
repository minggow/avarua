package com.mingguo.avarua.casual.account.test.common.mess.lintcode;

import com.google.common.base.Strings;
import org.apache.commons.lang.text.StrBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by wumingguo on 2016/12/27.
 */
public class Solution001 {

    /**
     * @param s: The first string
     * @param t: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        // write your code here
        if (s == "" || t == "") return false;
        if (s.length() != t.length()) return false;

        int len = s.length();
        int[] c = new int[256];

        for (int i = 0; i < len; ++i) {
            ++c[(int) s.charAt(i)];
            --c[(int) t.charAt(i)];
        }

        for (int i = 0; i < 256; ++i) {
            if (c[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == target.length()) {
                    return i;
                }
                if (i + j == source.length()) {
                    return -1;
                }
                if (target.charAt(j) != source.charAt(i + j)) {
                    break;
                }

            }
        }
    }

    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        if(A == null || B == null) {
            return false;
        }

        int[] c = new int[256];
        int aLength = A.length();
        int bLength = B.length();
        if(aLength >= bLength) {
            for(int i=0; i<aLength; ++i) {
                ++c[A.charAt(i)];
                if(i<bLength) {
                    --c[B.charAt(i)];
                }
            }

            for(int i=0; i<256; ++i) {
                if(c[i] < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String reverseCharacter(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        Stack stack = new Stack();
        for(int i=0; i<s.length(); ++i) {
            char c = s.charAt(i);
            if(c != ' ') {
                stack.push(c);
            } else {
                sb.append(c);
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        Stack stack = new Stack();
        String[] ss = s.split("//s+");
        for(String word : ss) {
            stack.push(word);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        int[] a ={3,2,1,7,6,5,4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        for(int i=0; i<a.length-1; ++i) {
            for(int j=i+1; j<a.length; ++j) {
                if(a[i] > a[j]) {
                    a[i] = a[i] ^ a[j];
                    a[j] = a[i] ^ a[j];
                    a[i] = a[i] ^ a[j];
                }
            }
        }
    }


    @Test
    public void test01() {
        //1->2->6->3->4->5->6->null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        removeElements(head, 6);

        System.out.println(1111);
    }


    @Test
    public void test02() {
        System.out.println(fibonacci(5));
    }


    public int fibonacci(int n) {

        int[] a = new int[n+2];
        a[0] = 0;
        a[1] = 1;

        if(n>1) {
            for(int i=2; i<n; ++i) {
                a[i] = a[i-1] + a[i-2];
            }

        }
        return a[n-1];
    }


    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if(head == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = p.next;
        while(q!= null) {
            q = p.next;
            if(p == head) {
                if(p.val == val) {
                    head = q;
                    p = q;
                    q = q.next;
                } else if(q.val == val) {
                    p.next = q.next;
                    q = q.next;
                } else {
                    p = q;
                    q = q.next;
                }
            } else {
                if(q.val == val) {
                    q = q.next;
                    p.next = q;
                } else {
                    p = q;
                    q = q.next;
                }
            }
        }
        if(p.val == val) {
            return p.next;
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


}
