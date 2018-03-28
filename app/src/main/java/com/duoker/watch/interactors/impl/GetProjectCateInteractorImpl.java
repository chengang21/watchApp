package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ProjectCateStorage;
import com.duoker.watch.repository.ProjectCateRepository;

/**
 * Created by cheng on 2017/10/8.
 */
public class GetProjectCateInteractorImpl extends SimpleInteractor<ProjectCateStorage>
{
    private final String mProjectCate;
    private final ProjectCateRepository mProjectCateRepository;

    public GetProjectCateInteractorImpl(String paramString, ProjectCateRepository paramProjectCateRepository)
    {
        this.mProjectCate = paramString;
        this.mProjectCateRepository = paramProjectCateRepository;
    }

    public void run()
    {
        try
        {
            postObject2UiThread(this.mProjectCateRepository.getProjectCateStorage(this.mProjectCate));
            return;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            postException2UiThread(localException);
        }
    }
}
