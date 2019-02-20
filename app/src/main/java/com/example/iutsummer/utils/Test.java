package com.example.iutsummer.utils;

public class Test {
    public static void main(String[] args) {
        String s = "INADULLAEV MAKHMUDJON";
        String surname = s.substring(0,s.indexOf(" "));
        String n = s.substring(s.indexOf(" ")+1);
        n=n.toLowerCase();
        char beg=n.charAt(0);
        System.out.println(beg+"."+surname.toLowerCase()+"@student.inha.uz");

    }
}
