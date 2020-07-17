package org.elasticsearch.index.analysis;

import rocks.veer66.Wordcut;

import java.io.IOException;
import java.net.URL;

public class Veer66Wordcut {

    private static final URL url = Veer66Wordcut.class.getResource("/dictionary.txt");
    public static Wordcut wordcut;

    static {
        try {
            wordcut = Wordcut.fromDixUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
