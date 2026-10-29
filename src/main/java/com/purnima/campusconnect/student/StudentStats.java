package com.purnima.campusconnect.student;

import java.util.Map;

public record StudentStats(
    long totalStudents,
    Map<String, Long> departmentCounts,
    Map<Integer, Long> graduationYearCounts
) {}

