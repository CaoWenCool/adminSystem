package com.demo.adminsystem.core.service.log;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 18:48
 * @version: V1.0
 * @detail:
 **/
@Component
public class SystemLogService {
    public Future<Boolean> addRequestLog() {
        return new AsyncResult<>(true);
    }
}
