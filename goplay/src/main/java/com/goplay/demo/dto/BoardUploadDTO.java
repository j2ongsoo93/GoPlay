package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Data
@AllArgsConstructor
public class BoardUploadDTO {

	private String fileName;

	private String uuid;

	private String folderPath;

	public String getImageURL(){
		try {
			return URLEncoder.encode(folderPath+"/" +uuid + fileName,"UTF-8");

		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return "";
	}



}
