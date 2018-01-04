package com.guxiang.controller;

import com.guxiang.common.utils.FastDFSClient;
import com.guxiang.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * PictureController
 *
 * @author guxiang
 * @date 2017/12/25
 */
@Controller
public class PictureController {
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value="/pic/upload", produces= MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {
        try {
            //把图片上传的图片服务器

            //取出扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);


            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //补充完整的url
            url=IMAGE_SERVER_URL+url;


            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);
        }catch (Exception e){
            e.printStackTrace();
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            return JsonUtils.objectToJson(result);
        }
    }
}