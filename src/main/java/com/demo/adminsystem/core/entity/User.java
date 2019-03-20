package com.demo.adminsystem.core.entity;

import com.demo.adminsystem.core.util.filterDto.FilterView;
import com.fasterxml.jackson.annotation.JsonView;
import io.github.biezhi.excel.plus.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:38
 * @version: V1.0
 * @detail:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "User", description = "用户")
public class User implements Serializable{

    private static final long serialVersionUID = -8548722610287071619L;

    @ApiModelProperty(value = "主键")
    @ExcelField(order = 0, columnName = "ID")
    Integer id;
    @ApiModelProperty(value = "姓名")
    @ExcelField(order = 1, columnName = "姓名")
    String name;
    @ApiModelProperty(value = "创建时间")
    @ExcelField(order = 2, columnName = "创建时间", datePattern = "yyyy年MM月dd日")
    Date createTime;

    @JsonView(FilterView.OutputA.class)
    @ApiModelProperty(value = "密码")
    String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
