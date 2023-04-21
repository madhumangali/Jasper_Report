package com.jasper_report.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommonService.class})
@ExtendWith(SpringExtension.class)
class CommonServiceTest {
    @Autowired
    private CommonService commonService;

    /**
     * Method under test: {@link CommonService#rename(String)}
     */
    @Test
    void testRename() {
        assertEquals("St", commonService.rename("St"));
        assertEquals(" ", commonService.rename("_"));
        assertEquals("", commonService.rename(""));
        assertTrue(commonService.rename(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link CommonService#rename(List)}
     */
    @Test
    void testRename2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("The given list is renamed");
        List<String> actualRenameResult = commonService.rename(stringList);
        assertEquals(1, actualRenameResult.size());
        assertEquals("The Given List Is Renamed", actualRenameResult.get(0));
    }

    /**
     * Method under test: {@link CommonService#rename(List)}
     */
    @Test
    void testRename3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("_");
        stringList.add("The given list is renamed");
        List<String> actualRenameResult = commonService.rename(stringList);
        assertEquals(2, actualRenameResult.size());
        assertEquals(" ", actualRenameResult.get(0));
        assertEquals("The Given List Is Renamed", actualRenameResult.get(1));
    }

    /**
     * Method under test: {@link CommonService#rename(List)}
     */
    @Test
    void testRename4() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("");
        List<String> actualRenameResult = commonService.rename(stringList);
        assertEquals(1, actualRenameResult.size());
        assertEquals("", actualRenameResult.get(0));
    }

    /**
     * Method under test: {@link CommonService#convertToDatabaseName(String)}
     */
    @Test
    void testConvertToDatabaseName() {
        assertEquals("st", commonService.convertToDatabaseName("St"));
    }
}

