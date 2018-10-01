package com.frontline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontline.dao.CartItemDAO;
import com.frontline.dao.ItemDAO;
import com.frontline.model.CartItemPurchase;
import com.frontline.model.Item;


@Controller
public class CartItemController {

	@Autowired
	ItemDAO itemdao;
	
	@Autowired
	CartItemDAO cartitemdao;
	
	@RequestMapping("/cart")
	public String showCart(HttpSession session,Model m)
	{
		
		String username=(String)session.getAttribute("username");
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		return "CartItem";
	}
	
	public long calcGrandTotalValue(List<CartItemPurchase> listCartItems)
	{
		
		int count=0;
		long grandtotalprice=0;
		while(count<listCartItems.size())
		{
			grandtotalprice+=(listCartItems.get(count).getQuantity()*listCartItems.get(count).getPrice());
			count++;
		}
		
		return grandtotalprice;

	}
	

	@RequestMapping(value="/updateCartItemPurchase/{cartitempurchaseid}")
	public String updateCartItemPurchase(@PathVariable("cartitempurchaseid")int cartitempurchaseid,@RequestParam("qty")int quantity,Model m,HttpSession session)
	{
		CartItemPurchase cartitempurchase = cartitemdao.getCartItemPurchase(cartitempurchaseid);
		cartitempurchase.setQuantity(quantity);
		cartitemdao.updateCartItemPurchase(cartitempurchase);
		
		String username=(String)session.getAttribute("username");
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		
		return "CartItem";
	}
	
	@RequestMapping(value="/deleteCartItemPurchase/{cartitempurchaseid}")
	public String deleteCartItemPurchase(@PathVariable("cartitempurchaseid")int cartitempurchaseid,Model m,HttpSession session)
	{
		CartItemPurchase cartitempurchase = cartitemdao.getCartItemPurchase(cartitempurchaseid);
		cartitemdao.deleteCartItemPurchase(cartitempurchase);
		
		String username=(String)session.getAttribute("username");
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		
		return "CartItem";
	}

	@RequestMapping("/addToCartItem/{itemid}")
	public String addToCartItem(@PathVariable("itemid") int itemid,@RequestParam("qty") int quantity,HttpSession session,Model m)
	{
		
		Item item = itemdao.getItem(itemid);
		
		CartItemPurchase cartitempurchase = new CartItemPurchase();
		
		String username=(String)session.getAttribute("username");
		
		cartitempurchase.setCartitempurchaseid(item.getItemid());
		cartitempurchase.setItemname(item.getItemname());
		cartitempurchase.setPrice(item.getPrice());
		cartitempurchase.setQuantity(quantity);
		cartitempurchase.setPstatus("NP");
		cartitempurchase.setUsername(username);		
		cartitemdao.addCartItemPurchase(cartitempurchase);
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		
		return "CartItem";
	}
	
	@RequestMapping(value="/checkout")
	public String checkOut(HttpSession session,Model m)
	{

		String username=(String)session.getAttribute("username");
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		
		return "OrderDetails";
	}

	
	
}
