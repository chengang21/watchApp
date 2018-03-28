package com.duoker.watch.repository;

import com.duoker.watch.model.BindUserListBean;
import com.duoker.watch.model.DoFactorySettingModel;
import com.duoker.watch.model.DoPermissionToTransferModel;
import com.duoker.watch.model.DoUnbindWatchModel;
import com.duoker.watch.model.FactorySettingBean;
import com.duoker.watch.model.GetBindUserListModel;
import com.duoker.watch.model.PermissionToTransferBean;
import com.duoker.watch.model.UnbindWatchBean;

import java.io.IOException;

/**
 * Created by cheng on 2017/10/9.
 */
public interface DeviceBindRepository {
    FactorySettingBean factorySetting(DoFactorySettingModel paramDoFactorySettingModel) throws IOException, IOException;

    BindUserListBean getBindUserList(GetBindUserListModel paramGetBindUserListModel) throws IOException;

    PermissionToTransferBean permissionToTransfer(DoPermissionToTransferModel paramDoPermissionToTransferModel) throws IOException;

    UnbindWatchBean unbindWatch(DoUnbindWatchModel paramDoUnbindWatchModel) throws IOException;
}
