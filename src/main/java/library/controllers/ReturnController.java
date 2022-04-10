package library.controllers;

import library.forms.ReturnArticleForm;
import library.service.ServiceClient;

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

import java.time.LocalDate;

@Controller
public class ReturnController {
    Logger logger = LoggerFactory.getLogger(ReturnController.class);

  private ServiceClient serviceClient;

    public ReturnController(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/returncreate")
    public String getReturnCreate(@ModelAttribute ReturnArticleForm returnArticleForm,
                                  @PathVariable(required = false)String id,
                                  Model model){
       model.addAttribute("returnArticleForm", returnArticleForm);
       return "returnArticle";
    }

    @PostMapping("/returncreate")
    public String returnPost(@ModelAttribute ReturnArticleForm returnArticleForm,
                           BindingResult errors,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        logger.info("return: " + returnArticleForm);
        serviceClient.returnEmprunt(serviceClient.findClientById(returnArticleForm.getClientId()).get(),returnArticleForm.getArticleId(),LocalDate.now());
        redirectAttributes.addFlashAttribute("ReturnArticleForm",returnArticleForm);
        model.addAttribute("ReturnArticleForm", returnArticleForm);
        return  "redirect:emprunts";
    }
}
