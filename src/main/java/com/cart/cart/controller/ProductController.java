package com.cart.cart.controller;

import com.cart.cart.model.Cart;
import com.cart.cart.model.Item;
import com.cart.cart.model.Plus;
import com.cart.cart.model.Product;
import com.cart.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public Cart setUpCartForm() {
        return new Cart();
    }

    @GetMapping("/buy-product/{id}")
    public ModelAndView buyProduct(@PathVariable Long id, @ModelAttribute("cart") Cart cart) {
        Product product = productService.findById(id);
        Item item = new Item(product,1);
        cart.doCart(id,item);
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("plus", new Plus());
        return modelAndView;
    }

    @GetMapping("/delete-item/{id}")
    public ModelAndView deleteItem(@PathVariable Long id,@ModelAttribute("cart") Cart cart) {
        cart.removeToCart(id);
        return new ModelAndView("/cart");
    }
    @GetMapping("/them-item/{id}")
    public ModelAndView themItem(@PathVariable Long id,@ModelAttribute("cart") Cart cart) {
        cart.change(id,1);
        return new ModelAndView("/cart");
    }
    @GetMapping("/bot-item/{id}")
    public ModelAndView botItem(@PathVariable Long id,@ModelAttribute("cart") Cart cart) {
        cart.change(id,-1);
        return new ModelAndView("/cart");
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveBook(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New Product created successfully");
        return modelAndView;

    }


    @GetMapping("/")
    public ModelAndView list(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "6") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "target", required = false, defaultValue = "id") String target
    ) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by(target).ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by(target).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        Page<Product> products;
        products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView updateBook(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Book updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-product")
    public String deleteBook(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:";
    }
}

