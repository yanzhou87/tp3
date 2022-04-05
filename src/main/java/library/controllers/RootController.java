package library.controllers;


import library.service.ServiceLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ServiceLibrary serviceLibrary;

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
}
