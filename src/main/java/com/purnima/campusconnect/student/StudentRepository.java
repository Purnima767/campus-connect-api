package com.purnima.campusconnect.student;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);
    Page<Student> findByDepartmentIgnoreCase(String department, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE " +
           "(:department IS NULL OR :department = '' OR LOWER(s.department) = LOWER(:department)) AND " +
           "(:query IS NULL OR :query = '' OR LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.email) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Student> searchStudents(@Param("query") String query, @Param("department") String department, Pageable pageable);

    @Query("SELECT DISTINCT s.department FROM Student s ORDER BY s.department")
    List<String> findDistinctDepartments();

    boolean existsByEmailIgnoreCase(String email);
    Optional<Student> findByEmailIgnoreCase(String email);
}
