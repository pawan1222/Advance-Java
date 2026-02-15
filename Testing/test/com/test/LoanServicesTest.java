package com.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanServicesTest {

	LoanServices service;

    @BeforeEach
    void setUp() {
        service = new LoanServices();
    }

    @Test
    void testValidEligibility() {
        assertTrue(service.isEligible(25, 30000));
    }
    
    @Test
    void testInvalidAge() {
        assertFalse(service.isEligible(18, 30000));
    }

    @Test
    void testInvalidSalary() {
        assertFalse(service.isEligible(30, 20000));
    }

    @Test
    void testValidEMI() {
        assertEquals(10000, service.calculateEMI(120000, 1));
    }

    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(0, 2);
        });
    }

    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(100000, 0);
        });
    }

    @Test
    void testPremiumCategory() {
        assertEquals("Premium", service.getLoanCategory(800));
    }
 
    @Test
    void testStandardCategory() {
        assertEquals("Standard", service.getLoanCategory(700));
    }
    
    @Test
    void testHighRiskCategory() {
        assertEquals("High Risk", service.getLoanCategory(500));
    }
 
    @Test
    void testGroupedAssertions() {

        assertAll(
            () -> assertTrue(service.isEligible(25, 40000)),
            () -> assertEquals("Standard", service.getLoanCategory(650)),
            () -> assertNotNull(service)
        );
    }
}
