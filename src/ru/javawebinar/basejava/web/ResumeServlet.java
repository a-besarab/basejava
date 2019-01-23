package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ResumeServlet extends HttpServlet {
    private Storage storage = new SqlStorage("", "", "");
    private List<Resume> list = Objects.requireNonNull(storage).getAllSorted();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");

        response.getWriter().write(
                "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "    <title>Resume</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table border=\"1\">\n" +
                        "    <tr>\n" +
                        "        <td>\n" +
                        "            Uuid\n" +
                        "        </td>\n" +
                        "        <td>\n" +
                        "            Full Name\n" +
                        "        </td>\n" +
                        "    </tr>\n");
        for (Resume resume : list) {
            response.getWriter().write("    <tr>\n" +
                    "        <td>\n" +
                    resume.getUuid() +
                    "        </td>\n" +
                    "        <td>\n" +
                    resume.getFullName() +
                    "        </td>\n" +
                    "    </tr>\n");
        }
        response.getWriter().write("</table>\n" +
                "</body>\n" +
                "</html>");
    }
}