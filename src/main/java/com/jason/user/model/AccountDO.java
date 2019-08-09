package com.jason.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * Created by BNC on 2019/7/2.
 */
@Entity
@Table(name = "security_account")
@org.hibernate.annotations.Table(appliesTo = "security_account", comment = "基础账号表")
public class AccountDO implements UserDetails {

    @Id
    @GenericGenerator(name = "accountIdGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "accountIdGenerator")
    @Column(name = "ACCOUNT_ID", columnDefinition = "CHAR(36) NOT NULL COMMENT '用户ID'")
    private String accountId;

    @Column(name = "USERNAME", unique = true, columnDefinition = "CHAR(32) NOT NULL COMMENT '用户名'")
    private String username;

    @Column(name = "PASSWORD", columnDefinition = "CHAR(64) NOT NULL COMMENT '密码'")
    private String password;

    @Column(name = "ACCOUNT_NON_EXPIRED", insertable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT 1 COMMENT '账号 0：过期；1：未过期'")
    private boolean accountNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED", insertable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT 1 COMMENT '账号 0：锁定；1：未锁定'")
    private boolean accountNonLocked;

    @Column(name = "CREDENTIALS_NON_EXPIRED", insertable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT 1 COMMENT '密码：0：过期；1：未过期'")
    private boolean credentialsNonExpired;

    @Column(name = "ENABLED", insertable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT 1 COMMENT '账号：0：不可用；1：可用'")
    private boolean enabled;

    @Column(name = "AUTHORITIES", columnDefinition = "VARCHAR(32) DEFAULT NULL COMMENT '权限'")
    private String authorities;

    @JsonIgnore
    @JsonIgnoreProperties(value = {"userId", "pageSize", "pageNum"})
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    @PrimaryKeyJoinColumn
    private UserDO userDO;

    @JsonIgnore
    @Transient
    private int pageNum;

    @JsonIgnore
    @Transient
    private int pageSize;

    public AccountDO() {
    }

    public AccountDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountDO(String username, String password, UserDO userDO) {
        this.username = username;
        this.password = password;
        this.userDO = userDO;
    }

    public AccountDO(String username, String password, UserDO userDO, int pageNum, int pageSize) {
        this.username = username;
        this.password = password;
        this.userDO = userDO;
        if (pageSize == 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
        if (pageNum > 0) {
            this.pageNum = pageNum - 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    public AccountDO(String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, String authorities) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "AccountDO{" +
                "accountId='" + accountId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}
