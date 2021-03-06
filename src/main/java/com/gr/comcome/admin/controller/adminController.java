package com.gr.comcome.admin.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gr.comcome.account.model.AccountService;
import com.gr.comcome.account.model.AccountVO;
import com.gr.comcome.admin.model.AdminService;
import com.gr.comcome.admin.model.AdminVO;
import com.gr.comcome.admin.model.NoticeVO;
import com.gr.comcome.category.model.CategoryService;
import com.gr.comcome.category.model.CategoryVO;
import com.gr.comcome.common.ConstUtil;
import com.gr.comcome.common.FileUploadUtil;
import com.gr.comcome.common.PaginationInfo;
import com.gr.comcome.common.SearchVO;
import com.gr.comcome.pd_order.model.PdOrderService;
import com.gr.comcome.pd_order.model.PdOrderVO;
import com.gr.comcome.saleproduct.model.SaleProductService;
import com.gr.comcome.saleproduct.model.SaleProductVO;
import com.gr.comcome.usedBoard.model.usedBoardService;
import com.gr.comcome.usedBoard.model.usedBoardVO;

@Controller
@RequestMapping("/admin")
public class adminController {

	private static final Logger logger = LoggerFactory.getLogger(adminController.class);

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private usedBoardService usedBoardService;
	
	@Autowired
	private FileUploadUtil fileUploadUtil;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SaleProductService saleProductService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PdOrderService pdorderService;
	

	
	  // http://localhost:9091/comcome/admin/member
	  
	  @RequestMapping("/member") public String member_get() { // 1
	  logger.info("?????? ?????? ??????");
	  
	  return "/admin/member"; 
	  }
	 

	  //????????????+???????????? 
	  // http://localhost:9091/comcome/admin/detailwithmain
	@RequestMapping("/detailwithmain")
	public String detailwithmain(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("??? ???????????? ???????????? no={}", boardNo);

		if (boardNo == 0) {
			model.addAttribute("msg", "????????? url?????????.");
			model.addAttribute("url", "/admin/boardwithmain");

			return "/common/message";
		}

		usedBoardVO vo = usedBoardService.selectByNo(boardNo);
		logger.info("???????????? ?????? vo={}", vo.toString());
		
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		

		model.addAttribute("vo", vo);
		model.addAttribute("content", contentbr);

		return "/adminview/boarddetailwithmain";
	}
	
	//??????????????? ?????? ????????? ?????? 
	//http://localhost:9091/comcome/admin/delete
	@RequestMapping("/delete")
	public String delete(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("??? ?????? ??????, no={}", boardNo);
		if (boardNo == 0) {
			model.addAttribute("msg", "????????? url?????????.");
			model.addAttribute("url", "/admin/boardwithmain");

			return "/common/message";
		}
		
		//boardNo??? ???????????? UseBoard ?????? 
		int result = usedBoardService.deleteBoardByNo(boardNo);
		logger.info("??? ?????? ??????, result={}", result);
		String msg ="?????? ????????? ??? ????????? ?????????????????????", url ="/admin/boardwithmain";
		if(result>0) {//????????? ???????????? 
			msg ="?????? ????????? ??? ????????? ?????????????????????";
		}else if(result<0) {
			msg ="??????????????? ??? ????????? ?????????????????????.";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "/common/message";
	}
	
	
	



	@GetMapping("/popupregi")
	public String popupregi() {
		return "/admin/popupregi";
	}

	// localhost:9091/comcome/admin/logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("???????????? ??????");

		// session.invalidate();
		session.removeAttribute("emailadmin");
		session.removeAttribute("adminNo");

		return "redirect:/";
	}

	

	//???????????? + ?????? ?????? ?????? ?????? 
	// localhost:9091/comcome/admin/popup-regi-with-main
	@RequestMapping("/popup-regi-with-main")
	public String popupRegiWithMain() {

		return "/adminview/popupwithmain";
	}

