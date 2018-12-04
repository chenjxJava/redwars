package com.chenjx.redwars.rest;

import com.chenjx.redwars.domain.FastDFSFile;
import com.chenjx.redwars.domain.UploadLog;
import com.chenjx.redwars.result.GlobalErrorInfoEnum;
import com.chenjx.redwars.result.GlobalErrorInfoException;
import com.chenjx.redwars.result.ResultBody;
import com.chenjx.redwars.result.UploadErrorInfoEnum;
import com.chenjx.redwars.service.UploadLogService;
import com.chenjx.redwars.utils.FastDFSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UploadLogService uploadLogService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path=saveFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed",e);
        }
        return "redirect:/uploadStatus";
    }

    @PostMapping("/fileupload")
    @ResponseBody
    public ResultBody Upload(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws GlobalErrorInfoException {
        UploadLog uploadLog = new UploadLog();
        String originalFilename = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new GlobalErrorInfoException(UploadErrorInfoEnum.NO_CONTENT);
        }
        try {
            // Get the file and save it somewhere
            String path=saveFile(file);
            logger.info(new StringBuffer("successfully uploaded '").
                    append(originalFilename).append("',path:").append(path).toString());

            uploadLog.setName(originalFilename);
            uploadLog.setPath(path);

            uploadLogService.saveUploadLog(uploadLog);
        } catch (Exception e) {
            logger.error("upload file failed",e);
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.SYSTEM_ERROR);
        }
        return new ResultBody(uploadLog);
    }


    @GetMapping("/list")
    @ResponseBody
    public ResultBody list() {
        List<UploadLog> list = uploadLogService.findAllUploadLog();
        return new ResultBody(list);
    }

    @GetMapping("/listByPage")
    @ResponseBody
    public ResultBody listByPage(HttpServletRequest request) {
        int currentPage = 1;
        int pageSize = 10;
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        if(!StringUtils.isEmpty(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if(!StringUtils.isEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        List<UploadLog> list = uploadLogService.findUploadLogsByPage(currentPage, pageSize);
        return new ResultBody(list);
    }


    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}