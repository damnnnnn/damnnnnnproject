package com.thzm.lixin.bootupload.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.thzm.lixin.bootupload.model.UserInfo;
import com.thzm.lixin.bootupload.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService  uservice;
	
	
	@RequestMapping(value="/")
	public String  defaultView()
	{
		System.out.println("UserController  is  defaultView  start...");
		
		return  "upload";
	}
	@RequestMapping("/upload")
	public String  uploadFile(MultipartFile  sfile,Model model)
	{
		System.out.println("UserController  is  uploadFile  start...");
		
		//1.用户上传的空文件
		if(sfile.isEmpty())
		{
			model.addAttribute("fileinfo", "对不起，你上传的文件是空文件,请重新上传");
		}
		else
		{
			
			String fileName=sfile.getOriginalFilename();
			System.out.println("fileName-->"+fileName);
			//判断上传的文件名
			if(!fileName.endsWith(".txt"))
			{
				model.addAttribute("fileinfo", "对不起，你上传的文件的后缀名不是.txt,请核对!");
			}
			else
			{
				
				 //读取上传文件的内容
				try {
					
					//缓冲流，提升流的读写的性能
					BufferedReader  br = new BufferedReader
							(new InputStreamReader(sfile.getInputStream(),"GBK"));
					
					String  content="";
					
					List<UserInfo>   lists = new ArrayList<UserInfo>();
					
					while((content=br.readLine())!=null)
					{
						System.out.println(content);
						
						String[]  udatas=content.split("\\|");
						UserInfo  u = new UserInfo();
						u.setUname(udatas[0]);
						u.setUpwd(udatas[1]);
						u.setUsex(udatas[2]);
						u.setUbirthday(udatas[3]);
						u.setUaddress(udatas[4]);
						u.setUstate(Integer.parseInt(udatas[5]));
						u.setDid(Integer.parseInt(udatas[6]));
						u.setFaceimg(udatas[7]);
						u.setPhone(udatas[8]);
						lists.add(u);
					}
					//应该走个异步
					this.uservice.batchUser(lists);
					model.addAttribute("fileinfo", "上传文件成功");
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
		
		
		return  "upload";
	}

}
