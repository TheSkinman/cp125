package com.scg.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

class StaffConsultantTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test_setPayRate_BadArgument() {
        StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, 3, 3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sc.setPayRate(-1);
          });
    }

    @Test
    void test_setSickLeaveHours_BadArgument() {
        StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, 3, 3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sc.setSickLeaveHours(-1);
          });
    }

    @Test
    void test_getVacationHours() {
        StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, 3, 3);
    }

    @Test
    void test_setVacationHours_BadArgument() {
        StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, 3, 3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sc.setVacationHours(-1);
          });
    }

    @Test
    void test_StaffConsultant_Construct_BadArgument_NullName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StaffConsultant sc = new StaffConsultant(null, 3, 3, 3);
          });
    }

    @Test
    void test_StaffConsultant_Construct_BadArgument_NegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), -1, 3, 3);
          });
    }

    @Test
    void test_StaffConsultant_Construct_BadArgument_NegativeSickLeave() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, -1, 3);
          });
    }

    @Test
    void test_StaffConsultant_Construct_BadArgument_NegativeVacation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StaffConsultant sc = new StaffConsultant(new PersonalName("lastName", "firstName", "middleName"), 3, 3, -1);
          });
    }

}
