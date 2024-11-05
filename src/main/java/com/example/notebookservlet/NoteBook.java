package com.example.notebookservlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class NoteBook {
    public static final String filePath = "C:\\Program Files\\Apache Software Foundation\\" +
            "Tomcat 9.0\\bin\\notebook.txt";
    private final LinkedList<String> book = new LinkedList<>();
    public synchronized void fileCheck(){
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void updateNoteBookData(){
        fileCheck();
        try {
            book.clear();
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                book.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized LinkedList<String> getBook(){
        return book;
    }
}
