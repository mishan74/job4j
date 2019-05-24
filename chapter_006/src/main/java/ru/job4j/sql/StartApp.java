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
    final static String FS = File.separator;
    private final StoreSQL storeSQL;


    private final File firstXML;
    private final File scheme;
    private final File resultXML;

    public StartApp(String config, String output, String input, String scheme) {
        this.storeSQL = new StoreSQL(new Config(config));
        this.firstXML = new File(input);
        this.resultXML = new File(output);
        this.scheme = new File(scheme);

    }

        public void start() throws IOException, TransformerException {
            storeSQL.init();
            storeSQL.generate(5);

            List<Entry> list = storeSQL.load();
            for (Entry entry : list) {
                System.out.println(entry.getField());
            }

            FileOutputStream fs = new FileOutputStream(firstXML);
            FileOutputStream fs1 = new FileOutputStream(resultXML);

            StoreXML storeXML = new StoreXML(firstXML);
            storeXML.save(list);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            convertXSQT.convert(firstXML, resultXML, scheme);
        }

    public static void main(String[] args) throws IOException, TransformerException {
        StringJoiner sj = new StringJoiner(FS);
        sj .add("chapter_006")
                .add("src")
                .add("main")
                .add("resources")
                .add(FS);

        new StartApp(
            sj.toString().concat("app.properties"),
            "test.xml",
            "result.xml",
            sj.toString().concat("scheme.xsl")
            ).start();
    }
}
