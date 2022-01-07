
  package com.gr.comcome.category.controller;
  
  import java.util.List;
  
  import org.springframework.stereotype.Controller; import
  org.springframework.ui.Model; import
  org.springframework.web.bind.annotation.RequestMapping;
  
  import com.gr.comcome.category.brand.model.BrandService; import
  com.gr.comcome.category.brand.model.BrandVO; import
  com.gr.comcome.category.model.CategoryService; import
  com.gr.comcome.category.model.CategoryVO; import
  com.gr.comcome.category.screensize.model.ScreenSizeService; import
  com.gr.comcome.category.screensize.model.ScreenSizeVO;
  
  import lombok.extern.slf4j.Slf4j;
  
  @Slf4j
  
  @Controller public class CategoryController { private final CategoryService
  categoryService; private final BrandService brandsService; private final
  ScreenSizeService screenSizeService;
  
  public CategoryController(CategoryService categoryService, BrandService
  brandsService, ScreenSizeService screenSizeService) { this.categoryService =
  categoryService; this.brandsService = brandsService; this.screenSizeService =
  screenSizeService; }
  
  @RequestMapping("/category") public String category(Model model) {
  log.info("Enter category()");
  
  List<CategoryVO> categoryList = categoryService.selectAllCategory();
  List<BrandVO> brandList = brandsService.selectAllBrand(); List<ScreenSizeVO>
  screenSizeList = screenSizeService.selectAllScreenSize();
  
  model.addAttribute("categoryList", categoryList);
  model.addAttribute("brandList", brandList);
  model.addAttribute("screenSizeList", screenSizeList);
  
  // for(BrandVO vo : brandList) // log.info("brandVo: {}", vo); // //
  for(ScreenSizeVO vo : screenSizeList) // log.info("screenSizeVo: {}", vo);
  
  return "/include/category"; } }
 