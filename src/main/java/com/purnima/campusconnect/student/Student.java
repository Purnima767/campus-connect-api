package com.purnima.campusconnect.student;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
@Table(name="students", indexes={
    @Index(name="idx_student_name", columnList="name"),
    @Index(name="idx_student_department", columnList="department")
})
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=100) private String name;
    @Column(nullable=false, unique=true, length=160) private String email;
    @Column(nullable=false, length=80) private String department;
    @Column(nullable=false) private Integer graduationYear;
    @Column(nullable=false, updatable=false) private Instant createdAt = Instant.now();
    protected Student() {}
    public Student(String name, String email, String department, Integer graduationYear) {
        this.name=name; this.email=email; this.department=department; this.graduationYear=graduationYear;
    }
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
    public String getDepartment(){return department;} public Integer getGraduationYear(){return graduationYear;}
    public Instant getCreatedAt(){return createdAt;}
    public void update(String name,String email,String department,Integer graduationYear){
        this.name=name; this.email=email; this.department=department; this.graduationYear=graduationYear;
    }
}