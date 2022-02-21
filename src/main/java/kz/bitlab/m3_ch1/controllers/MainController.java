package kz.bitlab.m3_ch1.controllers;

import kz.bitlab.m3_ch1.entities.Sport;
import kz.bitlab.m3_ch1.entities.Student;
import kz.bitlab.m3_ch1.entities.UniUser;
import kz.bitlab.m3_ch1.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CityService cityService;

    @Autowired
    private SportService sportService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UniUserService uniUserService;

    @Autowired
    private Student emptyStudent;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("student", emptyStudent);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(@ModelAttribute(name = "student") Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getStudentById(Model model, @PathVariable(name = "studentId") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("sports", sportService.getAllSports());
        return "/details";
    }

    @PostMapping(value = "/update-student/{studentId}")
    public String updateStudent(@PathVariable(name = "studentId") Long studentId,
                                @ModelAttribute(name = "student") Student student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping(value = "/delete-student/{studentId}")
    public String deleteStudent(@PathVariable(name = "studentId") Long studentId) {
        studentService.deleteStudentById(studentId);
        return "redirect:/";
    }

    @PostMapping(value = "/add-sport")
    public String addSport(@RequestParam(name = "studentId") Long studentId,
                           @RequestParam(name = "sportId") Long sportId) {
        Sport sport = sportService.getSport(sportId);
        if (sport != null) {
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                List<Sport> sportList = student.getSportList();
                if (sportList == null) {
                    sportList = new ArrayList<>();
                }
                sportList.add(sport);
                studentService.updateStudent(student);
                return "redirect:/details/" + studentId;
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/filter-city/{cityId}")
    public String allStudentsByCity(@PathVariable(name = "cityId") Long cityId,
                                    Model model) {
        model.addAttribute("students", studentService.getAllStudentsByCityId(cityId));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("student", emptyStudent);
        return "index";
    }

    @GetMapping(value = "/filter-faculty/{facultyId}")
    public String allStudentsByFaculty(@PathVariable(name = "facultyId") Long facultyId,
                                    Model model) {
        model.addAttribute("students", studentService.getAllStudentsByFacultyId(facultyId));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("student", emptyStudent);
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/login";
    }

    @GetMapping(value = "/403")
    public String accessDenied() {
        return"/403";
    }

    public UniUser getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            UniUser uniUser = uniUserService.getUserByEmail(secUser.getUsername());
            return uniUser;
        }
        return null;
    }
}
