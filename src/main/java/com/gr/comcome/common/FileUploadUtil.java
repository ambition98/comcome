package com.gr.comcome.common;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUploadUtil {

	// 파일 업로드 처리
	public List<Map<String, Object>> fileUpload(HttpServletRequest request, String path) {

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
		List<Map<String, Object>> list = new ArrayList<>();
		String uploadPath = request.getSession()
									.getServletContext()
									.getRealPath("/")
									 + ConstUtil.USER_FILE_UPLOAD_ROOT_PATH
									 + path;
		
		fileMap.forEach((key, value) -> {
			String originFileName = value.getOriginalFilename();
			String fileName = getUniqueFileName(originFileName);
			long fileSize = value.getSize();
			
			try {
				value.transferTo(new File(uploadPath, fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("originFileName", originFileName);
			map.put("fileName", fileName);
			map.put("fileSize", fileSize);

			list.add(map);
		});

		return list;
	}
	
	public String getFileInfo(String originName, long fileSize, HttpServletRequest request) {
		String result = "";
		if (originName != null && !originName.isEmpty()) {
			float fSize = fileSize / 1000f;
			fSize = Math.round(fSize * 10) / 10f;

			result = "<img src='" + request.getContextPath() + "/resources/images/file.gif/'> " + originName + " ("
					+ fSize + "KB)";
		}

		return result;
	}
	
	private String getUniqueFileName(String fileName) {
		// 파일명이 중복될 경우 파일이름 변경하기
		// 파일명에 현재시간을 붙여서 변경된 파일이름 구하기
		// ab.txt => ab_20211215_151720123.txt

		int idx = fileName.lastIndexOf(".");
		String fName = fileName.substring(0, idx);
		String ext = fileName.substring(idx);

		String time = getTimeStamp();
		String result = fName + "_" + time + ext;
		log.info("변경된 파일명 : {}", result);

		return result;
	}

	private String getTimeStamp() {
		// 현재 시간 구하기 - 밀리초까지 => 20211215151720123
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		String str = sdf.format(d);
		log.info("현재시간:{}", str);

		return str;
	}
	
	
	public String getUploadPath( HttpServletRequest request) {
		//업로드 경로 구하기
		String path=ConstUtil.USER_FILE_UPLOAD_ROOT_PATH;
		
		
			//실제 물리적인 경로 구하기
			//application.getRealPath()
			//config.getServletContext().getRealPath()
			path=request.getSession().getServletContext().getRealPath(path);
		
		
		log.info("업로드 경로 : {}", path);
		
		return path;
	}
}
