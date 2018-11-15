import model.*;

import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume testResume = new Resume("Ivanov Ivan");

        Map<ContactType, String> contacts = new TreeMap<>();

        contacts.put(ContactType.TEL, "123456789");
        contacts.put(ContactType.SKYPE, "SkYpe");
        contacts.put(ContactType.EMAIL, "@mail");
        contacts.put(ContactType.LINKEDIN, "linkId");
        contacts.put(ContactType.GITHUB, "gita");
        contacts.put(ContactType.STACKOVERFLOW, "StOver");
        contacts.put(ContactType.HOMEPAGE, "Home");

        Map<SectionType, Sections> sections = new TreeMap<>();

        List<String> list = new ArrayList<>();
        list.add("S1");
        list.add("S2");
        list.add("S3");

        sections.put(SectionType.OBJECTIVE, new TextSections("Тестовая секция для позиции"));
        sections.put(SectionType.PERSONAL, new TextSections("Тестовая секция для Личных качеств"));
        sections.put(SectionType.ACHIEVEMENT, new MarkedSections(list));
        sections.put(SectionType.QUALIFICATIONS, new MarkedSections(list));
        sections.put(SectionType.EXPERIENCE, new OrganizationsSections("Name", "10.01.19", "10.02.20", "sys", "descr"));
        sections.put(SectionType.EDUCATION, new OrganizationsSections("Name2", "10.01.17", "10.02.28", "sy56s", "de56scr"));

        testResume.setContacts(contacts);
        testResume.setSections(sections);

        System.out.println(testResume.getFullName());
        System.out.println(testResume.getContacts());
        System.out.println(testResume.getSections());

//        for (ContactType type : ContactType.values()) {
//            PrintContact pr1 = new PrintContact(type);
//            System.out.println(type.getTitle());
//            pr1.printContact();
//        }
//
//        System.out.println("_____________________________________");
//
//        for (SectionType type : SectionType.values()) {
//            PrintSections pr1 = new PrintSections(type);
//            System.out.println(type.getTitle());
//            pr1.printSection();
//        }
//    }
//
//    public static class PrintContact {
//        ContactType contactType;
//
//        public PrintContact(ContactType contactType) {
//            this.contactType = contactType;
//        }
//
//        public void printContact() {
//            switch (contactType) {
//                case TEL:
//                    System.out.println("tellll");
//                    break;
//                case SKYPE:
//                    System.out.println("sk");
//                    break;
//                case EMAIL:
//                    System.out.println("ema");
//                    break;
//                case LINKEDIN:
//                    System.out.println("lin");
//                    break;
//                case GITHUB:
//                    System.out.println("git");
//                    break;
//                case STACKOVERFLOW:
//                    System.out.println("stac");
//                    break;
//                case HOMEPAGE:
//                    System.out.println("hom");
//                    break;
//            }
//        }
//    }
//
//    public static class PrintSections {
//        SectionType sectionType;
//
//        public PrintSections(SectionType sectionType) {
//            this.sectionType = sectionType;
//        }
//
//        public void printSection() {
//            switch (sectionType) {
//                case OBJECTIVE:
//                    System.out.println(new TextSections("Тестовая поиция").getText());
//                    break;
//                case PERSONAL:
//                    System.out.println(new TextSections("Тестовые Личные качества").getText());
//                    break;
//                case ACHIEVEMENT:
//                    List list = new ArrayList<String>();
//                    list.add("S1");
//                    list.add("S2");
//                    list.add("S3");
//                    System.out.println(new MarkedSections(list).getMarkedList());
//                    break;
//                case QUALIFICATIONS:
//                    List ql = new ArrayList<String>();
//                    ql.add("S1");
//                    ql.add("S2");
//                    ql.add("S3");
//                    System.out.println(new MarkedSections(ql).getMarkedList());
//                    break;
//                case EXPERIENCE:
//
//                    System.out.println(new OrganizationsSections("Name", "10.01.19", "10.02.20", "sys", "descr"));
//                    break;
//                case EDUCATION:
//                    System.out.println("OK");
//                    break;
//            }
//        }
    }
}
