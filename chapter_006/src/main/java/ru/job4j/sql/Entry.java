package ru.job4j.sql;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */



public class Entry {


    private  int field;

    public Entry() {

    }

     public void setField(int field) {
        this.field = field;
     }

    public int getField() {
        return field;
    }

    public Entry(int field) {
        this.field = field;
    }
}
