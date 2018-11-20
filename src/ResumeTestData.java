import model.*;

import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume testResume = new Resume("Ivanov Ivan");

        Map<ContactType, String> contact = new EnumMap<>(ContactType.class);

        contact.put(ContactType.TEL, "123456789");
        contact.put(ContactType.SKYPE, "SkYpe");
        contact.put(ContactType.EMAIL, "@mail");
        contact.put(ContactType.LINKEDIN, "linkId");
        contact.put(ContactType.GITHUB, "gita");
        contact.put(ContactType.STACKOVERFLOW, "StOver");
        contact.put(ContactType.HOMEPAGE, "Home");

        Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

        List<String> list = new ArrayList<>();
        list.add("S1");
        list.add("S2");
        list.add("S3");

        section.put(SectionType.OBJECTIVE, new TextSection("Тестовая секция для позиции"));
        section.put(SectionType.PERSONAL, new TextSection("Тестовая секция для Личных качеств"));
        section.put(SectionType.ACHIEVEMENT, new MarkSection(list));
        section.put(SectionType.QUALIFICATIONS, new MarkSection(list));
        section.put(SectionType.EXPERIENCE, new OrganizationSection("Name", "10.01.19", "10.02.20", "sys", "descr"));
        section.put(SectionType.EDUCATION, new OrganizationSection("Name2", "10.01.17", "10.02.28", "sy56s", "de56scr"));

        testResume.setContact(contact);
        testResume.setSection(section);

        System.out.println(testResume.getFullName());
        System.out.println(testResume.getContact());
        System.out.println(testResume.getSection());
    }
}
