package com.zhongruan.service;

import com.zhongruan.bean.Role;
import com.zhongruan.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {
    public List<UserInfo> findAll(int page, int size);
    public List<UserInfo> login(UserInfo userInfo);
    public void add(UserInfo userInfo);
    public void update(UserInfo userInfo);
    public UserInfo findByUserName(String userName);
    List<UserInfo> findOneById(int id);
    List<Role> findRoleByUserId(int id);
}
