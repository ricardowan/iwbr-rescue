package cn.iwbr.rescue.grammar.entity;

import lombok.Data;

/**
 * @description: 员工（测试实体）
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2024/01/12
 */
@Data
public class Staff extends Human {

    /**
     * 工号
     */
    private String workNo;

    /**
     * 职位
     */
    private String position;

    /**
     * 部门
     */
    private String department;

    /**
     * 工资
     */
    private double salary;

    /**
     * 检查
     */
    public boolean check;

    public Staff() {
    }

    public Staff(boolean check) {
        this.check = check;
    }

    public Staff(String workNo, String position, String department, double salary) {
        this.workNo = workNo;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }
}
