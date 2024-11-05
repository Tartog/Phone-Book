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

@WebServlet(name = "addUser", urlPatterns = "/add-user")
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if(!name.equals("")){
            try {
                List<String> lines = Files.readAllLines(Paths.get(NoteBook.filePath));
                for(int i = 0;i < lines.size();i++){
                    if(lines.get(i).equals(name)){
                        break;
                    }
                    else if(i + 1 == lines.size()){
                        lines.add(name);
                    }
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
                "<label><p>Input phone number --- <input type=\"text\" name=\"name\" /></p></label>" +
                "<p><input type=\"submit\" value=\"Send data\"></p>" +
                "</form>");
        out.println("</body></html>");
    }
}
