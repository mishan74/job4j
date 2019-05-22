package ru.job4j.sql;

import ru.job4j.socket.Config;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class StartApp {
    public static void main(String[] args) throws IOException, TransformerException {
        final String FS = File.separator;
        StringJoiner sj = new StringJoiner(FS);
        sj
                .add("chapter_006")
                .add("src")
                .add("main")
                .add("resources");

        Config config = new Config(sj.toString().concat(FS).concat("app.properties"));
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(5);
        List<Entry> list = storeSQL.load();
        for (Entry entry : list) {
            System.out.println(entry.getField());
        }
        File firstXML = new File("test.xml");
        File resultXML = new File("result.xml");
        File scheme = new File(sj.toString().concat(FS).concat("scheme.xsl"));
        FileOutputStream fs = new FileOutputStream(firstXML);
        FileOutputStream fs1 = new FileOutputStream(resultXML);
      //  FileOutputStream fs2 = new FileOutputStream(file2);
        StoreXML storeXML = new StoreXML(firstXML);
        storeXML.save(list);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(firstXML, resultXML, scheme);


    }
}
