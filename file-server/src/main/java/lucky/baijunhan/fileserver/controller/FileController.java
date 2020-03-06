package lucky.baijunhan.fileserver.controller;

import lombok.extern.slf4j.Slf4j;
import lucky.baijunhan.fileserver.model.FileModel;
import lucky.baijunhan.fileserver.model.HttpResult;
import lucky.baijunhan.fileserver.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lucky.baijunhan.fileserver.utils.Utils.makePath;

@RestController
@Slf4j
public class FileController {

    private final FileService fileService;

    private final static String REQ_PREFIX = "/file-server/files";

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/one-file")
    public HttpResult<String> fileUpload(@RequestParam(name = "file") MultipartFile multipartFile,
                                         @RequestParam String path,
                                         @RequestParam(required = false, defaultValue = "true") boolean saveAsOriginalName) {
        return fileService.saveOneFile(multipartFile, path, saveAsOriginalName);
    }

    @RequestMapping(value = "/files/**")
    public Object fileExplorer(HttpServletRequest request) {
        String reqPath = request.getServletPath().substring("/files".length());
        if(reqPath.equals(""))
            reqPath = "/";
        String filePath = makePath(fileService.baseDir, reqPath);
        File file = new File(filePath);
        ModelAndView mv = new ModelAndView();
        if (!file.exists()) {
            mv.setViewName("error");
            mv.addObject("message", "file or dir: " + reqPath + " not exist!");
            return mv;
        }
        if (file.isDirectory()) {
            // 父目录
            String parentDir = reqPath.substring(0, reqPath.lastIndexOf('/'));
            List<FileModel> childs = new ArrayList<>();
            mv.setViewName("index");
            mv.addObject("childs", childs);
            mv.addObject("index", reqPath);
            childs.add(new FileModel(REQ_PREFIX + "/", "/", true, null,0));
            childs.add(new FileModel(makePath(REQ_PREFIX, parentDir), "..", true, null, 0));
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return mv;
            }
            for (File f : files) {
                FileModel fm = new FileModel(makePath(REQ_PREFIX, reqPath, f.getName()),
                        f.getName(),
                        f.isDirectory(),
                        new Date(f.lastModified()),
                        f.length());
                childs.add(fm);
            }
        } else {
//            URLEncoder.encode("/download" + reqPath, StandardCharsets.UTF_8);
            mv.setViewName("redirect:/download" + new String(reqPath.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
//            try (
//                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//            ){
//                HttpHeaders headers = new HttpHeaders();
//                String fileName = new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
////                headers.setContentDispositionFormData("attachment", fileName);
////                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                headers.setContentType(MediaType.ALL);
//                return new ResponseEntity<>(inputStream.readAllBytes(),
//                        headers, HttpStatus.CREATED);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
        return mv;
    }

    @RequestMapping("/delete/**")
    public Object delete(HttpServletRequest request){
        ModelAndView mv  = new ModelAndView();
        String reqPath = request.getServletPath().substring("/delete".length());
        if(reqPath.equals(""))
            reqPath = "/";
        String filePath = makePath(fileService.baseDir, reqPath);
        File file = new File(filePath);
        if(file.delete()) {
            log.info("delete file: {}", filePath);
            mv.addObject("message", "delete file: " + reqPath + " success");
        }
         else
             mv.addObject("error", "file: " + reqPath + " delete failed!");
        String parentDir = reqPath.substring(0, reqPath.lastIndexOf('/'));
        mv.setViewName("redirect:" + new String(makePath("/files", parentDir).getBytes(StandardCharsets.UTF_8)
                , StandardCharsets.ISO_8859_1));
        return mv;
    }

    @RequestMapping("/upload-page")
    public Object upload(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("upload");
        return mv;
    }
}
