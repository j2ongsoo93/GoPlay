package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Data
@AllArgsConstructor
public class BoardUploadDTO {

	//원본파일 이름
	private String fileName;

	private String uuid;

//	private String folderPath;

	private String realFileName;

	private String realWebPath;

	public String getImagePath(){
		try {
//			return URLEncoder.encode(folderPath+"/" +uuid + fileName,"UTF-8");
			return URLEncoder.encode(realWebPath,"UTF-8");
			//return realWebPath;

		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

}
