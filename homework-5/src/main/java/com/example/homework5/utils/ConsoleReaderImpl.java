package com.example.homework5.utils;

import lombok.NoArgsConstructor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

@NoArgsConstructor
public class ConsoleReaderImpl implements ConsoleReader {
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private final PrintStream out = System.out;

    public ConsoleReaderImpl(BufferedReader in) {
        this.in = in;
    }

    @Override
    public String read() {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    @Override
    public String read(String message) {
        out.println(message);
        return this.read();
    }

    @Override
    public Long readLong() {
        return Long.parseLong(read());
    }

    @Override
    public Long readLong(String message) {
        out.println(message);
        return readLong();
    }
}
