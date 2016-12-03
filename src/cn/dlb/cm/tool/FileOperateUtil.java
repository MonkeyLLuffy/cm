package cn.dlb.cm.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileOperateUtil {

	public static String FILEDIR = "f:\\file";

	/**
	 * 上传
	 * 
	 * @param request
	 * @throws IOException
	 * @author 路遥
	 */
	public static Map<String, String> upload(HttpServletRequest request)
			throws IOException {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		File file = new File(FILEDIR);
		if (!file.exists()) {
			file.mkdir();
		}
		Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator();
		MultipartFile mFile = null;
		String fileName = null;
		while (it.hasNext()) {
			Map.Entry<String, MultipartFile> entry = it.next();
			mFile = entry.getValue();
			if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
				System.out.println(initFilePath(mFile.getOriginalFilename()));
				fileName = initFilePath(mFile.getOriginalFilename());
				write(mFile.getInputStream(), new FileOutputStream(fileName));
			}
		}
		Map<String, String> map = new HashMap<>();
		map.put("address", fileName);
		map.put("fileName", mFile.getOriginalFilename());
		return map;
	}

	private static String initFilePath(String name) {
		String dir = getFileDir(name) + "";
		File file = new File(FILEDIR + "\\" + dir);
		if (!file.exists()) {
			file.mkdir();
		}
		Long num = new Date().getTime();
		Double d = Math.random() * num;
		return (file.getPath() + "\\" + num + d.longValue() + "_" + name)
				.replaceAll(" ", "-");
	}

	private static int getFileDir(String name) {
		return name.hashCode() & 0xf;
	}
	//下载
	public static void download(String downloadAddress,
			ServletOutputStream out, HttpServletRequest request,
			HttpServletResponse response,String type) {
		String fileName=null;
		if(!type.equals("download")){
			FileInputStream in = null;
			try {
				in = new FileInputStream(new File(downloadAddress));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				write(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		try {
			if(downloadAddress.indexOf("_")!=-1){
				int index=downloadAddress.indexOf("_");
				fileName=downloadAddress.substring(index+1);
				String address=downloadAddress.substring(0, index);
				
				String newAddress=address+setFileDownloadHeader(request, response, fileName,type);
				FileInputStream in = new FileInputStream(new File(newAddress));
				write(in, out);
				
			}else{
				setFileDownloadHeader(request, response, downloadAddress,type);
				FileInputStream in = new FileInputStream(new File(downloadAddress));
				write(in, out);
			}
			
			
		} catch (FileNotFoundException e) {
			try {
				FileInputStream in = new FileInputStream(new String(
						downloadAddress.getBytes("iso-8859-1"), "utf-8"));
				write(in, out);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	/**
	 * 写入数据
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 * @author 路遥
	 */
	public static void write(InputStream in, OutputStream out)
			throws IOException {
		try {
			byte[] buffer = new byte[2048];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	public static String setFileDownloadHeader(HttpServletRequest request,
			HttpServletResponse response, String fileName,String type) {
		String encodedfileName = null;
		try {
			// 中文文件名支持
			System.out.println(fileName);
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE")) {// IE
				encodedfileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				encodedfileName = new String(fileName.getBytes("UTF-8"),
						"iso-8859-1");
			} else {
				encodedfileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
			if(type.equals("download")){
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
			}else{
				response.setHeader("Content-Disposition", " ; filename=\""
						+ encodedfileName + "\"");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedfileName;
	}
}
