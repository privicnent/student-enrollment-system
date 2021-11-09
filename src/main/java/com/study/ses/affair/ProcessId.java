package com.study.ses.affair;

public enum ProcessId {

    COURSE_LIST(1),
    VIEW(2),
    UPDATE(3),
    DELETE(4),
    EXIT(5);

    private final int code;

    ProcessId(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProcessId determineProcessId(final int value) {
        for (ProcessId e : ProcessId.values()) {
            if (e.getCode() == value) {
                return e;
            }
        }
        return null;
    }

}
