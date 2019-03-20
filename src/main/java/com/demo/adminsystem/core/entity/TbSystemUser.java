package com.demo.adminsystem.core.entity;

import java.util.Date;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:22
 * @version: V1.0
 * @detail:
 **/
public class TbSystemUser {
    private Long id ;
    private Integer age ;
    private Integer inUse ;
    private Integer isAdmin ;
    private String department ;
    private String engishName ;
    private String imgUrl ;
    private String jobNo ;
    private String name ;
    private String nickName ;
    private String password ;
    private String position ;
    private String remark ;
    private Long roleId ;
    private String roleName ;
    private Date createTime ;
    private Date updateTime ;

    public TbSystemUser() {
    }

    public Long getId(){
        return  id;
    }
    public void setId(Long id ){
        this.id = id;
    }

    public Integer getAge(){
        return  age;
    }
    public void setAge(Integer age ){
        this.age = age;
    }

    public Integer getInUse(){
        return  inUse;
    }
    public void setInUse(Integer inUse ){
        this.inUse = inUse;
    }

    public Integer getIsAdmin(){
        return  isAdmin;
    }
    public void setIsAdmin(Integer isAdmin ){
        this.isAdmin = isAdmin;
    }

    public String getDepartment(){
        return  department;
    }
    public void setDepartment(String department ){
        this.department = department;
    }

    public String getEngishName(){
        return  engishName;
    }
    public void setEngishName(String engishName ){
        this.engishName = engishName;
    }

    public String getImgUrl(){
        return  imgUrl;
    }
    public void setImgUrl(String imgUrl ){
        this.imgUrl = imgUrl;
    }

    public String getJobNo(){
        return  jobNo;
    }
    public void setJobNo(String jobNo ){
        this.jobNo = jobNo;
    }

    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }

    public String getNickName(){
        return  nickName;
    }
    public void setNickName(String nickName ){
        this.nickName = nickName;
    }

    public String getPassword(){
        return  password;
    }
    public void setPassword(String password ){
        this.password = password;
    }

    public String getPosition(){
        return  position;
    }
    public void setPosition(String position ){
        this.position = position;
    }

    public String getRemark(){
        return  remark;
    }
    public void setRemark(String remark ){
        this.remark = remark;
    }

    public Long getRoleId(){
        return  roleId;
    }
    public void setRoleId(Long roleId ){
        this.roleId = roleId;
    }

    public String getRoleName(){
        return  roleName;
    }
    public void setRoleName(String roleName ){
        this.roleName = roleName;
    }

    public Date getCreateTime(){
        return  createTime;
    }
    public void setCreateTime(Date createTime ){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return  updateTime;
    }
    public void setUpdateTime(Date updateTime ){
        this.updateTime = updateTime;
    }
}
