package com.demo.adminsystem.core.util.filterDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:42
 * @version: V1.0
 * @detail: 可无需继承，jackson过滤属性也能生效
 **/
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = {"autoMark"})
public class BaseEntity {
    @JsonView(FilterView.OutputAutoMark.class)
    String autoMark;
}
