package web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.model.Clothes;
import web.service.ClothesService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ServletContext ctx;
	
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
	public String AddClothes2(@RequestParam(value="n")String name,
			@RequestParam(value="p" , defaultValue="99999999")int price,
			@RequestParam(value="d")String description,
			@RequestParam(value="f")MultipartFile reqfiles,
			Model model	) throws IllegalStateException, IOException {
			
			String files = reqfiles.getOriginalFilename();
		
			String webpath="/resources/customer/clothes";
			String realpath= ctx.getRealPath(webpath);
			System.out.println(realpath);
			realpath += File.separator + files;
			File savefile = new File(realpath);
			reqfiles.transferTo(savefile);
			
			
			clothesservice.ServiceInsertClothes(name, price, description, files);
		
		return "redirect:/customer/clotheslist";
	}
}
