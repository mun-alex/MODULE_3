package kz.bitlab.m3_ch1.controllers;

import kz.bitlab.m3_ch1.model.Student;
import kz.bitlab.m3_ch1.repository.DBManager;
import kz.bitlab.m3_ch1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(@RequestParam(name = "studentName") String name,
                             @RequestParam(name = "studentBirthday") String birthday,
                             @RequestParam(name = "studentCity") String city) {
        studentService.addStudent(new Student(null, name, birthday, city));
        return "redirect:/";
    }

    @GetMapping(value = "/details/{studentId}")
    public String getStudentById(Model model, @PathVariable(name = "studentId") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "/details";
    }
}
