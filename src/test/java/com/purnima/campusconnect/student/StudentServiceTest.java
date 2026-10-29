package com.purnima.campusconnect.student;

import com.purnima.campusconnect.common.DuplicateResourceException;
import com.purnima.campusconnect.common.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock StudentRepository repo;
    @InjectMocks StudentService service;

    @Test void createRejectsDuplicateEmail() {
        when(repo.existsByEmailIgnoreCase("purnima@example.com")).thenReturn(true);
        StudentRequest request = new StudentRequest("Purnima", "purnima@example.com", "CSE", 2028);
        assertThrows(DuplicateResourceException.class, () -> service.create(request));
        verify(repo, never()).save(any());
    }

    @Test void createSavesNewStudent() {
        when(repo.existsByEmailIgnoreCase("purnima@example.com")).thenReturn(false);
        Student saved = new Student("Purnima", "purnima@example.com", "CSE", 2028);
        when(repo.save(any(Student.class))).thenReturn(saved);

        StudentRequest request = new StudentRequest("Purnima", "purnima@example.com", "CSE", 2028);
        StudentResponse response = service.create(request);

        assertNotNull(response);
        assertEquals("Purnima", response.name());
        assertEquals("purnima@example.com", response.email());
        assertEquals("CSE", response.department());
        assertEquals(2028, response.graduationYear());
        verify(repo, times(1)).save(any(Student.class));
    }

    @Test void getThrowsNotFoundWhenMissing() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.get(99L));
    }

    @Test void getStatsReturnsAggregatedCounts() {
        Student s1 = new Student("Alice", "alice@example.com", "CSE", 2027);
        Student s2 = new Student("Bob", "bob@example.com", "ECE", 2028);
        Student s3 = new Student("Charlie", "charlie@example.com", "CSE", 2028);

        when(repo.count()).thenReturn(3L);
        when(repo.findAll()).thenReturn(List.of(s1, s2, s3));

        StudentStats stats = service.getStats();

        assertEquals(3L, stats.totalStudents());
        assertEquals(2L, stats.departmentCounts().get("CSE"));
        assertEquals(1L, stats.departmentCounts().get("ECE"));
        assertEquals(1L, stats.graduationYearCounts().get(2027));
        assertEquals(2L, stats.graduationYearCounts().get(2028));
    }
}
