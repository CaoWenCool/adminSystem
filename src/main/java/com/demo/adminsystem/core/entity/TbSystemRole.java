package com.demo.adminsystem.core.entity;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:21
 * @version: V1.0
 * @detail:
 **/
public class TbSystemRole {
    private Long id ;
    private String extraTable ;
    private String name ;
    private String remark ;

    public TbSystemRole() {
    }

    public Long getId(){
        return  id;
    }
    public void setId(Long id ){
        this.id = id;
    }

    public String getExtraTable(){
        return  extraTable;
    }
    public void setExtraTable(String extraTable ){
        this.extraTable = extraTable;
    }

    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }

    public String getRemark(){
        return  remark;
    }
    public void setRemark(String remark ){
        this.remark = remark;
    }
}
