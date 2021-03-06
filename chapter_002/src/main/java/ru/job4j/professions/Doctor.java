package ru.job4j.professions;

public class Doctor extends Profession {
    public Doctor(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Doctor";
    }

    public Diagnose getDiagnose(Patient patient) {
        return new Diagnose("");
    }

    public boolean cure(Patient patient) {
        return true;
    }
}