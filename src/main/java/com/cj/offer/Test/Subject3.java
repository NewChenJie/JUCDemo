package com.cj.offer.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author : chenjie
 * @date : 2019-06-20 10:42
 * @describe :输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class Subject3 {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        System.out.println(printListFromTailToHeadOne(one,new ArrayList<>()));
    }

    /** 采用栈的特性 **/
    public static ArrayList<Integer> printListFromTailToHeadTwo(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
    /** 递归 **/
    public static ArrayList<Integer> printListFromTailToHeadOne(ListNode listNode,ArrayList<Integer> arrayList) {
        if (listNode != null) {
            printListFromTailToHeadOne(listNode.next,arrayList);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        public void setNext(ListNode next) {
            this.next = next;
        }

        ListNode(int val) {
            this.val = val;
        }
    }
}