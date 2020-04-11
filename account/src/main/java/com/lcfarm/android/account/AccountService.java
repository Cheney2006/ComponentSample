package com.lcfarm.android.account;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lcfarm.android.base.AccountExportService;
import com.lcfarm.android.base.constants.ARouterConstant;

@Route(path = ARouterConstant.SERVICE_ACCOUNT,name = "测试服务")
public class AccountService implements AccountExportService {
    @Override
    public String getAccount() {
        return "Andy";
    }

    @Override
    public void init(Context context) {

    }
}
