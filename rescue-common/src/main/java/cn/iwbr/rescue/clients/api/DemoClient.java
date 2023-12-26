package cn.iwbr.rescue.clients.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 示例client
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2023/12/26
 */
@FeignClient(name = "${app.services.rescue-api:rescue-api}")
@RequestMapping("/rescue-api/demo/task")
public interface DemoClient {

}
