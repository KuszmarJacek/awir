package awir.lab2.controllers;

import awir.lab2.entities.User;
import awir.lab2.repositories.UserRepository;
import awir.lab2.services.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    MailService mailService;

//    @Value("${spring.mail.username}")
    String MAIL_LOGIN;

    @GetMapping("/adduser")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
//        mailService.sendMail("kj46573@zut.edu.pl", "Added user", "asdfdfghsdfhgdsfhgiudshg");
        return "add-user";
    }

    @PostMapping("/adduser")
    public String userSubmit(
        @RequestParam("file") MultipartFile file,
        @ModelAttribute @Valid User user,
        BindingResult bindingResult,
        Model model) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
//        store(file);
        model.addAttribute("user", user);
//        mailService.sendMail("kj46573@zut.edu.pl", "Added user", "asdfdfghsdfhgdsfhgiudshg");
        mailService.sendMessageWithAttachment(MAIL_LOGIN, "Added user", user.getName() + " " + user.getEmail(), file);
        return "user-info";
    }

//    @PostMapping("/adduser")
//    public String userSubmit(
//            @ModelAttribute @Valid User user,
//            BindingResult bindingResult,
//            Model model) {
//        if (bindingResult.hasErrors()) {
//            return "add-user";
//        }
//
//        model.addAttribute("user", user);
//        repository.save(user);
////        mailService.sendMail("kj46573@zut.edu.pl", "Added user", "asdfdfghsdfhgdsfhgiudshg");
//        return "user-info";
//    }

    @GetMapping("/userinfo/{username}")
    public String userInfo(@PathVariable String username, Model model) {
        model.addAttribute("user", repository.findUserByName(username));
        return "user-info";
    }

    @PostMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/adduser";
    }

    @PostMapping("/updateuser/{id}")
    public String updateUserInfo(@PathVariable Long id, @ModelAttribute User updatedUser) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            return "error";
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        repository.save(user);

        return "redirect:/userinfo/" + updatedUser.getName();
    }

//    private void store(MultipartFile file) {
//        try {
//            Files.write(Paths.get("foo.png"), file.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
