package com.duoker.watch.repository;

import com.duoker.watch.model.ProjectCateStorage;

/**
 * Created by cheng on 2017/10/8.
 */
public interface ProjectCateRepository
{
    ProjectCateStorage getProjectCateStorage(String paramString) throws Exception;
}
