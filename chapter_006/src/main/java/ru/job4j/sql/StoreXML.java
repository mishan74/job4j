package ru.job4j.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class StoreXML {
    private final File target;

    public StoreXML(File target) {
        this.target = target;
    }
    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLUsage.Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
           
                jaxbMarshaller.marshal(new XMLUsage.Entries(list), target);
                jaxbMarshaller.marshal(new XMLUsage.Entries(list), System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
