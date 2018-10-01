package com.frontline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.frontline.dao.CartItemDAO;
import com.frontline.dao.OrderDAO;
import com.frontline.model.CartItemPurchase;
import com.frontline.model.OrderDetail;

public class OrderController {

	@Autowired
	CartItemDAO cartitemdao;
	
	@Autowired
	OrderDAO orderdao;
	
	@RequestMapping(value="/payment")
	public String showPaymentPage()
	{
		return "Pay";
	}
	
	@RequestMapping(value="/paymentprocess",method=RequestMethod.POST)
	public String paymentProcess(@RequestParam("pmode")String pmode,HttpSession session,Model m)
	{
		String username=(String)session.getAttribute("username");
		
		List<CartItemPurchase> listCartItems = cartitemdao.retrieveCartItemsPurchase(username);
		m.addAttribute("cartItemsPurchase",listCartItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(listCartItems));
		
		OrderDetail orderdetail = new OrderDetail();
		orderdetail.setUsername(username);
		orderdetail.setOrderdate(new java.util.Date());
		orderdetail.setPmode(pmode);
		orderdetail.setTotalshoppingamount((int)this.calcGrandTotalValue(listCartItems));
		
		orderdao.insertOrderDetail(orderdetail);
		
		System.out.println("OrderDetail have been saved");
		
		return "ThankYou";
	}
	
	public long calcGrandTotalValue(List<CartItemPurchase> listCartItems)
	{
		int count=0;
		long grandtotalprice = 0;
		while(count<listCartItems.size())
		{
			grandtotalprice+=(listCartItems.get(count).getQuantity()*listCartItems.get(count).getPrice());
			count++;
		}
		
		return grandtotalprice;
	}

	
}
