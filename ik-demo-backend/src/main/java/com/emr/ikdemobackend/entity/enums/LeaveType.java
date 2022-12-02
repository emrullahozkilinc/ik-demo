package com.emr.ikdemobackend.entity.enums;

public enum LeaveType {
    ANNUAL_PERMIT("Annual Permit"), MATERNITY_LEAVE("Maternity Leave"), SICK_LEAVE("Sick Leave");

    final String name;

    LeaveType(String name) {
        this.name = name;
    }
}
