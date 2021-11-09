package com.study.ses.affair;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessIdTest {

    @Test
    void getCode() {
        assertEquals(1, ProcessId.COURSE_LIST.getCode());
        assertEquals(2, ProcessId.VIEW.getCode());
        assertEquals(3, ProcessId.UPDATE.getCode());
        assertEquals(4, ProcessId.DELETE.getCode());
        assertEquals(5, ProcessId.EXIT.getCode());
    }

    @Test
    void determineProcessId() {
        assertEquals(ProcessId.COURSE_LIST, ProcessId.determineProcessId(1));
        assertEquals(ProcessId.VIEW, ProcessId.determineProcessId(2));
        assertEquals(ProcessId.UPDATE, ProcessId.determineProcessId(3));
        assertEquals(ProcessId.DELETE, ProcessId.determineProcessId(4));
        assertEquals(ProcessId.EXIT, ProcessId.determineProcessId(5));
    }

    @Test
    void values() {
        assertEquals(5,ProcessId.values().length);
    }

    @Test
    void valueOf() {
        assertEquals(ProcessId.COURSE_LIST, ProcessId.valueOf("COURSE_LIST"));
        assertEquals(ProcessId.VIEW, ProcessId.valueOf("VIEW"));
        assertEquals(ProcessId.UPDATE, ProcessId.valueOf("UPDATE"));
        assertEquals(ProcessId.DELETE, ProcessId.valueOf("DELETE"));
        assertEquals(ProcessId.EXIT, ProcessId.valueOf("EXIT"));

    }
}