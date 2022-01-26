package com.company;

public class passenger {
    static int id=1;
    String name;
    String berthpreference;
    int age;
    int passengerid;
    String alloted;
    int num=-1;
    public passenger(String name,int age,String berthpreference){
        this.name=name;
        this.age=age;
        this.berthpreference=berthpreference;
        this.passengerid=id++;
//        this.alloted=" ";
//        this.num=-1;
    }

}
