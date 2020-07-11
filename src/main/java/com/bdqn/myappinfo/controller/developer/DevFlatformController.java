package com.bdqn.myappinfo.controller.developer;

import com.alibaba.fastjson.JSONObject;
import com.bdqn.myappinfo.pojo.*;
import com.bdqn.myappinfo.service.IAppCategoryService;
import com.bdqn.myappinfo.service.IAppInfoService;
import com.bdqn.myappinfo.service.IAppVersionService;
import com.bdqn.myappinfo.service.IDataDictionaryService;
import com.bdqn.myappinfo.tools.MyConstant;
import com.bdqn.myappinfo.tools.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/dev/flatform")
public class DevFlatformController {

    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IDataDictionaryService dataDictionaryService;
    @Autowired
    private IAppCategoryService appCategoryService;
    @Autowired
    private IAppVersionService appVersionService;

    /**
     * 欢迎界面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/main")
    public ModelAndView mainView(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        Object devUserSession = session.getAttribute("devUserSession");
        DevUser devUser = null;

        if (devUserSession instanceof DevUser) {
            devUser = (DevUser) devUserSession;
            mv.addObject("devUserSession", devUser);
        }

        mv.setViewName("developer/main");
        return mv;
    }

    /**
     * 列表页面
     *
     * @param pageNum             当前页码
     * @param querySoftwareName   搜索名字
     * @param queryStatus         搜索状态
     * @param queryFlatformId     搜索平台
     * @param queryCategoryLevel1 搜索第一类型
     * @param queryCategoryLevel2 搜索第二类型
     * @param queryCategoryLevel3 搜索第三类型
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/list")
    public ModelAndView appList(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
                                @RequestParam(value = "queryStatus", required = false) Long queryStatus,
                                @RequestParam(value = "queryFlatformId", required = false) Long queryFlatformId,
                                @RequestParam(value = "queryCategoryLevel1", required = false) Long queryCategoryLevel1,
                                @RequestParam(value = "queryCategoryLevel2", required = false) Long queryCategoryLevel2,
                                @RequestParam(value = "queryCategoryLevel3", required = false) Long queryCategoryLevel3) throws Exception {
        ModelAndView mv = new ModelAndView();
        Pages pages = new Pages();
        pages.setCurrentPageNo(pageNum);

        List<AppInfo> list = appInfoService.getByPageAndText(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, queryStatus, pageNum, pages.getPage());
        List<AppInfo> rawList = appInfoService.getByPageAndText(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, queryStatus, null, null);
        List<DataDictionary> flatForm = dataDictionaryService.getByTypeCode("APP_FLATFORM");
        List<DataDictionary> status = dataDictionaryService.getByTypeCode("APP_STATUS");

        pages.setTotalPageCount(rawList.size() % pages.getPage() == 0 ? rawList.size() / pages.getPage() : rawList.size() / pages.getPage() + 1);
        pages.setTotalCount(rawList.size());

        List<AppCategory> categoryParent = appCategoryService.getCategory(null);
        mv.addObject("categoryLevel1List", categoryParent);
        mv.addObject("pages", pages);
        mv.addObject("appInfoList", list);
        mv.addObject("flatFormList", flatForm);
        mv.addObject("statusList", status);

        if (querySoftwareName != null) {
            mv.addObject("querySoftwareName", querySoftwareName);
        }
        if (queryStatus != null) {
            mv.addObject("queryStatus", queryStatus);
        }
        if (queryFlatformId != null) {
            mv.addObject("queryFlatformId", queryFlatformId);
        }
        if (queryCategoryLevel1 != null) {
            List<AppCategory> categoryChildren = appCategoryService.getCategory(queryCategoryLevel1);
            mv.addObject("categoryLevel2List", categoryChildren);
            mv.addObject("queryCategoryLevel1", queryCategoryLevel1);
        }
        if (queryCategoryLevel2 != null) {
            List<AppCategory> categoryChildren = appCategoryService.getCategory(queryCategoryLevel2);
            mv.addObject("categoryLevel3List", categoryChildren);
            mv.addObject("queryCategoryLevel2", queryCategoryLevel2);
        }
        if (queryCategoryLevel3 != null) {
            mv.addObject("queryCategoryLevel3", queryCategoryLevel3);
        }
        mv.setViewName("developer/appinfolist");
        return mv;
    }

    /**
     * APP信息添加页面
     *
     * @param fileUploadError
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appinfoadd")
    public ModelAndView appInfoAdd(@RequestParam(value = "fileUploadError", required = false) String fileUploadError) throws Exception {
        ModelAndView mv = new ModelAndView();

        if (fileUploadError != null) {
            switch (fileUploadError) {
                case MyConstant.FILE_UPLOAD_ERROR:
                    mv.addObject("fileUploadError", MyConstant.FILE_UPLOAD_ERROR_STRING);
                    break;
                case MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR:
                    mv.addObject("fileUploadError", MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR_STRING);
                    break;
                case MyConstant.IMAGE_EMPTY_ERROR:
                    mv.addObject("fileUploadError", MyConstant.IMAGE_EMPTY_ERROR_STRING);
                    break;
            }
        }
        mv.setViewName("developer/appinfoadd");

        return mv;
    }

    /**
     * APP信息添加操作
     *
     * @param appInfo
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appinfoaddsave")
    public String appInfoAddSave(AppInfo appInfo, @RequestParam(value = "a_logoPicPath", required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        if (file != null) {
            Object devUserSession = request.getSession().getAttribute("devUserSession");
            if (devUserSession == null) {
                return "redirect:http://localhost:8888/dev/login?error=" + MyConstant.LOGIN_EXPIRED_ERROR;
            }
            String realPath = request.getServletContext().getRealPath("/static/uploadfiles");
            String contextPath = request.getServletContext().getContextPath() + "/static/uploadfiles";
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newUrl = "/" + appInfo.getApkname() + extension;

            if (file.getSize() > 524244) {
                return "redirect:appinfoadd?fileUploadError=" + MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR;
            } else if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")) {
                DevUser devUser = null;
                if (devUserSession instanceof DevUser) {
                    devUser = (DevUser) devUserSession;
                }
                if (devUser != null) {
                    appInfo.setCreatedby(devUser.getId());
                    appInfo.setDevid(devUser.getId());
                }
                appInfo.setCreationdate(new Date());
                appInfo.setLogopicpath(contextPath + newUrl);
                appInfo.setLogolocpath(realPath + newUrl);


                Integer insert = appInfoService.insert(appInfo);

                if (insert > 0) {
                    File f = new File(realPath, newUrl);
                    file.transferTo(f);
                }
            } else {
                return "redirect:appinfoadd?fileUploadError=" + MyConstant.FILE_UPLOAD_ERROR;
            }
        } else {
            return "redirect:appinfoadd?fileUploadError=" + MyConstant.IMAGE_EMPTY_ERROR;
        }
        return "redirect:list";
    }

    /**
     * APP版本添加页面
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appversionadd")
    public ModelAndView appVersionAdd(@RequestParam("id") Long id, @RequestParam(value = "fileUploadError", required = false)String error) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<AppVersion> appVersions = null;
        AppVersion appVersion = new AppVersion();
        appVersion.setAppid(id);

        if (id != null) {
            appVersions = appVersionService.getByAppId(id);
        }
        if(error != null){
            if(error.equals(MyConstant.FILE_UPLOAD_ERROR)){
                mv.addObject("fileUploadError", MyConstant.FILE_UPLOAD_ERROR_STRING);
            }else if(error.equals(MyConstant.FILE_EMPTY_ERROR)){
                mv.addObject("fileUploadError", MyConstant.FILE_EMPTY_ERROR_STRING);
            }
        }
        mv.addObject("appVersionList", appVersions);
        mv.addObject("appVersion", appVersion);
        mv.setViewName("developer/appversionadd");
        return mv;
    }

/*    private String fileIsPresent(File file, String url) throws Exception{
        if(file.exists() && !file.isDirectory()){
            return url;
        }else if (!file.exists() && !file.isDirectory()){
            String name = url.substring(0, url.lastIndexOf(".") - 1);
            String extension = url.substring(url.lastIndexOf("."));
            String newUrl = url + "(1)" + extension;
            System.out.println(newUrl);
            return fileIsPresent(new File(newUrl), newUrl);
        }

        return null;
    }*/

