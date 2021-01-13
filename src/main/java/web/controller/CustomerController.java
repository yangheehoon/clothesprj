package web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import web.model.Clothes;
import web.service.ClothesService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

ClothesService clothesservice = new ClothesService();	
	
	@RequestMapping("/clotheslist")
	public String ClothesList(Model model) {   
	
		List<Clothes> clotheslist = clothesservice.ServiceClothesList();
		
		model.addAttribute("clotheslist", clotheslist);
		
	return "customer/clotheslist";
	}	
		
	@RequestMapping("/clothesadd")
	public String AddClothes() {
		
		return "customer/clothesadd";
	}
	
	@RequestMapping("/clothesadd2")
	public String AddClothes2() {
		
		return "redirect:/customer/clotheslist";
	}
}
