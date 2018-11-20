import model.*;
import util.DateUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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

        List<Organization> orgList = new ArrayList<>();
        orgList.add(new Organization("OOO ogogo", null, DateUtil.of(1980, Month.JANUARY), DateUtil.of(1987, Month.AUGUST), "sys", "descr"));
        orgList.add(new Organization("ZAO", "url", DateUtil.of(1988, Month.JANUARY), DateUtil.of(1989, Month.AUGUST), "sysfdghdf", "descrdfgh"));


        section.put(SectionType.OBJECTIVE, new TextSection("Тестовая секция для позиции"));
        section.put(SectionType.PERSONAL, new TextSection("Тестовая секция для Личных качеств"));
        section.put(SectionType.ACHIEVEMENT, new MarkSection(list));
        section.put(SectionType.QUALIFICATIONS, new MarkSection(list));
        section.put(SectionType.EXPERIENCE, new OrganizationSection(orgList));
        section.put(SectionType.EDUCATION, new OrganizationSection(orgList));
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
