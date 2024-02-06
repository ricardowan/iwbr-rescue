package cn.iwbr.rescue.grammar.entity;

import lombok.Data;

/**
 * @description: 人类（测试实体）
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2024/01/12
 */
@Data
public class Human {

    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 肤色
     */
    private String color;

    /**
     * 测试
     */
    public String test;

}
