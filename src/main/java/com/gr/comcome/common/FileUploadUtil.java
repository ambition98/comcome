package com.gr.comcome.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUploadUtil {
	
	private static final Logger logger
		=LoggerFactory.getLogger(FileUploadUtil.class);
	
	//파일 업로드 처리
	public List<Map<String, Object>> fileUpload(HttpServletRequest request, 
			int pathFlag) throws IllegalStateException, IOException {
		
		MultipartHttpServletRequest multiRequest 
			= (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap=multiRequest.getFileMap();
		//List<MultipartFile> fileList=multiRequest.getFiles("upfile");
	
		List<Map<String, Object>> list = new ArrayList<>();
		
		Iterator<String> iter=fileMap.keySet().iterator();
		while(iter.hasNext()) {
			String key=iter.next();
			MultipartFile tempFile =fileMap.get(key);
			//=> 업로드된 파일을 임시파일 형태로 제공
			if(!tempFile.isEmpty()) {  //업로드 한 경우
				Map<String, Object> map = new HashMap<>();
				String originName=tempFile.getOriginalFilename();
				long fileSize=tempFile.getSize(); //업로드한 파일 크기
				logger.info("업로드 파일명={}, 파일 크기={}", originName, fileSize);
				
				//변경된 파일 이름 구하기
				String fileName=getUniqueFileName(originName);
				
				//업로드 처리
				String upPath = getUploadPath(pathFlag, request);
				File file = new File(upPath ,fileName);
				tempFile.transferTo(file);
				
				//map에 파일 정보 저장
				map.put("originalFileName", originName);
				map.put("fileName", fileName);
				map.put("fileSize", fileSize);
				
				list.add(map);
			}
		}//while
		
		return list;
	}
	
	public String getUploadPath(int pathFlag, HttpServletRequest request) {
		//업로드 경로 구하기
		String path="";
		
		if(ConstUtil.FILE_UPLOAD_TYPE.equals("test")) {
			if(pathFlag==ConstUtil.UPLOAD_FILE_FLAG) {  //자료실
				path=ConstUtil.FILE_UPLOAD_PATH_TEST;
			}else if(pathFlag==ConstUtil.UPLOAD_IMAGE_FLAG) { //상품등록
				path=ConstUtil.IMAGE_FILE_UPLOAD_PATH_TEST;
			}
			logger.info("type={}", ConstUtil.FILE_UPLOAD_TYPE);
		}else {  //deploy
			if(pathFlag==ConstUtil.UPLOAD_FILE_FLAG) {  //자료실
				path=ConstUtil.FILE_UPLOAD_PATH;
			}else if(pathFlag==ConstUtil.UPLOAD_IMAGE_FLAG) { //상품등록
				path=ConstUtil.IMAGE_FILE_UPLOAD_PATH;
			}
			logger.info("type={}", ConstUtil.FILE_UPLOAD_TYPE);
			
			//실제 물리적인 경로 구하기
			//application.getRealPath()
			//config.getServletContext().getRealPath()
			path=request.getSession().getServletContext().getRealPath(path);
		}
		
		logger.info("업로드 경로 : {}", path);
		
		return path;
	}
	
	public String getUniqueFileName(String fileName) {
		//파일명이 중복될 경우 파일이름 변경하기 
		//파일명에 현재시간을 붙여서 변경된 파일이름 구하기
		//ab.txt => ab_20211215151720123.txt
		
		//순수파일명만 추출 : ab
		int idx=fileName.lastIndexOf(".");
		String fName = fileName.substring(0, idx);  //ab
		
		//확장자 구하기 : .txt
		String ext = fileName.substring(idx);  //.txt
		
		String time=getTimeStamp();
		String result = fName+"_"+time+ext;
		logger.info("변경된 파일명 : {}", result);
		
		return result;
	}
	
	public String getTimeStamp() {
		//현재 시간 구하기 - 밀리초까지 => 20211215151720123
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str=sdf.format(d);
		logger.info("현재시간:{}", str);
		
		return str;
	}
	
	public String getFileInfo(String originName, long fileSize, 
			HttpServletRequest request) {
		String result="";
		if(originName!=null && !originName.isEmpty()) {
			float fSize = fileSize/1000f;
			fSize=Math.round(fSize*10)/10f;

			result="<img src='"+request.getContextPath()
				+"/resources/images/file.gif/'> "
				+originName+" ("+ fSize +"KB)";			
		}
		
		return result;
	}
	
}




