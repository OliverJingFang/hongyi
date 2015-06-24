package com.cn;

public class TestJava {
    
    String str = new String("good");
    
    char[] ch = { 'a', 'b', 'c' };
    
    public static void main(String args[]) {
        TestJava java = new TestJava();
        java.change(java.str, java.ch);
        System.out.println(java.str + "and");
    }
    
    public void change(String str, char ch[]) {
        str = "test oK";
        ch[0] = 'g';
    }
}
