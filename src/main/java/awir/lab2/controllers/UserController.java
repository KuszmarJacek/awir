package awir.lab2.controllers;

import awir.lab2.entities.User;
import awir.lab2.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping("/adduser")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

//    @PostMapping("/adduser")
//    public String userSubmit(
//        @RequestParam("file") MultipartFile file,
//        @ModelAttribute @Valid User user,
//        BindingResult bindingResult,
//        Model model) {
//        if (bindingResult.hasErrors()) {
//            return "add-user";
//        }
//        store(file);
//        model.addAttribute("user", user);
//
//        return "user-info";
//    }

    @PostMapping("/adduser")
    public String userSubmit(
            @ModelAttribute @Valid User user,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }

        model.addAttribute("user", user);
        repository.save(user);

        return "user-info";
    }

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
