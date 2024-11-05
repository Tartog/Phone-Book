package com.example.notebookservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "addPhoneNumberServlet", urlPatterns = "/add-phone")
public class AddPhoneNumber extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        if(!name.equals("") && !phone.equals("")){
            try {
                List<String> lines = Files.readAllLines(Paths.get(NoteBook.filePath));
                int oldSize = lines.size();
                for(int i = 0;i < lines.size();i++){
                    if(lines.get(i).equals(name)){
                        lines.add(i + 1, phone);
                        break;
                    }
                }
                int newSize = lines.size();
                if (oldSize == newSize) {
                    lines.add(name);
                    lines.add(phone);
                }
                Files.write(Paths.get(NoteBook.filePath), lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/main-page");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<form method = 'POST'> " +
                        "<label><p>Input user name -------- <input type=\"text\" name=\"name\" /></p></label>" +
                        "<label><p>Input phone number --- <input type=\"text\" name=\"phone\" /></p></label>" +
                        "<p><input type=\"submit\" value=\"Send data\"></p>" +
                    "</form>");
        out.println("</body></html>");
    }
}
