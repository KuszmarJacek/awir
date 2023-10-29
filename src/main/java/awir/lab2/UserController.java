package awir.lab2;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class UserController {
    @GetMapping("/adduser")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String userSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid User user, BindingResult
            bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        store(file);
        model.addAttribute("user", user);
        return "user-info";
    }

    private void store(MultipartFile file) {
        try {
            Files.write(Paths.get("foo.png"), file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
