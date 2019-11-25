package com.xt.web.controller;

import com.xt.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final String Folder = "D:\\ideaProjects\\xt-security\\xt-security-demo\\src\\main\\resources\\file";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println("文件名：" + file.getName()); // file
        System.out.println("原始文件名" + file.getOriginalFilename()); // test.txt
        System.out.println("文件大小：" + file.getSize());

        File localFile = new File(Folder, new Date().getTime() + ".txt");

        // 把文件写在本地
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try (InputStream is = new FileInputStream(new File(Folder, id + ".txt"));
             OutputStream os = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(is, os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
