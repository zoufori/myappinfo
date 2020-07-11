package com.bdqn.myappinfo.controller.backend;

import com.bdqn.myappinfo.pojo.AppCategory;
import com.bdqn.myappinfo.pojo.AppInfo;
import com.bdqn.myappinfo.pojo.AppVersion;
import com.bdqn.myappinfo.pojo.DataDictionary;
import com.bdqn.myappinfo.service.IAppCategoryService;
import com.bdqn.myappinfo.service.IAppInfoService;
import com.bdqn.myappinfo.service.IAppVersionService;
import com.bdqn.myappinfo.service.IDataDictionaryService;
import com.bdqn.myappinfo.tools.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manager/backend/app")
public class BDAppController {

    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IAppCategoryService appCategoryService;
    @Autowired
    private IDataDictionaryService dataDictionaryService;
    @Autowired
    private IAppVersionService appVersionService;

//    @RequestMapping("/list")
//    public ModelAndView appList() throws Exception{
//        return this.appList(1);
//    }

    @RequestMapping("/checksave")
    public String checkSave(@RequestParam("status")Long status, @RequestParam("id")Long id) throws Exception{
        AppInfo appInfo = appInfoService.getById(id);
        appInfo.setStatus(status);
        appInfoService.update(appInfo);
        return "redirect:list";
    }

    @RequestMapping("/check")
    public ModelAndView appCheck(@RequestParam("aid")Long aid, @RequestParam("vid")Long vid) throws Exception{
        ModelAndView mv = new ModelAndView();

        AppVersion appVersion = appVersionService.getById(vid);
        AppInfo appInfo = appInfoService.getById(aid);

        mv.addObject("appInfo", appInfo);
        mv.addObject("appVersion", appVersion);
        mv.setViewName("backend/appcheck");
        return mv;
    }

    @RequestMapping("/list")
    public ModelAndView appListRaw(@RequestParam(value = "pageIndex", required = false) Integer page,
                                   @RequestParam(value = "querySoftwareName", required = false) String text,
                                   @RequestParam(value = "queryCategoryLevel1", required = false) Long categoryLevel1,
                                   @RequestParam(value = "queryCategoryLevel2", required = false) Long categoryLevel2,
                                   @RequestParam(value = "queryCategoryLevel3", required = false) Long categoryLevel3,
                                   @RequestParam(value = "queryFlatformId", required = false) Long flatForm) throws Exception {
        if (page == null) {
            return this.appList(1, text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm);
        } else {
            return this.appList(page, text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm);
        }
    }


    @RequestMapping("/list/{pageIndex}")
    public ModelAndView appList(@PathVariable("pageIndex") Integer page, @RequestParam(value = "querySoftwareName", required = false) String text,
                                @RequestParam(value = "queryCategoryLevel1", required = false) Long categoryLevel1,
                                @RequestParam(value = "queryCategoryLevel2", required = false) Long categoryLevel2,
                                @RequestParam(value = "queryCategoryLevel3", required = false) Long categoryLevel3,
                                @RequestParam(value = "queryFlatformId", required = false) Long flatForm) throws Exception {
        ModelAndView mv = new ModelAndView();
        Pages pages = new Pages();
        pages.setCurrentPageNo(page);

        List<AppCategory> categoryLevel1List = appCategoryService.getCategory(null);
        List<DataDictionary> flatformList = dataDictionaryService.getByTypeCode("APP_FLATFORM");
        List<AppInfo> list = appInfoService.getByPageAndText(text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm, 1L, page, pages.getPage());
        List<AppInfo> rawList = appInfoService.getByPageAndText(text, categoryLevel1, categoryLevel2, categoryLevel3, flatForm, 1L, null, null);

        pages.setTotalCount(rawList.size());
        pages.setTotalPageCount(rawList.size() % pages.getPage() == 0 ? rawList.size() / pages.getPage() : rawList.size() / pages.getPage() + 1);

        if (categoryLevel1 != null) {
            mv.addObject("categoryLevel2List", appCategoryService.getCategory(categoryLevel1));
        }
        if (categoryLevel2 != null) {
            mv.addObject("categoryLevel3List", appCategoryService.getCategory(categoryLevel2));
        }

        mv.addObject("flatFormList", flatformList);
        mv.addObject("categoryLevel1List", categoryLevel1List);
        mv.addObject("appInfoList", list);
        mv.addObject("pages", pages);
        mv.addObject("querySoftwareName", text);
        mv.addObject("queryCategoryLevel1", categoryLevel1);
        mv.addObject("queryCategoryLevel2", categoryLevel2);
        mv.addObject("queryCategoryLevel3", categoryLevel3);
        mv.addObject("queryFlatformId", flatForm);

        mv.setViewName("backend/applist");
        return mv;
    }

    @RequestMapping(value = "/categorylevellist.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AppCategory> categorylevellist(@RequestParam("pid") Long pid) throws Exception {
        return appCategoryService.getCategory(pid);
    }

}

