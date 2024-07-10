package com.example.myapplication;

public class Persona {
    private String name;
    private String last_name;
    private int age;

    public Persona(String name, String last_name, int age){
        this.name = name;
        this.last_name = last_name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                '}';
    }
}
