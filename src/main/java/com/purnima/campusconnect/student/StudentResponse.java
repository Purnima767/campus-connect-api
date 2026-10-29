package com.purnima.campusconnect.student;
import java.time.Instant;
public record StudentResponse(Long id,String name,String email,String department,Integer graduationYear,Instant createdAt) {
    public static StudentResponse from(Student s) {
        return new StudentResponse(s.getId(),s.getName(),s.getEmail(),s.getDepartment(),s.getGraduationYear(),s.getCreatedAt());
    }
}