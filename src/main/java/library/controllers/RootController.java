package library.controllers;


import library.forms.SaveClientForm;
import library.model.Client;
import library.service.ServiceClient;
import library.service.ServiceLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RootController {

    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ServiceLibrary serviceLibrary;
    private ServiceClient serviceClient;

    public RootController(ServiceLibrary serviceLibrary) {
        this.serviceLibrary = serviceLibrary;
    }

    @GetMapping("/")
    public String getRootRequest(Model model) {
        model.addAttribute("saveClient", "Save client");
        model.addAttribute("saveNewBook", "Save New Book");
        model.addAttribute("empruntBook", "Emprunt Book");
        model.addAttribute("returnEmprunt", "Return Emprunt");
        return "index";
    }

    @GetMapping("/clientcreate")
    public String getClientCreate(@ModelAttribute SaveClientForm saveClientForm,
                           BindingResult errors,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        saveClientForm = new SaveClientForm(new Client());
        model.addAttribute("saveClientForm", saveClientForm);
        return "saveClient";
    }


    @PostMapping("/clientcreate")
    public String clientPost(@ModelAttribute SaveClientForm saveClientForm,
                             BindingResult errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("client: " + saveClientForm);
        serviceLibrary.saveUser(saveClientForm.toClient());
        redirectAttributes.addFlashAttribute("saveClientForm",saveClientForm);
        saveClientForm = new SaveClientForm(new Client());
        model.addAttribute("saveClientForm", saveClientForm);
        return  "redirect:saveClient/" + saveClientForm.getId();
    }
}