    @RequestMapping("/app/addversionsave")
    public String addVersionSave(@RequestParam(value = "a_downloadLink", required = false) MultipartFile file, AppVersion appVersion, HttpServletRequest request) throws Exception {
        if (file != null && !file.isEmpty()) {
            String realPath = request.getServletContext().getRealPath("/static/uploadfiles");
            String contextPath = request.getServletContext().getContextPath() + "/static/uploadfiles";
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

//            String tempUrl = realPath + "/"+originalFilename;
//            File ispresent = new File(tempUrl);
//            String newurl = fileIsPresent(ispresent, tempUrl);

            if(extension.equalsIgnoreCase(".apk")){
                Object devUserSession = request.getSession().getAttribute("devUserSession");
                DevUser devUser = null;
                if(devUserSession instanceof DevUser){
                    devUser = (DevUser)devUserSession;
                } else{
                    return "redirect:http://localhost:8888/dev/login?error=" + MyConstant.LOGIN_EXPIRED_ERROR;
                }

                appVersion.setCreatedby(devUser.getId());
                appVersion.setCreationdate(new Date());
                appVersion.setApklocpath(realPath+"/"+originalFilename);
                appVersion.setDownloadlink(request.getRequestURI() + contextPath + "/" + originalFilename);
                appVersion.setApkfilename(originalFilename);

                Integer save = appVersionService.save(appVersion);
                if(save > 0){
                    File file1 = new File(realPath, "/"+originalFilename);
                    if(!file1.exists() && !file1.isDirectory()) {
                        file.transferTo(file1);
                    }
                    AppInfo appInfo = appInfoService.getById(appVersion.getAppid());

                    appInfo.setVersionid(appVersionService.getNewest().getId());
                    appInfoService.update(appInfo);
                }
                return "redirect:list";
            }else{
                return "redirect:appversionadd?id="+appVersion.getAppid()+"&fileUploadError=" + MyConstant.FILE_UPLOAD_ERROR;
            }
        } else {
            return "redirect:appversionadd?id="+appVersion.getAppid()+"&fileUploadError=" + MyConstant.FILE_EMPTY_ERROR;
        }
    }

