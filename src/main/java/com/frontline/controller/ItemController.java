package com.frontline.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.frontline.dao.CategoryDAO;
import com.frontline.dao.ItemDAO;
import com.frontline.model.Category;
import com.frontline.model.Item;

@Controller
public class ItemController {

	@Autowired
	ItemDAO itemdao;
	
	@Autowired
	CategoryDAO categorydao;
	
	boolean flag=true;
	
	@RequestMapping(value="/item")
	public String showManageItem(Model m)
	{
		Item item = new Item();
		m.addAttribute("item",item);
	
		List<Item> itemlist = itemdao.listItems();
		m.addAttribute("itemList", itemlist);
		
		List<Category> categorylist = categorydao.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categorylist));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	public String addItem(@ModelAttribute("item")Item item,@RequestParam("picmage") MultipartFile fileDetail,Model m,BindingResult result)
	{
		
		itemdao.addItem(item);
		
		//=> Image Uploading Started
		
		String imagePath="C:\\Users\\Vinay\\eclipse-workspace\\FrontLineFrontEnd\\src\\main\\webapp\\WEB-INF\\resources\\images";
		imagePath = imagePath+String.valueOf(item.getItemid())+".jpg";
		
		File myFile=new File(imagePath);
		
		if(!fileDetail.isEmpty())
		{
			try
			{
				byte[] fileBytes=fileDetail.getBytes();
				
				FileOutputStream fos=new FileOutputStream(myFile);
				
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				
				bs.write(fileBytes);
				
				bs.close();
				
			}
			catch(Exception e)
			{
				m.addAttribute("errorInfo","Exception Arised:"+e);
			}
		}
		else
		{
			m.addAttribute("errorInfo","Error While Uploading the Image");
		}
		
		//=> Image Uploading Completed
		
		
		Item item1 = new Item();
		m.addAttribute("item", item1);
	
		List<Item> itemList = itemdao.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categorydao.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	

	@RequestMapping(value="/deleteItem/{itemid}")
	public String deleteItem(@PathVariable("itemid")int itemid,Model m)
	{
		Item item = itemdao.getItem(itemid);
		
		itemdao.deleteItem(item);
		
		Item item1 = new Item();
		m.addAttribute("item", item1);
	
		List<Item> itemList = itemdao.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categorydao.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/editItem/{itemid}")
	public String editItem(@PathVariable("itemid")int itemid,Model m)
	{
		Item item = itemdao.getItem(itemid);
		
		m.addAttribute("item",item);
	
		List<Item> itemList = itemdao.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categorydao.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=false;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/updateItem",method=RequestMethod.POST)
	public String updateItem(@ModelAttribute("item")Item item,Model m)
	{
		
		itemdao.updateItem(item);
		
		Item item1=new Item();
		m.addAttribute("item", item1);
	
		List<Item> itemList =itemdao.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categorydao.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=false;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping("/itemCatalog")
	public String displayAllItem(Model m)
	{
		List<Item> itemList = itemdao.listItems();
		m.addAttribute("itemList", itemList);
		
		return "ItemCatalog";
	}
	
	@RequestMapping("/itemDisplay/{itemid}")
	public String displaySingleProduct(@PathVariable("itemid")int itemid,Model m)
	{
		Item item = (Item)itemdao.getItem(itemid);
		m.addAttribute("itemInfo",item);
		m.addAttribute("categoryname", categorydao.getCategory(item.getCategoryid()).getCategoryname());
		return "ItemDisplay";
	}
	
	
	public LinkedHashMap<Integer,String> getCategoryList(List<Category> listCategory)
	{
		
		LinkedHashMap<Integer,String> categoryData=new LinkedHashMap<Integer,String>();
		
		int count=0;
		while(count<listCategory.size())
		{
			categoryData.put(listCategory.get(count).getCategoryid(),listCategory.get(count).getCategoryname());
			count++;
		}
		
		return categoryData;
	}
	
	
	
	
}
