package com.rhast.clstrg.spring.boot.controllers.test;

import java.util.List;

import com.dropbox.core.DbxException;
import com.rhast.clstrg.spring.boot.dropbox.DropboxClient;
import com.rhast.clstrg.spring.boot.google.drive.GoogleDriveAuthentication;
import com.rhast.clstrg.spring.boot.test.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lermontov-w
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private static final String ME = "_first_test_user_";

    DropboxClient dropboxClient = new DropboxClient();

    @GetMapping
    public BaseResponse test() {
        throw new RuntimeException("hi");
//        return new BaseResponse();
    }

    @GetMapping("/list-dir")
    public List<String> listDir(@RequestParam(defaultValue = "") String dir) throws DbxException {
        return dropboxClient.listFiles(dir);
    }
}
