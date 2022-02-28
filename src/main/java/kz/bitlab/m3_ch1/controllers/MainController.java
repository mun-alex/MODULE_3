package kz.bitlab.m3_ch1.controllers;

import kz.bitlab.m3_ch1.entities.Sport;
import kz.bitlab.m3_ch1.entities.Student;
import kz.bitlab.m3_ch1.entities.UniUser;
import kz.bitlab.m3_ch1.service.*;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private UniUser emptyUniUser;

    @Autowired
    private Student emptyStudent;

    @Value("${file.photo.upload}")
    private String uploadPath;

    @Value("${file.photo.upload.target}")
    private String uploadPathTarget;

    @Value("${file.photo.view}")
    private String viewPath;

    @Value("${file.photo.default}")
    private String defaultPhoto;

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

//    @GetMapping
//    public List<Student> allStudents() {
//        return studentService.getAllStudents();
//    }

    @PostMapping(value = "/add-student")
    public String addStudent(@ModelAttribute(name = "student") Student student,
                             @RequestParam(name = "studentPhoto") MultipartFile file) {
        String studentPhoto = uploadPhoto(file);
        student.setPhoto(studentPhoto);
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping(value = "/view-photo/{photoName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] viewStudentPhoto(@PathVariable(name = "photoName") String photoName) throws IOException {
        String photoUrl = viewPath + defaultPhoto;
        if (photoName != null) {
            photoUrl = viewPath + photoName + ".jpg";
        }
        InputStream inputStream;
        try {
            ClassPathResource resource = new ClassPathResource(photoUrl);
            inputStream = resource.getInputStream();
        } catch (Exception e) {
            ClassPathResource resource = new ClassPathResource(viewPath + defaultPhoto);
            inputStream = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(inputStream);
    }

//    @PostMapping(value = "/student")
//    public String addStudent(@RequestBody Student student) {
//        studentService.addStudent(student);
//        return "success";
//    }

//    @DeleteMapping(value = "/student/{id}")
//    public String deleteStudent(@PathVariable Long id) {
//        studentService.deleteStudentById(id);
//        return "deleted";
//    }

    @GetMapping(value = "/student/{id}")
    public Student getStudent(@PathVariable (name = "id") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping(value = "/details/{studentId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getStudentById(Model model, @PathVariable(name = "studentId") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("sports", sportService.getAllSports());
        model.addAttribute("currentUser", getUserData());
        return "/details";
    }

    @PostMapping(value = "/update-student/{studentId}")
    public String updateStudent(@PathVariable(name = "studentId") Long studentId,
                                @ModelAttribute(name = "student") Student student,
                                @RequestParam(name = "studentPhoto") MultipartFile file) {
        if (!file.isEmpty()) {
            student.setPhoto(uploadPhoto(file));
        }

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
        return "/403";
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

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("uniUser", emptyUniUser);
        return "/register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute(name = "uniUser") UniUser newUser,
                           @RequestParam(name = "uni_user_repassword") String repass) {

        if (newUser.getPassword().equals(repass)) {
            UniUser user = uniUserService.createUser(newUser);

            if (user != null) {
                return "redirect:/login?successRegister";
            }
            return "redirect:/register?errorEmail";
        }
        return "redirect:/register?errorPass";
    }

    @GetMapping(value = "/filter-city-and-faculty/{cityId}/{facultyId}")
    public String allStudentsByCityAndFaculty(
                                                @PathVariable(name = "cityId") Long cityId,
                                                @PathVariable(name = "facultyId") Long facultyId,
                                                Model model) {
        model.addAttribute("students", studentService.getAllStudentsByCityIdAndfFacultyId(cityId, facultyId));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        model.addAttribute("student", emptyStudent);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    public String uploadPhoto(MultipartFile file) {
        String photoName = DigestUtils.sha1Hex("photo" + file.getOriginalFilename());

        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                Path path = Paths.get(uploadPath + photoName + ".jpg");
                Path pathTarget = Paths.get(uploadPathTarget + photoName + ".jpg");
                byte[] bytes = file.getBytes();
                Files.write(path, bytes);
                Files.write(pathTarget, bytes);
                return photoName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
