package com.study.service.imp;

import com.study.dao.PermissionDao;
import com.study.dao.RoleDao;
import com.study.dao.UserInfoDao;
import com.study.domain.Permission;
import com.study.domain.Role;
import com.study.domain.UserInfo;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SimpleTimeZone;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        userInfo = userInfoDao.findByName(s);
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getId()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(String id){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        List<Role> roleList = roleDao.findById(id);
        for (Role r:roleList
             ) {
            list.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleName()));

        }

        return list;


    }
}
