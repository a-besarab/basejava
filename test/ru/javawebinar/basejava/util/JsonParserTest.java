package ru.javawebinar.basejava.util;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.TextSection;

import static org.junit.Assert.*;
import static ru.javawebinar.basejava.storage.AbstractStorageTest.RESUME_1;

public class JsonParserTest {

    @Test
    public void testResume() {
        String json = JsonParser.writer(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void writer() {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.writer(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}