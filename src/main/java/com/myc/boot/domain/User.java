package com.myc.boot.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(User)实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {

    /**
     * `serialVersionUID` 是 Java 中用于控制序列化版本的一个标识符。在 Java 中，当一个实现了 `Serializable` 接口的类被序列化时，会生成一个 `serialVersionUID`。这个 ID 的作用是在反序列化的过程中确保序列化和反序列化的类是兼容的。
     * `serialVersionUID` 是一个 `long` 类型的常量，用于标识序列化类的版本。如果不显式地为一个可序列化的类指定 `serialVersionUID`，Java 运行时会根据类的结构（字段、方法等）自动生成一个 `serialVersionUID`。但是如果类的结构发生了变化（比如增加或删除字段、修改方法等），这个自动生成的 `serialVersionUID` 可能会发生变化，导致在反序列化时可能出现版本不兼容的情况。
     * 为了确保序列化和反序列化过程中的兼容性，开发者可以显式地定义 `serialVersionUID`。当类的结构发生变化时，如果 `serialVersionUID` 没有变化，Java 序列化机制会根据这个 ID 来判断版本的兼容性。如果 `serialVersionUID` 发生了变化，反序列化时会抛出 `InvalidClassException` 异常。
     * 所以，一般来说，为了提供类的版本控制和防止出现不兼容的情况，特别是在分布式系统中，会显式地为可序列化的类指定一个固定的 `serialVersionUID`。
     */
    private static final long serialVersionUID = -40356785423868312L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;
}