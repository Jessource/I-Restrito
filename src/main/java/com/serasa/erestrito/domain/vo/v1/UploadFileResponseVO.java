package com.serasa.erestrito.domain.vo.v1;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponseVO implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
}