    /**
     * APP版本修改页面
     *
     * @param vid
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appversionmodify")
    public ModelAndView appVersionModify(@RequestParam("vid") Long vid, @RequestParam("aid") Long id, @RequestParam(value = "fileUploadError", required = false)String fileUploadError) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<AppVersion> appVersionList = null;
        AppVersion appVersion = null;
        if (id != null) {
            appVersionList = appVersionService.getByAppId(id);
        }
        if (vid != null) {
            appVersion = appVersionService.getById(vid);
        }
        if(fileUploadError != null){
            if(fileUploadError.equals(MyConstant.FILE_UPLOAD_ERROR)){
                mv.addObject("fileUploadError", MyConstant.FILE_UPLOAD_ERROR_STRING);
            }else if(fileUploadError.equals(MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR)){
                mv.addObject("fileUploadError", MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR_STRING);
            }
        }

        mv.addObject("appVersionList", appVersionList);
        mv.addObject("appVersion", appVersion);
        mv.setViewName("developer/appversionmodify");
        return mv;
    }


    @RequestMapping("/app/appversionmodifysave")
    public String appVersionModifySave(HttpServletRequest request, AppVersion appVersion, @RequestParam(value = "attach", required = false) MultipartFile file) throws Exception{
        Object devUserSession = request.getSession().getAttribute("devUserSession");
        DevUser devUser = null;
        if(devUserSession instanceof DevUser){
            devUser = (DevUser)devUserSession;
        } else{
            return "redirect:http://localhost:8888/dev/login?error=" + MyConstant.LOGIN_EXPIRED_ERROR;
        }
        if(file != null && !file.isEmpty()){
            String realPath = request.getServletContext().getRealPath("/static/uploadfiles");
            String contextPath = request.getServletContext().getContextPath() + "/static/uploadfiles";
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            if(extension.equalsIgnoreCase(".apk")){
                appVersion.setDownloadlink(request.getRequestURI() + contextPath+"/"+originalFilename);
                appVersion.setApklocpath(realPath+"/"+originalFilename);
                appVersion.setApkfilename(originalFilename);

                File file1 = new File(realPath, "/"+originalFilename);
                file.transferTo(file1);
            }else{
                return "redirect:appversionmodify?fileUploadError="+MyConstant.FILE_UPLOAD_ERROR + "&vid=" + appVersion.getId() + "&aid=" + appVersion.getAppid();
            }
        }

        appVersion.setModifyby(devUser.getId());
        appVersion.setModifydate(new Date());
        appVersionService.update(appVersion);
        return "redirect:list";

    }
    /**
     * APP信息修改页面
     *
     * @param id
     * @param fileUploadError
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appinfomodify")
    public ModelAndView appInfoModify(@RequestParam("id") Long id, @RequestParam(value = "fileUploadError", required = false) String fileUploadError) throws Exception {
        ModelAndView mv = new ModelAndView();
        AppInfo byId = null;
        if (id != null) {
            byId = appInfoService.getById(id);
        }
        if (fileUploadError != null) {
            if (fileUploadError.equals(MyConstant.FILE_UPLOAD_ERROR)) {
                mv.addObject("fileUploadError", MyConstant.FILE_UPLOAD_ERROR_STRING);
            } else if (fileUploadError.equals(MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR)) {
                mv.addObject("fileUploadError", MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR_STRING);
            }
        }

        mv.addObject("appInfo", byId);
        mv.setViewName("developer/appinfomodify");
        return mv;
    }

    /**
     * APP信息修改操作
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appinfomodifysave")
    public String appInfoModifySave(AppInfo appInfo, @RequestParam(value = "attach", required = false) MultipartFile attach, HttpServletRequest request) throws Exception {

        if (attach != null && !attach.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("static/images");
            String contextPath = request.getSession().getServletContext().getContextPath();
            String originalFilename = attach.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newUrl = appInfo.getApkname() + extension;

            if (attach.getSize() > 524244) {
                return "redirect:appinfomodify?id=" + appInfo.getId() + "&fileUploadError=" + MyConstant.FILE_SIZE_OUT_OF_RANGE_ERROR;
            } else if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")) {
                appInfo.setLogolocpath(realPath + newUrl);
                appInfo.setLogopicpath(contextPath + newUrl);
                File file = new File(realPath, newUrl);
                attach.transferTo(file);
            } else {
                return "redirect:appinfomodify?id=" + appInfo.getId() + "&fileUploadError=" + MyConstant.FILE_UPLOAD_ERROR;
            }
        }

        Object devUserSession = request.getSession().getAttribute("devUserSession");
        if (devUserSession == null) {
            return "redirect:http://localhost:8888/dev/login?error=" + MyConstant.LOGIN_EXPIRED_ERROR;
        }


        DevUser devUser = null;
        if (devUserSession instanceof DevUser) {
            devUser = (DevUser) devUserSession;
        }
        if (devUser != null) {
            appInfo.setModifyby(devUser.getId());
            appInfo.setModifydate(new Date());
        }
        appInfoService.update(appInfo);
        return "redirect:list";
    }

    /**
     * APP详细信息查看页面
     *
     * @param aid
     * @return
     * @throws Exception
     */
    @RequestMapping("/app/appview/{appinfoid}")
    public ModelAndView appView(@PathVariable("appinfoid") Long aid) throws Exception {
        ModelAndView mv = new ModelAndView();

        AppInfo appInfo = appInfoService.getById(aid);
        List<AppVersion> appVersionList = appVersionService.getByAppId(aid);

        mv.addObject("appVersionList", appVersionList);
        mv.addObject("appInfo", appInfo);

        mv.setViewName("developer/appinfoview");
        return mv;
    }

