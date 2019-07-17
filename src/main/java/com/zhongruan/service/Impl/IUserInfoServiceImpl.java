package com.zhongruan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zhongruan.bean.Role;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.dao.IRoleDao;
import com.zhongruan.dao.IUserInfoDao;
import com.zhongruan.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("userInfoService")
public class IUserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDao iUserInfoDao;
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<UserInfo> findAll(int page, int size){
        PageHelper.startPage(page,size);
        return  iUserInfoDao.findAll();
    }
    public List<UserInfo> login(UserInfo userInfo){return iUserInfoDao.login(userInfo);}
    public void add(UserInfo userInfo){ iUserInfoDao.add(userInfo);}
    public void update(UserInfo userInfo){
        iUserInfoDao.update(userInfo);
    }

    @Override
    public UserInfo findByUserName(String userName) {
        return iUserInfoDao.findByUserName(userName);
    }

    @Override
    public List<UserInfo> findOneById(int id) {
        return iUserInfoDao.findOneById(id);
    }

    @Override
    public List<Role> findRoleByUserId(int id) {
        return iRoleDao.findRoleByUserId(id);
    }
    //public void deleteAll(String ids){iUserInfoDao.deleteAll(ids);}
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserInfo userInfo=iUserInfoDao.findByUserName(username);
            List<Role> roles= iRoleDao.findRoleByUserId(userInfo.getId());
           // userInfo.setRoles(roles);
           User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(roles));
           return user;
    }

    private  List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list =new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename()));
        }
        return list;
    }


}
