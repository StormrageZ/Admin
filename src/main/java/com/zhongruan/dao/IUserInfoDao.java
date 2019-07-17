package com.zhongruan.dao;

import com.zhongruan.bean.Role;
import com.zhongruan.bean.UserInfo;

import java.util.List;

public interface IUserInfoDao {
   public List<UserInfo> findAll();
   public List<UserInfo> login(UserInfo userInfo);
   public void add(UserInfo userInfo);
   public void update(UserInfo userInfo);
   public List<UserInfo> findOneById(int id);
   public UserInfo findByUserName(String username);

}
