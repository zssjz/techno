package com.jason.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * 用户账号实体
 * Created by Jason on 2018/4/13.
 */
@ApiModel(description = "账户")
@Entity
@Table(name = "user")
public class UserDO implements UserDetails {

    @ApiModelProperty(name = "id", value = "主键", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "id", sequenceName = "")    // 使用自定义序列
    private Integer id;

    @ApiModelProperty(name = "username", value = "用户名")
    @NotBlank(message = "账号不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String username;

    @ApiModelProperty(name = "password", value = "密码")
    @NotBlank(message = "密码不能为空")
//    @Size(min = 6,max = 18, message = "密码长度不得小于6位，不得大于18位")
    private String password;

    @ApiModelProperty(name = "isAccountNonExpired", value = "账号未过期", hidden = true)
    private Integer isAccountNonExpired;

    @ApiModelProperty(name = "isAccountNonLocked", value = "账号未锁定", hidden = true)
    private Integer isAccountNonLocked;

    @ApiModelProperty(name = "isCredentialsNonExpired", value = "密码未过期", hidden = true)
    private Integer isCredentialsNonExpired;

    @ApiModelProperty(name = "isEnabled", value = "账号可用", hidden = true)
    private Integer isEnabled;

    public UserDO(){}

    public UserDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDO(String username, String password, Integer isAccountNonExpired, Integer isAccountNonLocked, Integer isCredentialsNonExpired, Integer isEnabled) {
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public UserDO(Integer id, String username, String password, Integer isAccountNonExpired, Integer isAccountNonLocked, Integer isCredentialsNonExpired, Integer isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(Integer isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public Integer getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Integer isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public Integer getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(Integer isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        AuthorityUtils.commaSeparatedStringToAuthorityList("role_admin,role_user");
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (this.getIsAccountNonExpired() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.getIsAccountNonLocked() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (this.getIsCredentialsNonExpired() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        if (this.getIsEnabled() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
