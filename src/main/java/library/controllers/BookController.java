package library.controllers;

import library.forms.SaveBookForm;
import library.model.Book;
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
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    private ServiceLibrary serviceLibrary;

    public BookController(ServiceLibrary serviceLibrary) {
        this.serviceLibrary = serviceLibrary;
    }

    @GetMapping("/bookcreate")
    public String getBookCreate(@ModelAttribute SaveBookForm saveBookForm,
                                @PathVariable(required = false)String id,
                                Model model
                               ) {
        saveBookForm = new SaveBookForm(new Book());
        model.addAttribute("saveBookForm", saveBookForm);
        return "saveBook";
    }

    @GetMapping("/bookcreate/{id}")
    public String getBookCreateWithId(@ModelAttribute SaveBookForm saveBookForm,
                                 @PathVariable("id")String id,
                                  Model model) {
        saveBookForm = new SaveBookForm(new Book());
        model.addAttribute("saveBookForm", saveBookForm);
        return "saveBook";
    }

    @PostMapping("/bookcreate")
    public String bookPost(@ModelAttribute SaveBookForm saveBookForm,
                             BindingResult errors,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("book: " + saveBookForm);
        serviceLibrary.saveArticle(saveBookForm.toBook());
        redirectAttributes.addFlashAttribute("saveBookForm",saveBookForm);
        saveBookForm = new SaveBookForm(new Book());
        model.addAttribute("saveBookForm", saveBookForm);
        return  "redirect:bookcreate/" + saveBookForm.getId();
    }

}
