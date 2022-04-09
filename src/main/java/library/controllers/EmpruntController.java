package library.controllers;

import library.forms.SaveBookForm;
import library.forms.SaveEmpruntForm;
import library.model.Book;
import library.model.Client;
import library.model.Emprunt;
import library.model.Exemplaire;
import library.service.ServiceClient;
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

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EmpruntController {
    Logger logger = LoggerFactory.getLogger(EmpruntController.class);

    private ServiceLibrary serviceLibrary;
    private ServiceClient serviceClient;

    public EmpruntController(ServiceLibrary serviceLibrary, ServiceClient serviceClient) {
        this.serviceLibrary = serviceLibrary;
        this.serviceClient = serviceClient;
    }

    @GetMapping("/empruntcreate")
    public String getEmpruntCreate(@ModelAttribute SaveEmpruntForm saveEmpruntForm, @PathVariable(required = false) String id, Model model) {
      //  saveEmpruntForm = new SaveEmpruntForm(new Emprunt());
        model.addAttribute("saveEmpruntForm", saveEmpruntForm);
        return "saveEmprunt";
    }

    @PostMapping("/empruntcreate")
    public String empruntPost(@ModelAttribute SaveEmpruntForm saveEmpruntForm,
                              BindingResult errors,
                              Model model,
                              RedirectAttributes redirectAttributes){
        logger.info("emprunt : " + saveEmpruntForm);
        List<Exemplaire> exemplaires = serviceClient.findALLExemplairesByArticleId(saveEmpruntForm.getArticleId());
        Client client = serviceClient.findClientById(saveEmpruntForm.getClientId()).get();

        Emprunt emprunt = serviceLibrary.saveEmprunt(serviceClient.findArticleById(saveEmpruntForm.getArticleId()).get()
                ,exemplaires, client, LocalDateTime.now());
        serviceLibrary.addEmpruntToClient(emprunt.getId(),client.getId());
        redirectAttributes.addFlashAttribute("saveEmpruntForm",saveEmpruntForm);
        model.addAttribute("saveEmpruntForm",saveEmpruntForm);

        return "redirect:emprunts";
    }

    @GetMapping("/emprunts")
    public String getEmprunts(Model model) {
        model.addAttribute("pageTitle", "Emprunts");
        var emprunts = serviceClient.findAllEmprunts();
        model.addAttribute("emprunts", emprunts);
        return "emprunts";
    }

}
