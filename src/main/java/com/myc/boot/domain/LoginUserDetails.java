package com.myc.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 用户登录信息
 */

@Data
@NoArgsConstructor
public class LoginUserDetails implements UserDetails {


    private User user;

    private List<String> permissions;


    // 不被序列化
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> permissionsCollect;

    public LoginUserDetails(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (permissionsCollect != null) {
            return permissionsCollect;
        }

        permissionsCollect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return permissionsCollect;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
