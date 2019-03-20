package com.demo.adminsystem.core.entity;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 19:05
 * @version: V1.0
 * @detail:
 **/
public class TbSystemRolePermission {
    private Long id ;
    private Integer isMenu ;
    private Integer isPoint ;
    private Long permissionId ;
    private Long roleId ;

    public TbSystemRolePermission() {
    }

    public Long getId(){
        return  id;
    }
    public void setId(Long id ){
        this.id = id;
    }

    public Integer getIsMenu(){
        return  isMenu;
    }
    public void setIsMenu(Integer isMenu ){
        this.isMenu = isMenu;
    }

    public Integer getIsPoint(){
        return  isPoint;
    }
    public void setIsPoint(Integer isPoint ){
        this.isPoint = isPoint;
    }

    public Long getPermissionId(){
        return  permissionId;
    }
    public void setPermissionId(Long permissionId ){
        this.permissionId = permissionId;
    }

    public Long getRoleId(){
        return  roleId;
    }
    public void setRoleId(Long roleId ){
        this.roleId = roleId;
    }

}
