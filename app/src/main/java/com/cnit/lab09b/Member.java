package com.cnit.lab09b;

import java.util.ArrayList;

public class Member {
    private String name;
    private ArrayList<String> hobbies;
    private int no;
    private String id;
    private int pw;
    public Member(String name, int age, ArrayList<String> hobbies, int no, String id, int pw) {
        this.name = name;
        this.hobbies = hobbies;
        this.no = no;
        this.id = id;
        this.pw = pw;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> gethobbies() {
        return hobbies;
    }
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getPw() {
        return pw;
    }
    public void setPw(int pw) {
        this.pw = pw;
    }
}
