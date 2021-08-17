package com.liuwohe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class Organization implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String text;

    private Integer parentId;

    private List<Organization> nodes;
}