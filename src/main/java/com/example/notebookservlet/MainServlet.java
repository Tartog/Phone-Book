package com.example.notebookservlet;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "mainServlet", value = "/main-page")
public class MainServlet extends HttpServlet {
    private final NoteBook phoneBook = new NoteBook();

    public void init() {
        phoneBook.updateNoteBookData();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        phoneBook.updateNoteBookData();

        PrintWriter out = response.getWriter();
        out.println("<html><body>" +
                "<h1>List number</h1>" +
                "<p><a href=\"http://localhost:8081/add-phone\">Add new user and phone number</a></p>"+
                "<p><a href=\"http://localhost:8081/add-user\">Add new user</a></p>");


        for (String s : phoneBook.getBook()) {
            out.println("<p>" + s + "</p>");
        }

        out.println("</body></html>");
    }
}