package com.hf.adminService.service.process.impl;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by HF on 2017/11/27.
 */
public class HelloActiviti implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Hello Activiti");
    }
}
