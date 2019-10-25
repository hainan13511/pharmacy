package pharmacy.web.management.controller;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.RSAUtils;

@Controller
public class GenerateRsaKeyController {

	/**
	 * 		生成公钥私钥
	 * @param request  
	 * @return 返回集合
	 * @throws Exception  
	 */
	@RequestMapping("/html/getKey.action")
	@ResponseBody
	public ArrayList<String> RSAString(HttpServletRequest request)throws Exception {
		String publicKeyExponent="";
		String publicKeyModulus="";
		
		HashMap<String, Object> map = RSAUtils.getKeys();
		//生成公钥和私钥    
		 RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		 RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
		//私钥保存在session中，用于解密  
		 request.getSession().setAttribute("privateKeyMagLogin", privateKey);
		//公钥信息保存在页面，用于加密   公钥指数  
		publicKeyExponent = publicKey.getPublicExponent().toString(16);
		 System.out.println("页面的公钥1:"+publicKeyExponent);
		//模 
		publicKeyModulus = publicKey.getModulus().toString(16);
		 System.out.println("页面的公钥:"+publicKeyModulus);
		
		// request.getSession().setAttribute("publicKeyExponent", publicKeyExponent);  
		//request.getSession().setAttribute("publicKeyModulus", publicKeyModulus); 
		ArrayList<String> list = new ArrayList<String>();
		list.add(publicKeyExponent);
		list.add(publicKeyModulus);
		System.out.println("map:"+list.get(0).toString());
		return list;
	}
	
	
	
	
	
}
