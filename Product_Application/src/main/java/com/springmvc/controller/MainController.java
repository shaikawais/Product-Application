package com.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.springmvc.dao.ProductDao;
import com.springmvc.entities.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model model) {
		List<Product> products = productDao.getAllProduct();
		model.addAttribute("products", products);
		return "index";
	}

	@RequestMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add Product");
		return "addProductForm";
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public RedirectView handleForm(@ModelAttribute Product product, HttpServletRequest request) {
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/");
		productDao.createProduct(product);
		return view;
	}

	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int id, HttpServletRequest request) {
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/");
		productDao.deleteProduct(id);
		return view;
	}

	@RequestMapping("/update/{productId}")
	public String updateProduct(@PathVariable("productId") int id, Model model) {
		Product product = this.productDao.getProduct(id);
		model.addAttribute("p", product);
		return "updateForm";
	}
}
