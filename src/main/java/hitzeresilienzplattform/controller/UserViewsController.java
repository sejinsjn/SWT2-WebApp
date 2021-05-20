package hitzeresilienzplattform.controller;

import hitzeresilienzplattform.entities.SensorDaten;
import hitzeresilienzplattform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserViewsController {

    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping("/sensorData")
    public String sensorData(Model model) {

        return "";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute SensorDaten user, Model model) {
        model.addAttribute("user", user);

        userService.createUser(user);

        return "redirect:/";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
