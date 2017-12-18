package com.yunengzhe.PVMTS.util;  
  
import java.io.File;  
import java.io.IOException;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.apache.commons.io.FileUtils;  
import org.springframework.context.annotation.Scope;  
import org.springframework.stereotype.Component;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.multipart.MultipartFile;  
  
/** 
 * 文件上传处理类 
 * <功能详细描述> 
 *  
 * @author  Administrator 
 * @version  [版本号, 2014年3月6日] 
 * @see  [相关类/方法] 
 * @since  [产品/模块版本] 
 */  
@Component  
@Scope("prototype")   
@RequestMapping("/uploadFile")  
public class UploadController  
{  
    @RequestMapping("/upload")  
    public String upload(@RequestParam MultipartFile myfiles,HttpServletRequest request) throws IOException  
    {  
      //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解   
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解   
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件   
          
   //     for(MultipartFile myfile : myfiles){   
            if(myfiles.isEmpty()){   
                System.out.println("文件未上传");   
            }else{   
                System.out.println("文件长度: " + myfiles.getSize());   
                System.out.println("文件类型: " + myfiles.getContentType());   
                System.out.println("文件名称: " + myfiles.getName());   
                System.out.println("文件原名: " + myfiles.getOriginalFilename());   
                System.out.println("========================================");   
                //如果用的是Tomcat服务器，则文件会上传到  {服务发布位置}\\WEB-INF\\upload\\文件夹中   
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");   
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的   
              //  FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
               // FileUtils.copyFileToDirectory(srcFile, destDir)
            }   
       // }   
          
        return "";  
    }  
}  