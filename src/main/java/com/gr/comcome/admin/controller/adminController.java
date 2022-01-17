package com.gr.comcome.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


	// http://localhost:9091/comcome/admin/member

	@RequestMapping("/member") public String member_get() { // 1
		logger.info("회원 목록 화면");

		return "/admin/member"; 
	}


	//사이드바+상세보기 
	// http://localhost:9091/comcome/admin/detailwithmain
	@RequestMapping("/detailwithmain")
	public String detailwithmain(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("글 상세보기 파라미터 no={}", boardNo);

		if (boardNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/boardwithmain");

			return "/common/message";
		}

		usedBoardVO vo = usedBoardService.selectByNo(boardNo);
		logger.info("상세보기 결과 vo={}", vo.toString());

		String content = vo.getContent();
		String contentbr = content.replaceAll("\n", "<br />");


		model.addAttribute("vo", vo);
		model.addAttribute("content", contentbr);

		return "/adminview/boarddetailwithmain";
	}

	//중고게시판 상세 레코드 삭제 
	//http://localhost:9091/comcome/admin/delete
	@RequestMapping("/delete")
	public String delete(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("글 삭제 처리, no={}", boardNo);
		if (boardNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/boardwithmain");

			return "/common/message";
		}

		//boardNo에 해당하는 UseBoard 삭제 
		int result = usedBoardService.deleteBoardByNo(boardNo);
		logger.info("글 삭제 처리, result={}", result);
		String msg ="중고 게시판 글 삭제에 실패하였습니다", url ="/admin/boardwithmain";
		if(result>0) {//삭제에 성공하면 
			msg ="중고 게시판 글 삭제에 성공하였습니다";
		}else if(result<0) {
			msg ="중고게시판 글 삭제에 실패하였습니다.";
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
		logger.info("로그아웃 처리");

		// session.invalidate();
		session.removeAttribute("emailadmin");
		session.removeAttribute("adminNo");

		return "redirect:/";
	}



	//사이드바 + 팝업 공지 등록 화면 
	// localhost:9091/comcome/admin/popup-regi-with-main
	@RequestMapping("/popup-regi-with-main")
	public String popupRegiWithMain() {

		return "/adminview/popupwithmain";
	}

	// localhost:9091/comcome/admin/popup-regi
	@RequestMapping("/popup-regi")
	public String popupRegi_post(@RequestParam String title,
			@RequestParam String content, @RequestParam String email, Model model) {


		logger.info("팝업 등록 처리, title={}, content={}, email={}", title, content, email);

		int result = adminService.insertNotice(email, title, content);
		String msg = "공지 등록에 실패하였습니다", url = "/admin/popup-regi-with-main";
		if (result == adminService.INSERT_OK) {
			msg = "공지 등록이 완료되었습니다";
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

	//사이드바 + 로그인 화면 
	// localhost:9091/comcome/admin/login-with-main
	@GetMapping("/login-with-main")
	public String adminwithmain() {
		return "/adminview/adminloginwithmain";
	}

	//관리자 로그인 
	// localhost:9091/comcome/admin/login
	@PostMapping("/login")
	public String adminlogin_post(@RequestParam(required = false) String email,
			@RequestParam(required = false) String password, @RequestParam(required = false) String chkSave,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		if (email == null || password == null || email == "" || password == "") {
			model.addAttribute("msg", "이메일/비밀번호를 입력해주세요");
			model.addAttribute("url", "/admin/login-with-main");
			return "common/message";
		}

		logger.info("로그인 처리, 파라미터 " + "email={}, password={}, chkSave={}", email, password, chkSave);



		logger.info("관리자 계정 처리 ");

		int result = adminService.loginCheck(email, password);

		String msg = "로그인 처리 실패!", url = "/admin/login-with-main";
		if (result == adminService.LOGIN_OK) {
			AdminVO adminVO = adminService.selectByEmail(email);
			logger.info("adminVO={}", adminVO);
			HttpSession session = request.getSession();
			session.setAttribute("emailadmin", adminVO.getEmail());
			session.setAttribute("adminNo", adminVO.getAdminNo());

			// [2] 쿠키에 저장 - 아이디 저장하기 체크된 경우
			Cookie ck = new Cookie("ck_email", adminVO.getEmail());
			ck.setPath("/");

			if (chkSave != null) {
				ck.setMaxAge(1000 * 24 * 60 * 60);
				response.addCookie(ck);
			} else {
				ck.setMaxAge(0); // 쿠키 제거
				response.addCookie(ck);
			}

			msg = "관리자님 환영합니다";
			url = "/admin/allmemberview";

		} else if (result == adminService.DISAGREE_PWD) {
			msg = "비밀번호가 일치하지 않습니다.";
		} else if (result == adminService.EMAIL_NONE) {
			msg = "해당 이메일이 존재하지 않습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/common/message";

	}


	//회원 목록 화면 get, post
	//localhost:9091/comcome/admin/allmemberview
	@RequestMapping("/allmemberview")
	public String main_Post(@ModelAttribute SearchVO searchVO, Model model) {
		// 1
		logger.info("회원 목록 화면, searchVo={}", searchVO);
		// 2
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVO.getCurrentPage());

		// 3
		searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("값 셋팅 후 searchVo={}", searchVO);

		List<AccountVO> list = adminService.selectAllMember(searchVO);
		logger.info("전체조회 결과 list.size={}", list.size());
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



	//사이드바 + 중고 게시판 관리 
	//localhost:9091/comcome/admin/boardwithmain
	@RequestMapping(value = "/boardwithmain", method= {RequestMethod.GET, RequestMethod.POST})
	public String boardwithmain(@ModelAttribute SearchVO searchVO, Model model) {
		logger.info("중고 게시판 관리 + 사이드 화면 처리");
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVO.getCurrentPage());

		// 3
		searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("값 셋팅 후 searchVo={}", searchVO);

		List<usedBoardVO> list = usedBoardService.selectAll(searchVO);
		logger.info("전체조회 결과 list.size={}", list.size());
		for (usedBoardVO vo : list) {
			logger.info(vo.toString());
		}


		for(int i=0; i< list.size(); i++) { //만약 '내용' 컬럼이 10자가 넘으면
			if(list.get(i).getContent().length()>=10) {
				list.get(i).setContent(list.get(i).getContent().substring(0, 9)); } }




		int totalRecord = usedBoardService.selectTotalRecord(searchVO);
		pagingInfo.setTotalRecord(totalRecord);

		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);

		return "/adminview/boardwithmain";
	}


	//사이드바 + 중고 게시판 글 수정 화면 처리 
	//localhost:9091/comcome/admin/board-update
	@RequestMapping("/board-update")
	public String boardUpdate(@RequestParam(defaultValue = "0") int boardNo, Model model) {
		logger.info("중고 거래 게시판 글 수정 화면 처리");

		if (boardNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/boardwithmain");

			return "/common/message";
		}

		model.addAttribute("boardNo", boardNo);
		return "/adminview/boardupdatewithmain";
	}

	@PostMapping("/updateboard")
	public String updateboard(@ModelAttribute usedBoardVO usedBoardVO, 
			HttpServletRequest request) {
		logger.info("중고게시판 글쓰기 처리, 파라미터 vo={}", usedBoardVO);

		//파일 업로드 처리
		String fileName="", originalFileName="";
		int fileSize=0;

		try {
			List<Map<String, Object>> fileList 
			= fileUploadUtil.fileUpload(request, "testboard");//fileupload 경로는 "/testboard"(폴더이름)
			for(int i=0;i<fileList.size();i++) {
				Map<String, Object> map=fileList.get(i);

				fileName=(String) map.get("fileName");
				originalFileName=(String) map.get("originFileName");
				fileSize=(int)(long) map.get("fileSize");				 
			}

			logger.info("파일 업로드 성공, fileName={}", fileName);


		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		usedBoardVO.setFileName(fileName);
		usedBoardVO.setOriginalFileName(originalFileName);
		usedBoardVO.setFileSize(fileSize);
		logger.info("usedBoardVO={}",usedBoardVO);
		int cnt=usedBoardService.updateBoardByAdmin(usedBoardVO);
		logger.info("글쓰기 결과, cnt={}", cnt);



		//4
		return "redirect:/admin/boardwithmain";

	}

	//사이드바 + 특가 상품 등록 화면
	//localhost:9091/comcome/admin/insert-sale-product
	@GetMapping("/insert-sale-product")
	public String insertSaleProduct(Model model) {
		logger.info("특가 상품 등록 화면 처리");

		List<CategoryVO> list = categoryService.selectAllCategory();
		model.addAttribute("list", list);



		return "/adminview/addsaleproductwithmain";


	}

	//특가 상품 등록 처리
	//localhost:9091/comcome/admin/addsaleproduct
	@PostMapping("/addsaleproduct")
	public String addsaleproduct(@ModelAttribute SaleProductVO saleProductVO, 
			Model model,HttpServletRequest request) {
		logger.info("특가 상품 등록 처리, 파라미터 vo={}", saleProductVO.toString());
		//파일 등록
		//파일 업로드 처리

		List<String> fileName = new ArrayList<String>();
		List<String> originalFileName = new ArrayList<String>();

		try {
			List<Map<String, Object>> fileList 
			= fileUploadUtil.fileUpload(request, "testboard");//fileupload 경로는 "/testboard"(폴더이름)
			for(int i=0;i<fileList.size();i++) {
				Map<String, Object> map=fileList.get(i);

				fileName.add((String) map.get("fileName"));
				originalFileName.add((String) map.get("originFileName"));


			}

			logger.info("파일 업로드 성공, fileName={}, originalFileName={}", fileName.get(0), originalFileName.get(0));


		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		saleProductVO.setThumbNailImg(fileName.get(0));
		saleProductVO.setContentImg(fileName.get(1));
		logger.info("saleProductVO={}",saleProductVO);


		//상품 등록 
		int cnt= saleProductService.insertProduct(saleProductVO);
		logger.info("특가 상품 등록 결과, cnt={}", cnt);
		String msg ="특가 상품 등록에 실패하였습니다", url ="/admin/insert-sale-product";
		if(cnt>0) {
			msg ="특가 상품 등록에 성공하였습니다";
			url ="/admin/allsaleproduct";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);


		//4
		return "/common/message";

	}
	//특가 상품 목록 +사이드바
	//localhost:9091/comcome/admin/allsaleproduct
	@RequestMapping("/allsaleproduct")
	public String allsaleproduct(@ModelAttribute SearchVO searchVO, Model model) {
		// 1
		logger.info("특가 상품 목록 화면, searchVo={}", searchVO);
		// 2
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVO.getCurrentPage());

		// 3
		searchVO.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("값 셋팅 후 searchVo={}", searchVO);

		List<SaleProductVO> list = saleProductService.selectAllProduct(searchVO);

		logger.info("전체조회 결과 list.size={}", list.size());
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


	//특가 상품 상세 +사이드바
	//localhost:9091/comcome/admin/detail-sale-product
	@RequestMapping("/detail-sale-product")
	public String detailsaleproduct(@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
		logger.info("특가 상품 상세보기 파라미터 no={}", saleProductNo);

		if (saleProductNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/allsaleproduct");

			return "/common/message";
		}

		SaleProductVO vo = saleProductService.selectByNo(saleProductNo);

		logger.info("상세보기 결과 vo={}", vo.toString());



		model.addAttribute("vo", vo);

		return "/adminview/saleproductdetailwithmain";
	}

	//특가 상품 수정 화면 +사이드바
	//localhost:9091/comcome/admin/sale-product-update
	@RequestMapping("/sale-product-update")
	public String saleProductUpdate(@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
		logger.info("특가 상품 수정 화면 처리");

		if (saleProductNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/allsaleproduct");

			return "/common/message";
		}

		List<CategoryVO> list = categoryService.selectAllCategory();
		model.addAttribute("list", list);
		model.addAttribute("saleProductNo", saleProductNo);
		return "/adminview/updatesaleproductwithmain";
	}

	//특가 상품 수정 Post
	//localhost:9091/comcome/admin/post-sale-product
	@PostMapping("/post-sale-product")
	public String updateproduct_post(@ModelAttribute SaleProductVO saleProductVO, 
			Model model,HttpServletRequest request) {
		logger.info("상품 수정 포스트 , 파라미터 vo={}", saleProductVO);

		//파일 등록
		List<String> fileName = new ArrayList<String>();
		List<String> originalFileName = new ArrayList<String>();

		try {
			List<Map<String, Object>> fileList 
			= fileUploadUtil.fileUpload(request, "testboard");//fileupload 경로는 "/testboard"(폴더이름)
			for(int i=0;i<fileList.size();i++) {
				Map<String, Object> map=fileList.get(i);

				fileName.add((String) map.get("fileName"));
				originalFileName.add((String) map.get("originFileName"));


			}

			logger.info("파일 업로드 성공, fileName={}, originalFileName={}", fileName.get(0), originalFileName.get(0));


		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		saleProductVO.setThumbNailImg(fileName.get(0));
		saleProductVO.setContentImg(fileName.get(1));

		int cnt = saleProductService.updateProduct(saleProductVO);
		logger.info("글쓰기 결과, cnt={}", cnt);
		String msg ="특가 상품 수정에 실패하였습니다", url ="/admin/allsaleproduct";
		if(cnt>0) {
			msg ="특가 상품 수정에 성공하였습니다";

		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);


		//4
		return "/common/message";
	}

	//특가 상품 삭제
	//localhost:9091/comcome/admin/delete-sale-product
	@RequestMapping("/delete-sale-product")
	public String deleteSaleProduct(@RequestParam(defaultValue = "0") int saleProductNo, Model model) {
		logger.info("글 삭제 처리, no={}", saleProductNo);
		if (saleProductNo == 0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/admin/allsaleproduct");

			return "/common/message";
		}

		int result = saleProductService.deleteProductByNo(saleProductNo);

		logger.info("상품 삭제 처리, result={}", result);
		String msg ="특가 상품 삭제에 실패하였습니다", url ="/admin/allsaleproduct";
		if(result>0) {//삭제에 성공하면 
			msg ="특가 상품 글 삭제에 성공하였습니다";
		}else if(result<0) {
			msg ="특가 상품 삭제에 실패하였습니다.";
		}

		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		return "/common/message";
	}










}
