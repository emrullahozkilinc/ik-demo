package com.emr.ikdemobackend.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum LeaveType {
    ANNUAL_PERMIT("Annual Permit"), MATERNITY_LEAVE("Maternity Leave"), SICK_LEAVE("Sick Leave");

    final String name;

    static final Map<String, LeaveType> leavesMap;
    static {
        leavesMap = new HashMap<>();
        for (LeaveType type : LeaveType.values())
            leavesMap.put(type.getName(), type);
    }

    LeaveType(String name) {
        this.name = name;
    }

    public static LeaveType getByName(String key){
        return leavesMap.get(key);
    }

    public String getName() {
        return name;
    }


}
