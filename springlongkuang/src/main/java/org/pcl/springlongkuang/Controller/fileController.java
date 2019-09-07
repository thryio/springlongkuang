package org.pcl.springlongkuang.Controller;

import lombok.extern.slf4j.Slf4j;
import org.pcl.springlongkuang.Utils.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Slf4j
@RestController
@RequestMapping("/file")
public class fileController {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseVo<?> Post(@RequestParam("file")MultipartFile file){
        // 获取原始名字
        String fileName = file.getOriginalFilename();

        File file1=new File("");
        try {
            // 文件保存路径
            String filePath = file1.getCanonicalPath()+File.separator+"files\\";
            // 文件重命名，防止重复
            fileName = filePath +fileName;
            System.out.println("fileName===="+fileName+"   "+File.separator);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseVo.success(true);
    }
}
