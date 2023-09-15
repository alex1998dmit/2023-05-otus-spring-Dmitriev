package com.example.homework6.utils;

import java.io.PrintStream;

public class ConsoleWriterImpl implements ConsoleWriter {
    private final PrintStream out = System.out;

    @Override
    public void write(String message) {
        out.println(message);
    }
}
