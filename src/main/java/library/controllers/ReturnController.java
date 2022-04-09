package library.controllers;

import library.forms.ReturnArticleForm;
import library.forms.SaveBookForm;
import library.model.Exemplaire;
import library.service.ServiceClient;
import library.service.ServiceLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReturnController {
    Logger logger = LoggerFactory.getLogger(ReturnController.class);

  private ServiceClient serviceClient;
  private ServiceLibrary serviceLibrary;

    public ReturnController(ServiceClient serviceClient, ServiceLibrary serviceLibrary) {
        this.serviceClient = serviceClient;
        this.serviceLibrary = serviceLibrary;
    }
//
//    public String getReturnCreate(@ModelAttribute ReturnArticleForm returnArticleForm,
//                                  @PathVariable(required = false)String id,
//                                  Model model){
//        Exemplaire exemplaire = serviceClient.findClientById(Long.parseLong(id)).get().getEmprunts().
//        returnArticleForm = new ReturnArticleForm(serviceClient.findClientById(Long.parseLong(id)).get());
//    }
}
