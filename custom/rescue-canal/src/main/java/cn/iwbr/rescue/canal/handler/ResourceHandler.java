package cn.iwbr.rescue.canal.handler;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.util.Map;

/**
 * @description: 资源处理程序
 * @author: <a href="mailto:ricardomrwang@gmail.com">wangbaorui</a>
 * @date: 2024-09-01 18:28:00
 */
@CanalTable(value = "all")
@Component
@Slf4j
public class ResourceHandler implements EntryHandler<Map<String, Object>> {

    @Override
    public void insert(Map resource) {
        log.info(JSON.toJSONString(resource));
    }

    @Override
    public void update(Map before, Map after) {
        log.info("before:{}",JSON.toJSONString(before));
        log.info("after:{}",JSON.toJSONString(after));
    }

    @Override
    public void delete(Map resource) {
        log.info(JSON.toJSONString(resource));
    }
}
