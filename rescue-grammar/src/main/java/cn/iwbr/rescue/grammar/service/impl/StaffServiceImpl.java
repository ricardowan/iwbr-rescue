package cn.iwbr.rescue.grammar.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.iwbr.rescue.grammar.annotation.MyAnnotation;
import cn.iwbr.rescue.grammar.entity.Staff;
import cn.iwbr.rescue.grammar.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    @MyAnnotation(requestParamsType = MyAnnotation.BODY, bodyDataType = Staff.class, paramName = "name")
    @Override
    public Staff addStaff(Staff staff) {
        staff.setId(IdUtil.randomUUID());
        return staff;
    }
}
