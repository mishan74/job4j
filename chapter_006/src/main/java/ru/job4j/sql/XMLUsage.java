package ru.job4j.sql;

import javax.xml.bind.annotation.*;
import java.util.List;

public class XMLUsage {


    @XmlRootElement
    public static class Entries {

        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return values;
        }

        @XmlElement(name = "entry")
        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

   // @XmlRootElement
   // public static class Entry {
   //     private int value;
//
   //     public Entry() {}
   //     public Entry(int value) {
   //         this.value = value;
   //     }
//
   //     public int getValue() {
   //         return value;
   //     }
//
   //     public void setValue(int value) {
   //         this.value = value;
   //     }
   // }
}