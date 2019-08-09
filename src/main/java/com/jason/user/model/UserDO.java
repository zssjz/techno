package com.jason.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jason.user.model.enums.GenderEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by BNC on 2019/7/2.
 */
@Entity
@Table(name = "security_user")
@org.hibernate.annotations.Table(appliesTo = "security_user", comment = "基本用户表")
public class UserDO {

    @Id
    @GenericGenerator(name = "userIdGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "accountDO"))
    @GeneratedValue(generator = "userIdGenerator")
    @Column(name = "USER_ID", columnDefinition = "CHAR(36) NOT NULL COMMENT '用户ID'")
    private String userId;

    @Column(name = "NICKNAME", unique = true, columnDefinition = "VARCHAR(32) NOT NULL COMMENT '昵称'")
    private String nickname;

    @Column(name = "AGE", columnDefinition = "SMALLINT DEFAULT NULL COMMENT '年龄'")
    private Integer age;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "GENDER", columnDefinition = "TINYINT NOT NULL COMMENT '性别'")
    private GenderEnum gender;

    @Column(name = "CREATE_TIME", columnDefinition = "BIGINT NOT NULL COMMENT '创建时间'")
    private Long createTime;

    @JsonIgnoreProperties(value = {"accountId", "password", "pageSize", "pageNum"})
    @OneToOne(mappedBy = "userDO")
    private AccountDO accountDO;

    @JsonIgnore
    @Transient
    private int pageNum;

    @JsonIgnore
    @Transient
    private int pageSize;

    public UserDO() {
    }

    public UserDO(String nickname, Integer age, GenderEnum gender) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }

    public UserDO(String nickname, Integer age, GenderEnum gender, AccountDO accountDO) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.accountDO = accountDO;
    }

    public UserDO(String nickname, Integer age, GenderEnum gender, Long createTime, AccountDO accountDO, int pageNum, int pageSize) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
        this.accountDO = accountDO;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public AccountDO getAccountDO() {
        return accountDO;
    }

    public void setAccountDO(AccountDO accountDO) {
        this.accountDO = accountDO;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", createTime=" + createTime +
                '}';
    }
}
