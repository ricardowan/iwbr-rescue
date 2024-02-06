package cn.iwbr.rescue.grammar.web;

import cn.iwbr.rescue.grammar.entity.Staff;
import cn.iwbr.rescue.grammar.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Aop测试接口
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/02/06
 */
@Slf4j
@RequestMapping("/rest/aop/test")
@RestController
public class AopTestController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/addStaff")
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

}
