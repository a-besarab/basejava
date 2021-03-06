package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {

        fillResume("testUuid", "Ivanov Ivan");
    }

    public static Resume fillResume(String uuid, String name) {

        Resume testResume = new Resume(uuid, name);

        testResume.setContact(ContactType.TEL, "123456789");
        testResume.setContact(ContactType.SKYPE, "SkYpe");
        testResume.setContact(ContactType.EMAIL, "@mail");
        testResume.setContact(ContactType.LINKEDIN, "linkId");
        testResume.setContact(ContactType.GITHUB, "gita");
        testResume.setContact(ContactType.STACKOVERFLOW, "StOver");
        testResume.setContact(ContactType.HOMEPAGE, "Home");
        testResume.setSection(SectionType.OBJECTIVE, new TextSection("Objective1111"));
        testResume.setSection(SectionType.PERSONAL, new TextSection("Personal1111"));
        testResume.setSection(SectionType.ACHIEVEMENT, new MarkSection("Ach1", "Ach2", "Ach3"));
        testResume.setSection(SectionType.QUALIFICATIONS, new MarkSection("Qual1", "Qual2", "Qual3"));
        testResume.setSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("OOO", "url1",
                        new Organization.Content(1999, Month.AUGUST, 2002, Month.AUGUST, "position1", "Description1")),
                new Organization("ZAO", "url2",
                        new Organization.Content(2015, Month.OCTOBER, 2018, Month.DECEMBER, "position3", "Description3"))));
        testResume.setSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", "url",
                        new Organization.Content(1994, Month.SEPTEMBER, 1999, Month.JUNE, "position", "Description")),
                new Organization("ZAO", null,
                        new Organization.Content(1984, Month.SEPTEMBER, 1994, Month.JUNE, "position", null),
                        new Organization.Content(2002, Month.SEPTEMBER, 2015, Month.OCTOBER, "position2", "Description2"))));
        return testResume;
    }
}
