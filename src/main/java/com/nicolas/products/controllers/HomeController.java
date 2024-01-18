package com.nicolas.products.controllers;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nicolas.products.models.Category;
import com.nicolas.products.models.Product;
import com.nicolas.products.services.APIService;

import jakarta.validation.Valid;


@Controller
public class HomeController {
	private APIService api;
	
	public HomeController(APIService api) {
		this.api = api;
	}
	
	//Mapping
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/products")
	public String productIndex(Model model) {
		List<Product> products = api.findAllProducts();
		model.addAttribute("products", products);
		return "indexProducts";
	}
	
	@RequestMapping("/categories")
	public String categoryIndex(Model model) {
		List<Category> categories = api.findAllCategories();
		model.addAttribute("categories", categories);
		return "indexCategories";
	}
	
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "newProduct";
	}
	
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "newCategory";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String createProduct(
			@Valid @ModelAttribute("product") Product product,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newProduct";
		} else {
			api.createProduct(product);
			return "redirect:/products";
		}
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String createCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newCategory";
		} else {
			api.createCategory(category);
			return "redirect:/categories";
		}
	}
	
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product product = api.findOneProduct(id);
		List<Category> categories = api.findAllCategoriesNotAssoc(id);
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "showProduct";
	}	
	
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category category = api.findOneCategory(id);
		List<Product> products = api.findAllProductsNotAssoc(id);
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		return "showCategory";
	}
	
	@RequestMapping(value="/addProductToCategory/{productId}", method=RequestMethod.POST)
	public String addProductToCategory(
			@PathVariable("productId") Long productId,
			@RequestParam(name="categoryId") Long categoryId) {
		api.addProductToCategory(productId, categoryId);
		return "redirect:/products/" + productId;
	}
	
	@RequestMapping(value="/addCategoryToProduct/{categoryId}", method=RequestMethod.POST)
	public String addCategoryToProduct(
			@PathVariable("categoryId") Long categoryId,
			@RequestParam(name="productId") Long productId) {
		api.addProductToCategory(productId, categoryId);
		return "redirect:/categories/" + categoryId;
	}

}
