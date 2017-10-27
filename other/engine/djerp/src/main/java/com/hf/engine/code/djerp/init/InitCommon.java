package com.hf.engine.code.djerp.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HF on 2017/9/21.
 */
public class InitCommon extends AbstractInit {

    private static Logger logger = LoggerFactory.getLogger(InitCommon.class);

    /**
     * 模块配置名称
     */
    private final String modulName;

    public InitCommon(String modulName) {
        this.modulName = modulName;
    }

    @Override
    public void init() {
        String name = modulName;
        try {
            defaultInit(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
