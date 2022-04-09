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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ServiceLibrary serviceLibrary;
    private ServiceClient serviceClient;

    public ClientController(ServiceLibrary serviceLibrary, ServiceClient serviceClient) {
        this.serviceLibrary = serviceLibrary;
        this.serviceClient = serviceClient;
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
                                  @PathVariable(required = false)String id,
                                  Model model
    ) {
        saveClientForm = new SaveClientForm(new Client());
        model.addAttribute("saveClientForm", saveClientForm);
        return "saveClient";
    }

    @GetMapping("/clientcreate/{id}")
    public String getClientCreateWithId(@ModelAttribute SaveClientForm saveClientForm,
                           @PathVariable("id")String id,
                           Model model
                          ) {
        saveClientForm = new SaveClientForm(new Client());
        model.addAttribute("saveClientForm", saveClientForm);
        return "clients";
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
        return "clients";
       // return  "redirect:clientcreate/" + saveClientForm.getId();
    }
    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("pageTitle", "Mon Demo");
        var clients = serviceClient.findAllClients();
        model.addAttribute("clients", clients);
        return "clients";
    }



}

