package com.study.controller;




import com.study.domain.Product;
import com.study.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
@Autowired
private IProductService productService;

@RequestMapping("/findAll.do")
    public ModelAndView findall(){

        ModelAndView ma = new ModelAndView();
        List<Product> productList = productService.findAll();
        ma.addObject("productList",productList);
        ma.setViewName("product-list");
        return ma;

    }
    @RequestMapping(value = "/save.do",method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.productSave(product);
        return "redirect:/product/findAll.do";


    }
}
