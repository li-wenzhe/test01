package com.itheima.health.springSecurity;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.pojo.Permission;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //使用userame从数据库查询User对象
        com.itheima.health.pojo.User user = userService.findUserByUserName(username);
        //判断username是否存在
        if (user==null){
            return null;
        }
        //获取用户所对应的角色
        Set<Role> roles = user.getRoles();
        //封装用户具有的角色和权限
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            //封装角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        //返回参数
        UserDetails userDetails = new User(username,user.getPassword(),list);
        return userDetails;
    }
}
