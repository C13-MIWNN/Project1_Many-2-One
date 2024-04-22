package nl.mitw.ch13.many2one.ctrlalteat.controller;

import nl.mitw.ch13.many2one.ctrlalteat.dtos.CtrlAltEatUserFormDTO;
import nl.mitw.ch13.many2one.ctrlalteat.model.CtrlAltEatUser;
import nl.mitw.ch13.many2one.ctrlalteat.services.CtrlAltEatUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Author Linda Munsterman
 * purpose for the class
 **/

@Controller
public class CtrlAltEatUserController {

    private final CtrlAltEatUserService ctrlAltEatUserService;

    public CtrlAltEatUserController(CtrlAltEatUserService ctrlAltEatUserService) {
        this.ctrlAltEatUserService = ctrlAltEatUserService;
    }

    @GetMapping("/user/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new CtrlAltEatUserFormDTO());
        return "userForm";
    }

    @PostMapping("/user/new")
    public String processUserForm(@ModelAttribute("user") CtrlAltEatUserFormDTO ctrlAltEatUserFormDTO,
                                  BindingResult bindingResult) {
        if (ctrlAltEatUserService.userExists(ctrlAltEatUserFormDTO.getName())) {
            bindingResult.rejectValue("name", "duplicate",
                    "This username is not available");
        }

        if (!ctrlAltEatUserFormDTO.getPassword().equals(ctrlAltEatUserFormDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", "no.match",
                    "The passwords do not match");
        }

        if(bindingResult.hasErrors()) {
            return "userForm";
        }

        ctrlAltEatUserService.saveUser(ctrlAltEatUserFormDTO);
        return "redirect:/user/new";
    }
}
