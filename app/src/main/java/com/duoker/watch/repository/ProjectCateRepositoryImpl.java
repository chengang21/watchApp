package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.model.ProjectCateStorage;
import com.duoker.watch.utils.GsonTools;
import com.duoker.watch.utils.IOUtils;

import java.util.Iterator;
import java.util.List;

public class ProjectCateRepositoryImpl implements ProjectCateRepository {
    private static ProjectCateStorage mProjectCateStorage;
    private final Context mContext;

    public ProjectCateRepositoryImpl(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    public ProjectCateStorage getProjectCateStorage(String paramString) throws Exception {
        if ((mProjectCateStorage != null) && (TextUtils.equals(mProjectCateStorage.getProjectCate(), paramString)))
            return mProjectCateStorage;

        List list = GsonTools.fromJsonArray(IOUtils.readStreamAsString(this.mContext.getAssets().open("projectcate.json"), "UTF-8"), ProjectCateStorage.class);
        ProjectCateStorage projectCateStorage = null;
        if (list != null && !list.isEmpty()) {

            Iterator iterator = list.iterator();
            ProjectCateStorage item;
            while (iterator.hasNext())

            {

                projectCateStorage = null;

                item = (ProjectCateStorage) iterator.next();
                if (TextUtils.equals(item.getProjectCate(), paramString)) {
                    projectCateStorage = item;
                    break;
                }
            }
        }
        if (projectCateStorage == null)
            projectCateStorage = new ProjectCateStorage(paramString);
        mProjectCateStorage = (ProjectCateStorage) projectCateStorage;
        return projectCateStorage;
    }
}
