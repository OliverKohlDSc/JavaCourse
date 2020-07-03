package com.company;

public class Person {
    private String vorname;
    private String nachname;
    private PersonenJob personenJob;

    public Person(String vorname, String nachname, PersonenJob personenJob) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.personenJob = personenJob;
    }

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    @Override
    public String toString() {
        return vorname + " " + nachname + " ist " + this.personenJob.getJob();
    }
}
