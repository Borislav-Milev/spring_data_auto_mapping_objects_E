package map.app.util;

import map.app.util.contract.Reader;

import java.io.BufferedReader;
import java.io.IOException;

public class ReaderImpl implements Reader {

    private final BufferedReader reader;

    public ReaderImpl(BufferedReader reader) {
        this.reader = reader;
    }

    public String read(){
        String line = null;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
