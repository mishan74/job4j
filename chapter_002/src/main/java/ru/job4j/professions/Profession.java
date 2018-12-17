package ru.job4j.professions;

public abstract class Profession {
    private String name;
    private Profession profession;


    public Profession(String name) {
        this.name = name;
        this.profession = this;
    }

    public String getName() {
        return this.name;
    }

    public String getProfession() {
        return String.valueOf(this.profession);
    }
}
