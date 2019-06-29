package com.cj.offer.Test;

/**
 * @author : chenjie
 * @date : 2019-06-20 10:20
 * @describe :题目描述
 *请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Subject2 {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("We Are Happy");
        System.out.println(replaceSpace(str));
    }
    public static String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ", "%20");
    }
    public static String replaceSpaceTwo(StringBuffer str) {
        StringBuilder newStr = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            }else{
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}
