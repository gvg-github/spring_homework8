package net.zt.funcode.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.zt.funcode.domain.Author;
import net.zt.funcode.service.AuthorService;

@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private MessageSource messageSource;
	
    //возвращает форму регистрации 
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registrationForm(Model uiModel){
		
		Author author = new Author();
	    uiModel.addAttribute("author", author);
		return "security/registration";
	}
	
	//принимает данные формы
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String registration(Model uiModel, @ModelAttribute("author") @Valid Author author, BindingResult bindingResult, RedirectAttributes redAttributes, Locale locale){
		
		if(bindingResult.hasErrors()){
			
			uiModel.addAttribute("message", messageSource.getMessage("author_create_fail", new Object[]{}, locale));
			return "security/registration";
			
		}
		if(authorService.getByLogin(author.getLogin())!=null){
			
		    uiModel.addAttribute("message", messageSource.getMessage("author_login_exist", new Object[]{}, locale));
			return "security/registration";
			
		}
		authorService.save(author);
		return "redirect:/";
	}

}