    /**
     * 上架操作
     *
     * @param appId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/app/{appId}/sale.json")
    public String sale(@PathVariable("appId") Long appId) throws Exception {
        JSONObject json = new JSONObject();

        AppInfo appInfo = appInfoService.getById(appId);
        if(appInfo != null){
            Integer update = 0;
            if(appInfo.getStatus() == 4){
                appInfo.setStatus(5L);
                update = appInfoService.update(appInfo);
            }else if(appInfo.getStatus() == 5 || appInfo.getStatus() == 2){
                appInfo.setStatus(4L);
                update = appInfoService.update(appInfo);
            }

            if(update > 0){
                json.put("resultMsg", "success");
                json.put("errorCode", "0");
            }else{
                json.put("resultMsg", "failed");
                json.put("errorCode", "exception000001");
            }
        }else{
            json.put("resultMsg", "failed");
            json.put("errorCode", "param000001");
        }

        return json.toJSONString();
    }

    /**
     * 获取种类信息
     *
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/categorylevellist.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AppCategory> categorylevellist(@RequestParam("pid") Long pid) throws Exception {
        return appCategoryService.getCategory(pid);
    }

    /**
     * 获取各种数据信息（如平台种类，状态种类等）
     *
     * @param tcode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/datadictionarylist.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DataDictionary> dataDictionaryList(@RequestParam("tcode") String tcode) throws Exception {
        return dataDictionaryService.getByTypeCode(tcode);
    }

    @ResponseBody
    @RequestMapping("/app/delapp.json")
    public String delApp(@RequestParam("id") Long id) throws Exception {
        JSONObject json = new JSONObject();

        Integer delete = appInfoService.delete(id);
        if (delete > 0) {
            json.put("delResult", "true");
        } else if (delete == 0) {
            json.put("delResult", "notexist");
        } else {
            json.put("delResult", "false");
        }

        return json.toJSONString();
    }
}
