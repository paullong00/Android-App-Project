package com.example.autumn;

public class Player {
    private Integer id;
    private String name;
    private String age;
    private String appearances;
    private String goals;
    private String form;
    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player(String name, String age, String appearances, String goals, String form, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.appearances = appearances;
        this.goals = goals;
        this.form = form;
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAppearances(String appearances) {
        this.appearances = appearances;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public void setForm(String form) {
        this.form = form;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAppearances() {
        return appearances;
    }

    public String getGoals() {
        return goals;
    }

    public String getForm() {
        return form;
    }
    public String getPosition() {
        return position;
    }

}
