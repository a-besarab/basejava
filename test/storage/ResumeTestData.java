package storage;

import model.*;
import util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        fillResume();
    }

    protected static void fillResume() {
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

        List<String> markedList = new ArrayList<>();
        markedList.add("S1");
        markedList.add("S2");
        markedList.add("S3");

        List<Organization> organizationList = new ArrayList<>();
        Organization.Content cont_1 = new Organization.Content(DateUtil.of(1988, Month.JANUARY), DateUtil.of(1989, Month.AUGUST), "sysfdghdf", "descrdfgh");
        Organization.Content cont_2 = new Organization.Content(DateUtil.of(1986, Month.JANUARY), DateUtil.of(1989, Month.AUGUST), "sysfdghdf", "descrdfgh");
        organizationList.add(new Organization("ZAO", "url", cont_1, cont_2));
        organizationList.add(new Organization("PAO", "url1111", cont_2));

        section.put(SectionType.OBJECTIVE, new TextSection("Тестовая секция для позиции"));
        section.put(SectionType.PERSONAL, new TextSection("Тестовая секция для Личных качеств"));
        section.put(SectionType.ACHIEVEMENT, new MarkSection(markedList));
        section.put(SectionType.QUALIFICATIONS, new MarkSection(markedList));
        section.put(SectionType.EXPERIENCE, new OrganizationSection(organizationList));
        section.put(SectionType.EDUCATION, new OrganizationSection(organizationList));

        testResume.setContact(contact);
        testResume.setSection(section);

        System.out.println(testResume.getFullName());
        for (ContactType type : ContactType.values()) {
            System.out.println(testResume.getContact(type));
        }
        for (SectionType type : SectionType.values()) {
            System.out.println(testResume.getSection(type));
        }
    }
}
