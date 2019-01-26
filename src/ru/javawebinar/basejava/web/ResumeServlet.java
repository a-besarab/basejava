package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("resumes", storage.getAllSorted());
        request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);

//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        Writer writer = response.getWriter();
//        writer.write(
//                "<!DOCTYPE html>\n" +
//                        "<html lang=\"en\">\n" +
//                        "<head>\n" +
//                        "    <meta charset=\"UTF-8\">\n" +
//                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
//                        "    <title>Resume</title>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "<table border=\"1\">\n" +
//                        "    <tr>\n" +
//                        "        <td>\n" +
//                        "            Uuid\n" +
//                        "        </td>\n" +
//                        "        <td>\n" +
//                        "            Full Name\n" +
//                        "        </td>\n" +
//                        "    </tr>\n");
//        for (Resume resume : storage.getAllSorted()) {
//            writer.write("    <tr>\n" +
//                    "        <td>\n" +
//                    resume.getUuid() +
//                    "        </td>\n" +
//                    "        <td>\n" +
//                    resume.getFullName() +
//                    "        </td>\n" +
//                    "    </tr>\n");
//        }
//        writer.write("</table>\n" +
//                        "</body>\n" +
//                        "</html>");
    }
}