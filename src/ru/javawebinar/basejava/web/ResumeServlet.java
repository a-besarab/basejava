package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
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
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        resume.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.setContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.setSection(type, new MarkSection(value.trim().split("\\n")));
                        break;
                }
            } else {
                resume.getSections().remove(type);
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")).forward(request, response);


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