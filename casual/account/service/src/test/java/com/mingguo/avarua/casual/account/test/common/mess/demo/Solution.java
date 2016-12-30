package com.mingguo.avarua.casual.account.test.common.mess.demo;

/**
 * Created by mingguo.wu on 2016/12/29.
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        // Write your code here
        if (head == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = p.next;
        while (q != null) {
            if (p == head) {
                if (p.val == val) {
                    head = q;
                    p = q;
                    q = q.next;
                } else if (q.val == val) {
                    p.next = q.next;
                    q = q.next;
                }
            } else {
                if (q.val == val) {
                    q = q.next;
                    p.next = q;
                } else {
                    p = q;
                    q = q.next;
                }
            }
        }
        if (p.val == val) {
            return p.next;
        }
        return head;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