	// localhost:9091/comcome/admin/popup-regi
	@RequestMapping("/popup-regi")
	public String popupRegi_post(@RequestParam String title,
			@RequestParam String content, @RequestParam String email, Model model) {

		
		logger.info("?????? ?????? ??????, title={}, content={}, email={}", title, content, email);

		int result = adminService.insertNotice(email, title, content);
		String msg = "?????? ????????? ?????????????????????", url = "/admin/popup-regi-with-main";
		if (result == adminService.INSERT_OK) {
			msg = "?????? ????????? ?????????????????????";
			url = "/admin/allmemberview";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/common/message";

	}

	// localhost:9091/comcome/admin/popup
	@GetMapping("/popup")
	public String popup_get(Model model) {
		NoticeVO vo = adminService.selectRecentNotice();
		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");
		String title = vo.getTitle();
		String titlebr = title.replaceAll("\n", "<br />");

		model.addAttribute("content", contentbr);
		model.addAttribute("title", titlebr);
		model.addAttribute("vo", vo);

		return "/admin/popup3";
	}

	// localhost:9091/comcome/admin/login
	@GetMapping("/login")
	public String adminlogin() {
		return "/admin/adminlogin";
	}

	//???????????? + ????????? ?????? 
	// localhost:9091/comcome/admin/login-with-main
	@GetMapping("/login-with-main")
	public String adminwithmain() {
		return "/adminview/adminloginwithmain";
	}

	//????????? ????????? 
	// localhost:9091/comcome/admin/login
	@PostMapping("/login")
	public String adminlogin_post(@RequestParam(required = false) String email,
			@RequestParam(required = false) String password, @RequestParam(required = false) String chkSave,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (email == null || password == null || email == "" || password == "") {
			model.addAttribute("msg", "?????????/??????????????? ??????????????????");
			model.addAttribute("url", "/admin/login-with-main");
			return "common/message";
		}

		logger.info("????????? ??????, ???????????? " + "email={}, password={}, chkSave={}", email, password, chkSave);

		

		logger.info("????????? ?????? ?????? ");
		
		int result = adminService.loginCheck(email, password);
	
		String msg = "????????? ?????? ??????!", url = "/admin/login-with-main";
		if (result == adminService.LOGIN_OK) {
			AdminVO adminVO = adminService.selectByEmail(email);
			logger.info("adminVO={}", adminVO);
			HttpSession session = request.getSession();
			session.setAttribute("emailadmin", adminVO.getEmail());
			session.setAttribute("adminNo", adminVO.getAdminNo());

			// [2] ????????? ?????? - ????????? ???????????? ????????? ??????
			Cookie ck = new Cookie("ck_email", adminVO.getEmail());
			ck.setPath("/");

			if (chkSave != null) {
				ck.setMaxAge(1000 * 24 * 60 * 60);
				response.addCookie(ck);
			} else {
				ck.setMaxAge(0); // ?????? ??????
				response.addCookie(ck);
			}

			msg = "???????????? ???????????????";
			url = "/admin/allmemberview";

		} else if (result == adminService.DISAGREE_PWD) {
			msg = "??????????????? ???????????? ????????????.";
		} else if (result == adminService.EMAIL_NONE) {
			msg = "?????? ???????????? ???????????? ????????????";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/common/message";

	}
	
	
    //?????? ?????? ?????? get, post
	//localhost:9091/comcome/admin/allmemberview
		@RequestMapping("/allmemberview")
		public String main_Post(@ModelAttribute SearchVO searchVO, Model model) {
			// 1
			logger.info("?????? ?????? ??????, searchVo={}", searchVO);
			// 2
			PaginationInfo pagingInfo = new PaginationInfo();
			pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			pagingInfo.setCurrentPage(searchVO.getCurrentPage());

			// 3
			searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
			logger.info("??? ?????? ??? searchVo={}", searchVO);

			List<AccountVO> list = adminService.selectAllMember(searchVO);
			logger.info("???????????? ?????? list.size={}", list.size());
			for (AccountVO vo : list) {
				logger.info(vo.toString());
			}
			// 4
			int totalRecord = adminService.selectTotalRecord(searchVO);
			pagingInfo.setTotalRecord(totalRecord);

			model.addAttribute("list", list);
			model.addAttribute("pagingInfo", pagingInfo);

			return "/adminview/allmember";
		}
		
		
		
		//???????????? + ?????? ????????? ?????? 
		//localhost:9091/comcome/admin/boardwithmain
		@RequestMapping(value = "/boardwithmain", method= {RequestMethod.GET, RequestMethod.POST})
		public String boardwithmain(@ModelAttribute SearchVO searchVO, Model model) {
			logger.info("?????? ????????? ?????? + ????????? ?????? ??????");
			PaginationInfo pagingInfo = new PaginationInfo();
			pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			pagingInfo.setCurrentPage(searchVO.getCurrentPage());

			// 3
			searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
			logger.info("??? ?????? ??? searchVo={}", searchVO);
			
			List<usedBoardVO> list = usedBoardService.selectAll(searchVO);
			logger.info("???????????? ?????? list.size={}", list.size());
			for (usedBoardVO vo : list) {
				logger.info(vo.toString());
			}
			
			
			  for(int i=0; i< list.size(); i++) { //?????? '??????' ????????? 10?????? ?????????
			  if(list.get(i).getContent().length()>=10) {
			  list.get(i).setContent(list.get(i).getContent().substring(0, 9)); } }
			 
			
			
			
			int totalRecord = usedBoardService.selectTotalRecord(searchVO);
			pagingInfo.setTotalRecord(totalRecord);

			model.addAttribute("list", list);
			model.addAttribute("pagingInfo", pagingInfo);
			
			return "/adminview/boardwithmain";
		}
		
		
		//???????????? + ?????? ????????? ??? ?????? ?????? ?????? 
		//localhost:9091/comcome/admin/board-update
		@RequestMapping("/board-update")
		public String boardUpdate(@RequestParam(defaultValue = "0") int boardNo, Model model) {
			logger.info("?????? ?????? ????????? ??? ?????? ?????? ??????");

			if (boardNo == 0) {
				model.addAttribute("msg", "????????? url?????????.");
				model.addAttribute("url", "/admin/boardwithmain");

				return "/common/message";
			}
			
			model.addAttribute("boardNo", boardNo);
			return "/adminview/boardupdatewithmain";
		}
		
		@PostMapping("/updateboard")
		public String updateboard(@ModelAttribute usedBoardVO usedBoardVO, 
				HttpServletRequest request) {
			logger.info("??????????????? ????????? ??????, ???????????? vo={}", usedBoardVO);
			
			//?????? ????????? ??????
			String fileName="", originalFileName="";
			int fileSize=0;
			
			try {
				List<Map<String, Object>> fileList 
					= fileUploadUtil.fileUpload(request, "usedboard");//fileupload ????????? "/testboard"(????????????)
				for(int i=0;i<fileList.size();i++) {
					 Map<String, Object> map=fileList.get(i);
					 
					 fileName=(String) map.get("fileName");
					 originalFileName=(String) map.get("originFileName");
					 fileSize=(int)(long) map.get("fileSize");				 
				}
				
				logger.info("?????? ????????? ??????, fileName={}", fileName);
				
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			
			usedBoardVO.setFileName(fileName);
			usedBoardVO.setOriginalFileName(originalFileName);
			usedBoardVO.setFileSize(fileSize);
			logger.info("usedBoardVO={}",usedBoardVO);
			int cnt=usedBoardService.updateBoardByAdmin(usedBoardVO);
			logger.info("????????? ??????, cnt={}", cnt);
			
				
			
			//4
			return "redirect:/admin/boardwithmain";
			
		}
		
		//???????????? + ?????? ?????? ?????? ??????
		//localhost:9091/comcome/admin/insert-sale-product
		@GetMapping("/insert-sale-product")
		public String insertSaleProduct(Model model) {
			logger.info("?????? ?????? ?????? ?????? ??????");
			
			List<CategoryVO> list = categoryService.selectAllCategory();
			model.addAttribute("list", list);
			
			
			
			return "/adminview/addsaleproductwithmain";
			
			
		}
		
		//?????? ?????? ?????? ??????
		//localhost:9091/comcome/admin/addsaleproduct
		@PostMapping("/addsaleproduct")
		public String addsaleproduct(@ModelAttribute SaleProductVO saleProductVO, 
				Model model,HttpServletRequest request) {
			logger.info("?????? ?????? ?????? ??????, ???????????? vo={}", saleProductVO.toString());
		    //?????? ??????
			//?????? ????????? ??????
			
			List<String> fileName = new ArrayList<String>();
			List<String> originalFileName = new ArrayList<String>();
			
			try {
				List<Map<String, Object>> fileList 
					= fileUploadUtil.fileUpload(request, "testboard");//fileupload ????????? "/testboard"(????????????)
				for(int i=0;i<fileList.size();i++) {
					 Map<String, Object> map=fileList.get(i);
					 
					 fileName.add((String) map.get("fileName"));
					 originalFileName.add((String) map.get("originFileName"));
					
					 			 
				}
				
				logger.info("?????? ????????? ??????, fileName={}, originalFileName={}", fileName.get(0), originalFileName.get(0));
				
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			saleProductVO.setThumbNailImg(fileName.get(0));
			saleProductVO.setContentImg(fileName.get(1));
			logger.info("saleProductVO={}",saleProductVO);
			
			
			//?????? ?????? 
			int cnt= saleProductService.insertProduct(saleProductVO);
			logger.info("?????? ?????? ?????? ??????, cnt={}", cnt);
			String msg ="?????? ?????? ????????? ?????????????????????", url ="/admin/insert-sale-product";
			if(cnt>0) {
				msg ="?????? ?????? ????????? ?????????????????????";
				url ="/admin/allsaleproduct";
			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
				
			
			//4
			return "/common/message";
			
		}
		//?????? ?????? ?????? +????????????
		//localhost:9091/comcome/admin/allsaleproduct
		@RequestMapping("/allsaleproduct")
		public String allsaleproduct(@ModelAttribute SearchVO searchVO, Model model) {
			// 1
			logger.info("?????? ?????? ?????? ??????, searchVo={}", searchVO);
			// 2
			PaginationInfo pagingInfo = new PaginationInfo();
			pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			pagingInfo.setCurrentPage(searchVO.getCurrentPage());

			// 3
			searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
			logger.info("??? ?????? ??? searchVo={}", searchVO);

			List<SaleProductVO> list = saleProductService.selectAllProduct(searchVO);
		
			logger.info("???????????? ?????? list.size={}", list.size());
			for (SaleProductVO vo : list) {
				logger.info(vo.toString());
			}
			// 4
			int totalRecord = saleProductService.selectTotalRecord(searchVO);
			pagingInfo.setTotalRecord(totalRecord);

			model.addAttribute("list", list);
			model.addAttribute("pagingInfo", pagingInfo);

			return "/adminview/allsaleproductwithmain";
		}
		
		
		//?????? ?????? ?????? +????????????
		//localhost:9091/comcome/admin/detail-sale-product
		@RequestMapping("/detail-sale-product")
		public String detailsaleproduct(@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
			logger.info("?????? ?????? ???????????? ???????????? no={}", saleProductNo);

			if (saleProductNo == 0) {
				model.addAttribute("msg", "????????? url?????????.");
				model.addAttribute("url", "/admin/allsaleproduct");

				return "/common/message";
			}

			SaleProductVO vo = saleProductService.selectByNo(saleProductNo);
			
			logger.info("???????????? ?????? vo={}", vo.toString());
			
			

			model.addAttribute("vo", vo);
			
			return "/adminview/saleproductdetailwithmain";
		}
		
		//?????? ?????? ?????? ?????? +????????????
		//localhost:9091/comcome/admin/sale-product-update
		@RequestMapping("/sale-product-update")
		public String saleProductUpdate( @RequestParam(defaultValue = "0") int saleProductNo, Model model) {
			logger.info("?????? ?????? ?????? ?????? ??????");

			if (saleProductNo == 0) {
				model.addAttribute("msg", "????????? url?????????.");
				model.addAttribute("url", "/admin/allsaleproduct");

				return "/common/message";
			}
			
			
			SaleProductVO vo = saleProductService.selectByNo(saleProductNo);
			List<CategoryVO> list = categoryService.selectAllCategory();
			model.addAttribute("list", list);
			model.addAttribute("vo", vo);
			model.addAttribute("saleProductNo", saleProductNo);
			return "/adminview/updatesaleproductwithmain";
		}
		
		//?????? ?????? ?????? Post
		//localhost:9091/comcome/admin/post-sale-product
		@PostMapping("/post-sale-product")
		public String updateproduct_post(@ModelAttribute SaleProductVO saleProductVO, 
				Model model,HttpServletRequest request) {
			logger.info("?????? ?????? ????????? , ???????????? vo={}", saleProductVO);
			
			//?????? ??????
			List<String> fileName = new ArrayList<String>();
			List<String> originalFileName = new ArrayList<String>();
			
			try {
				List<Map<String, Object>> fileList 
					= fileUploadUtil.fileUpload(request, "testboard");//fileupload ????????? "/testboard"(????????????)
				for(int i=0;i<fileList.size();i++) {
					 Map<String, Object> map=fileList.get(i);
					 
					 fileName.add((String) map.get("fileName"));
					 originalFileName.add((String) map.get("originFileName"));
					
					 			 
				}
				
				logger.info("?????? ????????? ??????, fileName={}, originalFileName={}", fileName.get(0), originalFileName.get(0));
				
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			saleProductVO.setThumbNailImg(fileName.get(0));
			saleProductVO.setContentImg(fileName.get(1));
			
			int cnt = saleProductService.updateProduct(saleProductVO);
			logger.info("????????? ??????, cnt={}", cnt);
			String msg ="?????? ?????? ????????? ?????????????????????", url ="/admin/allsaleproduct";
			if(cnt>0) {
				msg ="?????? ?????? ????????? ?????????????????????";
				
			}
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
				
			
			//4
			return "/common/message";
		}
		
		//?????? ?????? ??????
		//localhost:9091/comcome/admin/delete-sale-product
		@RequestMapping("/delete-sale-product")
		public String deleteSaleProduct(@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
			logger.info("??? ?????? ??????, no={}", saleProductNo);
			if (saleProductNo == 0) {
				model.addAttribute("msg", "????????? url?????????.");
				model.addAttribute("url", "/admin/allsaleproduct");

				return "/common/message";
			}
			
			int result = saleProductService.deleteProductByNo(saleProductNo);
			
			logger.info("?????? ?????? ??????, result={}", result);
			String msg ="?????? ?????? ????????? ?????????????????????", url ="/admin/allsaleproduct";
			if(result>0) {//????????? ???????????? 
				msg ="?????? ?????? ??? ????????? ?????????????????????";
			}else if(result<0) {
				msg ="?????? ?????? ????????? ?????????????????????.";
			}
			
			model.addAttribute("msg",msg);
			model.addAttribute("url",url);
			return "/common/message";
		}
		
		
		//localhost:9091/comcome/admin/chart
		@RequestMapping("/chart") 
		public String chart_get(Model model) {
			logger.info("????????? ?????? ??????");
			List<Map<String,Integer>> listAccount = accountService.selectDaysRegister();
			List<Map<String,Integer>> listOrderSale = pdorderService.selectDaysSales();
			List<Map<String,Integer>> listOrderSaleCount = pdorderService.selectDaysSalesCount();
			List<Map<String,Integer>> listboardCount = usedBoardService.selectDaysBoardCount();
			
			
			int size = listAccount.size();
			
			String[] accountRegdate = new String[size];
			int[] accountCount =new int[size];
			int[] orderSalesSum =new int[size];
			int[] orderSalesCount =new int[size];
			int[] usedBoardCount =new int[size];
			
			
			int i=0;
			
			
			for(Map<String, Integer> map : listAccount) {				
				String str = String.valueOf(map.get("REGDATE"));
				if(i==6) {
					model.addAttribute("date", str);
				}
				int count = Integer.parseInt(String.valueOf(map.get("COUNT")));
				String month = str.substring(str.indexOf("-")+1,str.indexOf("-")+3);
				String day = str.substring(str.lastIndexOf("-")+1,str.lastIndexOf("-")+3);
				String regdate=month+"/"+day;
				
				accountRegdate[i]=regdate;
				accountCount[i]=count;
				i++;
			}
			
			i=0;
			for(Map<String, Integer> map : listOrderSale) {				
				int sum = Integer.parseInt(String.valueOf(map.get("TOTAL")));
				orderSalesSum[i]=sum;
				i++;
			}
			i=0;
			for(Map<String, Integer> map : listOrderSaleCount) {				
				int sum = Integer.parseInt(String.valueOf(map.get("COUNT")));
				orderSalesCount[i]=sum;
				i++;
			}
			i=0;
			for(Map<String, Integer> map : listboardCount) {				
				int sum = Integer.parseInt(String.valueOf(map.get("COUNT")));
				usedBoardCount[i]=sum;
				i++;
			}
			
			int sum =(int) (orderSalesSum[6]*0.05) ;
			
			model.addAttribute("a", accountRegdate);
			model.addAttribute("b", accountCount);
			model.addAttribute("c", orderSalesSum);
			model.addAttribute("d", orderSalesCount);
			model.addAttribute("e", usedBoardCount);
			model.addAttribute("sum", sum);
			
			  
			return "/adminview/chartwithmain"; 
		}
		
	
		@RequestMapping("/order") 
		public String order_get(@ModelAttribute SearchVO searchVO,Model model) {
			logger.info("???????????? ?????? ??????");
			
			// 2
			PaginationInfo pagingInfo = new PaginationInfo();
			pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
			pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			pagingInfo.setCurrentPage(searchVO.getCurrentPage());

			// 3
			searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
			searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
			logger.info("??? ?????? ??? searchVo={}", searchVO);

			List<PdOrderVO> list = pdorderService.selectAllOrder(searchVO);
			logger.info("???????????? ?????? list.size={}", list.size());
			for (PdOrderVO vo : list) {
				logger.info(vo.toString());
			}
			// 4
			int totalRecord = adminService.selectTotalRecord(searchVO);
			pagingInfo.setTotalRecord(totalRecord);

			model.addAttribute("list", list);
			model.addAttribute("pagingInfo", pagingInfo);
			return "/adminview/orderwithmain"; 
		}
		
		@RequestMapping("/excelDownload")
	    public String excelDownload(HttpServletResponse response) throws IOException {
	        
	        Workbook wb = new XSSFWorkbook();
	        Sheet sheet = wb.createSheet("????????? ??????");
	        Row row = null;
	        Cell cell = null;
	        int rowNum = 0;
	        
	        
	        // Header
	        row = sheet.createRow(rowNum++);
	        cell = row.createCell(0);
	        cell.setCellValue("????????????");
	        cell = row.createCell(1);
	        cell.setCellValue("????????????");
	        cell = row.createCell(2);
	        cell.setCellValue("??????????????????");
	        cell = row.createCell(3);
	        cell.setCellValue("?????????");
	        cell = row.createCell(4);
	        cell.setCellValue("??????");

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        DecimalFormat df=new DecimalFormat("#,###");
	        
	        List<PdOrderVO> list = pdorderService.selectAllData();
	        for (PdOrderVO vo : list) {
				logger.info(vo.toString());
				row = sheet.createRow(rowNum++);
	            cell = row.createCell(0);
	            cell.setCellValue(vo.getPdOrderNo());
	            cell = row.createCell(1);
	            cell.setCellValue(vo.getAccountNo());
	            cell = row.createCell(2);
	            cell.setCellValue(vo.getSaleProductNo());
	            cell = row.createCell(3);
	            cell.setCellValue(sdf.format(vo.getOrderDate()));
	            cell = row.createCell(4);
	            cell.setCellValue(df.format(vo.getPrice()));
			}

	        // ????????? ????????? ????????? ??????
	        response.setContentType("ms-vnd/excel");
	        response.setHeader("Content-Disposition", "attachment;filename=orderData.xlsx");

	        // Excel File Output
	        wb.write(response.getOutputStream());
	        wb.close();
	        
	        return "/adminview/orderwithmain"; 
	    }
	

}
