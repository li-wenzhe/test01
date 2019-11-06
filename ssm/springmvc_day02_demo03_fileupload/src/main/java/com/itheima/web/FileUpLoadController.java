package com.itheima.web;

import com.itheima.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "file")
public class FileUpLoadController {
    @RequestMapping(value = "upload")
    public String fileUpLoad(HttpServletRequest request, String pdesc, MultipartFile upload) throws IOException {

        System.out.println("pdesc============>"+pdesc);
        //获得文件名
        String filename = upload.getOriginalFilename();
        //获得随机文件名
        String uuidName = UploadUtils.getUUIDName(filename);
        //获得绝对路径(文件上传在那个文件里,自定义的)
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        //4. 获得两层目录
        String dir = UploadUtils.getDir();
        //5.创建两层目录
        File fileDir = new File(realPath, dir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        //6.使用 MulitpartFile 接口中方法，把上传的文件写到指定位置
        upload.transferTo(new File(fileDir,uuidName));


        System.out.println("传统文件上传");
        return "success";
    }
}
