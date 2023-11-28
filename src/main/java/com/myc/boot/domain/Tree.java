package com.myc.boot.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_tree")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree {

    @TableId
    public Long id;

    public Long pid;

    public String name;
}
