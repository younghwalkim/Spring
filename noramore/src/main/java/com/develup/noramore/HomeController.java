package com.develup.noramore;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.develup.noramore.category.model.service.CategoryService;
import com.develup.noramore.category.model.vo.Category;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private CategoryService categoryService;
	
	
	
	@RequestMapping("home.do")
	public String forwardMainView(Model model) {
		ArrayList<Category> list = categoryService.selectAll();
		
		model.addAttribute("categoryList", list);
		
		return "common/home";
	}//
	
}
