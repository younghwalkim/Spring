package com.develup.noramore.category.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.develup.noramore.category.model.service.CategoryService;
import com.develup.noramore.category.model.vo.Category;
import com.develup.noramore.recrboard.controller.RecrBoardController;

@Controller
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(RecrBoardController.class);
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("insertCategory.do")
	public String insertCategory(Category category, Model model, HttpServletRequest request,
			@RequestParam("PNGimageFile") MultipartFile imageFile) {
		
		String savePath = request.getSession().getServletContext().getRealPath("resources/categoryImg");
		int categoryId = categoryService.nextCategoryId();
		category.setCategoryId(categoryId);
		
		if (!imageFile.isEmpty()) {
			String fileName = imageFile.getOriginalFilename();
			String renameFileName = null;

			if (fileName != null && fileName.length() > 0) {
				renameFileName = "categoryImg" + category.getCategoryId();
				logger.info("파일명 변경 : " + fileName + " => " + renameFileName);

				try {
					imageFile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "첨부파일 파일명 변환 중 에러가 발생했습니다. ");
					return "recerBoarad/RecrBoardList";
				}
			}

			category.setOriginalFileName(fileName);
			category.setRenameFileName(renameFileName);
		}
		
		int result = categoryService.insertCategory(category);


		if (result > 0) {
			model.addAttribute("message", category.getCategoryName() + "카테고리가 추가되었습니다.");
			return "redirect:home.do";
		} else {
			model.addAttribute("message", category.getCategoryName() + "카테고리 추가에 실패했습니다.");
			return "redirect:home.do";
		}

	}//
	
	
	@RequestMapping("goCategoryWriteForm.do")
	public String goCategoryWriteForm() {	
		return"common/categoryWriteForm";
	}
	
	@RequestMapping(value="selectCategory.do", method = RequestMethod.POST)
	@ResponseBody
	public String selecrCategory() {
		ArrayList<Category> list = categoryService.selectCategory();
		
		JSONArray jarr = new JSONArray();
		for(Category category : list) {
			JSONObject job = new JSONObject();
			job.put("categoryId", category.getCategoryId());
			job.put("categoryName", category.getCategoryName());
			
			jarr.add(job);
		}
		
		return jarr.toJSONString();
	}//
	
}//




















