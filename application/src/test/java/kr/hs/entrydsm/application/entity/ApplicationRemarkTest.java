package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationRemarkTest {

    @Test
    public void applicationRemark() {
        assertEquals(ApplicationRemark.ONE_PARENT, "ONE_PARENT");
        assertEquals(ApplicationRemark.FROM_NORTH, "FROM_NORTH");
        assertEquals(ApplicationRemark.MULTICULTURAL, "MULTICULTURAL");
        assertEquals(ApplicationRemark.BASIC_LIVING, "BASIC_LIVING");
        assertEquals(ApplicationRemark.LOWEST_INCOME, "LOWEST_INCOME");
        assertEquals(ApplicationRemark.TEEN_HOUSEHOLDER, "TEEN_HOUSEHOLDER");
        assertEquals(ApplicationRemark.PRIVILEGED_ADMISSION, "PRIVILEGED_ADMISSION");
        assertEquals(ApplicationRemark.NATIONAL_MERIT, "NATIONAL_MERIT");
    }

}