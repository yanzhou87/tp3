package library.controllers;

import library.forms.SaveBookForm;
import library.forms.SaveEmpruntForm;
import library.model.Book;
import library.model.Emprunt;
import library.service.ServiceLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmpruntController {
    Logger logger = LoggerFactory.getLogger(EmpruntController.class);

    private ServiceLibrary serviceLibrary;

    public EmpruntController(ServiceLibrary serviceLibrary) {
        this.serviceLibrary = serviceLibrary;
    }

    @GetMapping("/empruntcreate")
    public String getEmpruntCreate(@ModelAttribute SaveEmpruntForm saveEmpruntForm, @PathVariable(required = false) String id, Model model) {
        saveEmpruntForm = new SaveEmpruntForm(new Emprunt());
        model.addAttribute("saveEmpruntForm", saveEmpruntForm);
        return "saveEmprunt";
    }

    @PostMapping("/empruntcreate")
    public String empruntPost(@ModelAttribute SaveEmpruntForm saveEmpruntForm,
                              BindingResult errors,
                              Model model,
                              RedirectAttributes redirectAttributes){
        logger.info("emprunt : " + saveEmpruntForm);
        serviceLibrary.getEmpruntRepository().save(saveEmpruntForm.toEmprunt());
        redirectAttributes.addFlashAttribute("saveEmpruntForm",saveEmpruntForm);
        saveEmpruntForm = new SaveEmpruntForm(new Emprunt());
        model.addAttribute("saveEmpruntForm",saveEmpruntForm);
        return "redirect:empruntcreate";
    }

}
