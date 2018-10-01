package com.frontline.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontline.dao.CategoryDAO;
import com.frontline.model.Category;


@Controller
public class CategoryController {

	@Autowired
	CategoryDAO categorydao;
	
	@RequestMapping("/category")
	public String showCategoryPage(Model m)
	{
		List<Category> listCategory = categorydao.listCategories();
		m.addAttribute("categoryList", listCategory);
		return "Category";
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public String addCategory(@RequestParam("categoryname")String categoryname,@RequestParam("categorydesc")String categorydesc,Model m)
	{
		
		Category category = new Category();
		category.setCategoryname(categoryname);
		category.setCategorydesc(categorydesc);
		
		categorydao.addCategory(category);
		
		List<Category> listCategory = categorydao.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/updateCategory",method=RequestMethod.POST)
	public String updateCategory(@RequestParam("categoryid")int categoryid,@RequestParam("categoryname")String categoryname,@RequestParam("categorydesc")String categorydesc,Model m)
	{
		
		Category category = categorydao.getCategory(categoryid);
		category.setCategoryname(categoryname);
		category.setCategorydesc(categorydesc);
		
		categorydao.updateCategory(category);
		
		List<Category> listCategory = categorydao.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/deleteCategory/{categoryid}")
	public String deleteCategory(@PathVariable("categoryid")int categoryid,Model m)
	{
		Category category = categorydao.getCategory(categoryid);
		categorydao.deleteCategory(category);
		
		List<Category> listCategory=categorydao.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/editCategory/{categoryid}")
	public String editCategory(@PathVariable("categoryid")int categoryid,Model m)
	{
		Category category = categorydao.getCategory(categoryid);
		m.addAttribute("categoryData", category);
		return "UpdateCategory";
	}

	
	
}
