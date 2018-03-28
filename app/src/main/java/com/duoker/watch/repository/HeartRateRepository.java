package com.duoker.watch.repository;

import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.StartHeartRateMeasureBean;
import com.duoker.watch.model.StartHeartRateMeasureModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/8.
 */
public interface HeartRateRepository {
    List<HeartRateBean> downloadHeartRate(String userid, String watchid, long calcTime, String range) throws IOException;

    List<HeartRateBean> downloadHeartRateAfter(String userid, String watchid, long calcTime) throws IOException;

    List<HeartRateBean> downloadHeartRateBefore(String userid, String watchid, long calcTime) throws IOException;

    List<HeartRateBean> getHeartRate(String userid, String watchid, long beginTime, long endTime) throws IOException;

    StartHeartRateMeasureBean startHeartRate(StartHeartRateMeasureModel paramStartHeartRateMeasureModel) throws IOException;
}
