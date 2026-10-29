package com.purnima.campusconnect.student;

import com.purnima.campusconnect.common.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> list(int page, int size, String sortBy, String direction, String query, String department) {
        if (page < 0) throw new IllegalArgumentException("Page must be zero or greater.");
        if (size < 1 || size > 100) throw new IllegalArgumentException("Page size must be between 1 and 100.");
        String safeSort = java.util.Set.of("name", "email", "department", "graduationYear", "createdAt").contains(sortBy) ? sortBy : "createdAt";
        Sort sort = "asc".equalsIgnoreCase(direction) ? Sort.by(safeSort).ascending() : Sort.by(safeSort).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> result;
        boolean hasQuery = query != null && !query.isBlank();
        boolean hasDept = department != null && !department.isBlank();

        if (hasQuery && hasDept) {
            result = repo.searchStudents(query.trim(), department.trim(), pageable);
        } else if (hasQuery) {
            result = repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query.trim(), query.trim(), pageable);
        } else if (hasDept) {
            result = repo.findByDepartmentIgnoreCase(department.trim(), pageable);
        } else {
            result = repo.findAll(pageable);
        }

        return result.map(StudentResponse::from);
    }

    public StudentResponse create(StudentRequest request) {
        if (repo.existsByEmailIgnoreCase(request.email())) {
            throw new DuplicateResourceException("Email is already registered.");
        }
        Student s = new Student(
            request.name().trim(),
            request.email().trim().toLowerCase(),
            request.department().trim(),
            request.graduationYear()
        );
        return StudentResponse.from(repo.save(s));
    }

    @Transactional(readOnly = true)
    public StudentResponse get(long id) {
        return StudentResponse.from(repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Student " + id + " was not found.")));
    }

    public StudentResponse update(long id, StudentRequest request) {
        Student s = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Student " + id + " was not found."));
        repo.findByEmailIgnoreCase(request.email())
            .filter(other -> !other.getId().equals(id))
            .ifPresent(other -> {
                throw new DuplicateResourceException("Email is already used by another student.");
            });
        s.update(
            request.name().trim(),
            request.email().trim().toLowerCase(),
            request.department().trim(),
            request.graduationYear()
        );
        return StudentResponse.from(s);
    }

    public void delete(long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Student " + id + " was not found.");
        }
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public StudentStats getStats() {
        long total = repo.count();
        List<Student> all = repo.findAll();
        Map<String, Long> deptCounts = all.stream()
            .collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
        Map<Integer, Long> gradYearCounts = all.stream()
            .collect(Collectors.groupingBy(Student::getGraduationYear, Collectors.counting()));
        return new StudentStats(total, deptCounts, gradYearCounts);
    }

    @Transactional(readOnly = true)
    public List<String> getDepartments() {
        return repo.findDistinctDepartments();
    }
}
