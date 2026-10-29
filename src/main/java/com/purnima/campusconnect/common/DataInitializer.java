package com.purnima.campusconnect.common;

import com.purnima.campusconnect.student.Student;
import com.purnima.campusconnect.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository repository;

    public DataInitializer(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        // Only seed if database is completely empty
        if (repository.count() > 0) {
            return;
        }

        List<Student> initialStudents = List.of(
            new Student("Aarav Sharma", "aarav.sharma@campus.edu", "CSE", 2026),
            new Student("Diya Patel", "diya.patel@campus.edu", "ECE", 2027),
            new Student("Rohan Verma", "rohan.verma@campus.edu", "IT", 2025),
            new Student("Ananya Iyer", "ananya.iyer@campus.edu", "AIML", 2028),
            new Student("Vikram Reddy", "vikram.reddy@campus.edu", "MECH", 2026),
            new Student("Sneha Kulkarni", "sneha.kulkarni@campus.edu", "CSE", 2027),
            new Student("Aditya Nair", "aditya.nair@campus.edu", "CIVIL", 2025),
            new Student("Pooja Hegde", "pooja.hegde@campus.edu", "ECE", 2028),
            new Student("Siddharth Rao", "siddharth.rao@campus.edu", "AIDS", 2027),
            new Student("Kavya Menon", "kavya.menon@campus.edu", "CSE", 2026),
            new Student("Rahul Gupta", "rahul.gupta@campus.edu", "IT", 2028),
            new Student("Meera Nambiar", "meera.nambiar@campus.edu", "EEE", 2025),
            new Student("Arjun Deshmukh", "arjun.deshmukh@campus.edu", "MECH", 2027),
            new Student("Ishita Banerjee", "ishita.banerjee@campus.edu", "AIML", 2026),
            new Student("Karthik Sundaram", "karthik.sundaram@campus.edu", "CSE", 2025),
            new Student("Tanvi Joshi", "tanvi.joshi@campus.edu", "ECE", 2028),
            new Student("Manish Choudhary", "manish.choudhary@campus.edu", "CIVIL", 2026),
            new Student("Rhea Mukherjee", "rhea.mukherjee@campus.edu", "IT", 2027),
            new Student("Gautam Prasad", "gautam.prasad@campus.edu", "AIDS", 2025),
            new Student("Shreya Sen", "shreya.sen@campus.edu", "CSE", 2028),
            new Student("Varun Singhania", "varun.singhania@campus.edu", "MECH", 2026),
            new Student("Deepika Pillai", "deepika.pillai@campus.edu", "AIML", 2027),
            new Student("Nikhil Bhatt", "nikhil.bhatt@campus.edu", "ECE", 2025),
            new Student("Prachi Mishra", "prachi.mishra@campus.edu", "CSE", 2028),
            new Student("Harish Krishnan", "harish.krishnan@campus.edu", "EEE", 2026),
            new Student("Sanjana Kapoor", "sanjana.kapoor@campus.edu", "IT", 2027),
            new Student("Abhishek Tiwari", "abhishek.tiwari@campus.edu", "CIVIL", 2028),
            new Student("Divya Nambeesan", "divya.nambeesan@campus.edu", "CSE", 2025),
            new Student("Yash Chopra", "yash.chopra@campus.edu", "AIDS", 2026),
            new Student("Ritu Agarwal", "ritu.agarwal@campus.edu", "ECE", 2027),
            new Student("Pranav Namboodiri", "pranav.namboodiri@campus.edu", "MECH", 2028),
            new Student("Anjali Chauhan", "anjali.chauhan@campus.edu", "AIML", 2025),
            new Student("Tarun Saxena", "tarun.saxena@campus.edu", "CSE", 2026),
            new Student("Swati Mahajan", "swati.mahajan@campus.edu", "IT", 2028),
            new Student("Karan Varma", "karan.varma@campus.edu", "CIVIL", 2027),
            new Student("Pallavi Dutta", "pallavi.dutta@campus.edu", "ECE", 2026),
            new Student("Chetan Rawat", "chetan.rawat@campus.edu", "EEE", 2028),
            new Student("Bhavna Sethi", "bhavna.sethi@campus.edu", "CSE", 2027),
            new Student("Devansh Bhatia", "devansh.bhatia@campus.edu", "AIDS", 2025),
            new Student("Monika Das", "monika.das@campus.edu", "MECH", 2026),
            new Student("Kunal Goswami", "kunal.goswami@campus.edu", "IT", 2027),
            new Student("Aakanksha Roy", "aakanksha.roy@campus.edu", "AIML", 2028),
            new Student("Samarpan Das", "samarpan.das@campus.edu", "CSE", 2025),
            new Student("Trisha Bose", "trisha.bose@campus.edu", "ECE", 2026),
            new Student("Naveen Chandran", "naveen.chandran@campus.edu", "CIVIL", 2028),
            new Student("Radhika Mittal", "radhika.mittal@campus.edu", "CSE", 2027),
            new Student("Omkar Jadhav", "omkar.jadhav@campus.edu", "MECH", 2025),
            new Student("Aishwarya Soni", "aishwarya.soni@campus.edu", "IT", 2026),
            new Student("Vivek Kaushik", "vivek.kaushik@campus.edu", "EEE", 2027),
            new Student("Lavanya Venkatesh", "lavanya.venkatesh@campus.edu", "AIDS", 2028)
        );

        repository.saveAll(initialStudents);
    }
}

