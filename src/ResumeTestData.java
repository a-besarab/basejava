import model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume testResume = new Resume("Ivanov Ivan");
        System.out.println(testResume.getFullName());

        System.out.println(" ");
        System.out.println(" ");

        for (ContactType type : ContactType.values()) {
            PrintContact pr1 = new PrintContact(type);
            System.out.println(type.getTitle());
            pr1.printContact();
        }

        System.out.println("_____________________________________");
        System.out.println(" ");


        for (SectionType type : SectionType.values()) {
            PrintSections pr1 = new PrintSections(type);
            System.out.println(type.getTitle());
            pr1.printSection();
        }
    }

    public static class PrintContact {
        ContactType contactType;

        public PrintContact(ContactType contactType) {
            this.contactType = contactType;
        }

        public void printContact() {
            switch (contactType) {
                case TEL:
                    System.out.println("tellll");
                    break;
                case SKYPE:
                    System.out.println("sk");
                    break;
                case EMAIL:
                    System.out.println("ema");
                    break;
                case LINKEDIN:
                    System.out.println("lin");
                    break;
                case GITHUB:
                    System.out.println("git");
                    break;
                case STACKOVERFLOW:
                    System.out.println("stac");
                    break;
                case HOMEPAGE:
                    System.out.println("hom");
                    break;
            }
        }
    }

    public static class PrintSections {
        SectionType sectionType;

        public PrintSections(SectionType sectionType) {
            this.sectionType = sectionType;
        }

        public void printSection() {
            switch (sectionType) {
                case OBJECTIVE:
                    System.out.println(new TextSections("Тестовая поиция").getText());
                    break;
                case PERSONAL:
                    System.out.println(new TextSections("Тестовые Личные качества").getText());
                    break;
                case ACHIEVEMENT:
                    List list = new ArrayList<String>();
                    list.add("S1");
                    list.add("S2");
                    list.add("S3");
                    System.out.println(new MarkedSections(list).getMarkedList());
                    break;
                case QUALIFICATIONS:
                    List ql = new ArrayList<String>();
                    ql.add("S1");
                    ql.add("S2");
                    ql.add("S3");
                    System.out.println(new MarkedSections(ql).getMarkedList());
                    break;
                case EXPERIENCE:
                    System.out.println("OK");
                    break;
                case EDUCATION:
                    System.out.println("OK");
                    break;
            }
        }
    }
}
