package com.zhongruan.controller;

import com.github.pagehelper.PageInfo;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int page ,@RequestParam(defaultValue = "5") int size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userInfoService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    /*@RequestMapping("/login.do")
    public ModelAndView login(UserInfo userInfo){
        ModelAndView mv = new ModelAndView();
        if(userInfo.getUsername() == null){
            mv.setViewName("../index");
        }else {
            List<UserInfo> userInfos = userInfoService.login(userInfo);
            if (userInfos.isEmpty()) mv.setViewName("/../login");
            else {
                mv.setViewName("main");
               // request.getSession().setAttribute("userInfo",userInfo);
            }
        }
        return mv;
    }*/
    @RequestMapping("/add.do")
    public String add(UserInfo userInfo){
        userInfoService.add(userInfo);
        return "redirect:/user/findAll.do";
    }
    @RequestMapping("/toUpdate.do")
    public ModelAndView toUpdate(int id){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfo = userInfoService.findOneById(id);
        mv.addObject("user",userInfo.get(0));
        mv.setViewName("user-update");
        return mv;
    }
    @RequestMapping("/update.do")
    public String update(UserInfo userInfo){
        userInfoService.update(userInfo);
        return  "redirect:/user/findAll.do";
    }


}
