package com.duoker.watch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.model.iflytek.DatetimeBean;
import com.duoker.watch.model.iflytek.SemanticBean;
import com.duoker.watch.model.iflytek.SlotsBean;
import com.duoker.watch.model.iflytek.SpeechScheduleBean;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.event.ScheduleSpeech2TextEvent;
import com.duoker.watch.utils.GsonTools;
import com.duoker.watch.utils.SystemServiceUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstander;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.TextUnderstander;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by cheng on 2017/10/6.
 */
public class ScheduleCreateSpeechFragment extends BaseFragment {
    public static final String TAG = ScheduleCreateSpeechFragment.class.getSimpleName();
    private View mChangeTextView;
    private SpeechUnderstander mSpeechUnderstander;

    private ImageView mSpeechView;
    private TextUnderstander mTextUnderstander;

    private SpeechUnderstanderListener mSpeechUnderstanderListener = new SpeechUnderstanderListener() {
        @Override
        public void onBeginOfSpeech() {
            showShortToast(getString(R.string.lc_str_start_talk));
        }

        @Override
        public void onEndOfSpeech() {
            showShortToast(getString(R.string.lc_str_end_talk));
        }

        @Override
        public void onError(SpeechError paramSpeechError) {
            showShortToast(paramSpeechError.getPlainDescription(true));
        }

        @Override
        public void onEvent(int paramInt1, int paramInt2, int paramInt3, Bundle paramBundle) {
        }

        @Override
        public void onResult(UnderstanderResult paramUnderstanderResult) {
            if (paramUnderstanderResult != null) {
                String str1 = paramUnderstanderResult.getResultString();
                Log.d(ScheduleCreateSpeechFragment.TAG, str1);
                SpeechScheduleBean localSpeechScheduleBean = (SpeechScheduleBean) GsonTools.changeGsonToBean(str1, SpeechScheduleBean.class);
                if (localSpeechScheduleBean == null) {
                    showShortToast(getString(R.string.lc_str_remind_format));
                    return;
                }
                String text = localSpeechScheduleBean.getText();
                SemanticBean localSemanticBean = localSpeechScheduleBean.getSemantic();
                if (localSemanticBean == null) {
                    textUnderstander(text);
                    return;
                }
                SlotsBean localSlotsBean = localSemanticBean.getSlots();
                if (localSlotsBean == null) {
                    textUnderstander(text);
                    return;
                }
                DatetimeBean localDatetimeBean = localSlotsBean.getDatetime();
                String str3 = localDatetimeBean.getDate();
                String str4 = localDatetimeBean.getTime();
                String str5 = str3 + " " + str4;
                ScheduleModel localScheduleModel = new ScheduleModel();
                localScheduleModel.setUserId(DuokerWatchApp.getInstance().getLoginUser().getUserid());
                localScheduleModel.setWatchId(DuokerWatchApp.getInstance().getDefaultWatch().getWatchId());
                localScheduleModel.setScheduleid("-1");
                localScheduleModel.setSchedulerepeat(0);
                localScheduleModel.setSchedulestatus(1);
                localScheduleModel.setScheduleweek(0);
                localScheduleModel.setSchedulecontent(text);
                if (!TextUtils.isEmpty(str5))
                    localScheduleModel.setScheduletime(str5);
                ScheduleSpeech2TextEvent localScheduleSpeech2TextEvent = new ScheduleSpeech2TextEvent();
                localScheduleSpeech2TextEvent.scheduleModel = localScheduleModel;
                localScheduleSpeech2TextEvent.speech2TextFragment = true;
                EventBus.getDefault().post(localScheduleSpeech2TextEvent);
                return;
            }
            showShortToast(getString(R.string.lc_str_identification_not_correct));
        }

        @Override
        public void onVolumeChanged(int paramInt, byte[] paramArrayOfByte) {
            if (paramInt <= 5) {
                mSpeechView.setImageResource(R.drawable.hb_voice_1);
                return;
            }
            if (paramInt <= 10) {
                mSpeechView.setImageResource(R.drawable.hb_voice_2);
                return;
            }
            if (paramInt <= 15) {
                mSpeechView.setImageResource(R.drawable.hb_voice_3);
                return;
            }
            if (paramInt <= 20) {
                mSpeechView.setImageResource(R.drawable.hb_voice_4);
                return;
            }
            if (paramInt <= 25) {
                mSpeechView.setImageResource(R.drawable.hb_voice_5);
                return;
            }
            if (paramInt <= 30) {
                mSpeechView.setImageResource(R.drawable.hb_voice_6);
                return;
            }
            if (paramInt <= 35) {
                mSpeechView.setImageResource(R.drawable.hb_voice_7);
                return;
            }
            if (paramInt <= 40) {
                mSpeechView.setImageResource(R.drawable.hb_voice_8);
                return;
            }
            if (paramInt <= 45) {
                mSpeechView.setImageResource(R.drawable.hb_voice_9);
                return;
            }
            mSpeechView.setImageResource(R.drawable.hb_voice_9);
        }
    };

    private TextUnderstanderListener mTextUnderstanderListener = new TextUnderstanderListener() {
        @Override
        public void onResult(UnderstanderResult understanderResult) {
            if (understanderResult != null) {
                String str1 = understanderResult.getResultString();
                Log.d(ScheduleCreateSpeechFragment.TAG, str1);
                SpeechScheduleBean localSpeechScheduleBean = (SpeechScheduleBean) GsonTools.changeGsonToBean(str1, SpeechScheduleBean.class);
                if (localSpeechScheduleBean == null) {
                    showShortToast(getString(R.string.lc_str_remind_format));
                    return;
                }
                SemanticBean localSemanticBean = localSpeechScheduleBean.getSemantic();
                if (localSemanticBean == null) {
                    showShortToast(getString(R.string.lc_str_remind_format));
                    return;
                }
                SlotsBean localSlotsBean = localSemanticBean.getSlots();
                if (localSlotsBean == null) {
                    showShortToast(getString(R.string.lc_str_remind_format));
                    return;
                }
                DatetimeBean localDatetimeBean = localSlotsBean.getDatetime();
                String str2 = localDatetimeBean.getDate();
                String str3 = localDatetimeBean.getTime();
                String str4 = str2 + " " + str3;
                String str5 = localSpeechScheduleBean.getText();
                ScheduleModel scheduleModel = new ScheduleModel();
                scheduleModel.setUserId(DuokerWatchApp.getInstance().getLoginUser().getUserid());
                scheduleModel.setWatchId(DuokerWatchApp.getInstance().getDefaultWatch().getWatchId());
                scheduleModel.setScheduleid("-1");
                scheduleModel.setSchedulerepeat(0);
                scheduleModel.setSchedulestatus(1);
                scheduleModel.setScheduleweek(0);
                scheduleModel.setSchedulecontent(str5);
                if (!TextUtils.isEmpty(str4))
                    scheduleModel.setScheduletime(str4);
                ScheduleSpeech2TextEvent localScheduleSpeech2TextEvent = new ScheduleSpeech2TextEvent();
                localScheduleSpeech2TextEvent.scheduleModel = scheduleModel;
                localScheduleSpeech2TextEvent.speech2TextFragment = true;
                EventBus.getDefault().post(localScheduleSpeech2TextEvent);
                return;
            }
            showShortToast(getString(R.string.lc_str_identification_not_correct));
        }

        @Override
        public void onError(SpeechError speechError) {
            showShortToast(speechError.getPlainDescription(true));
        }
    };

    private void initListener() {
        this.mChangeTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ScheduleSpeech2TextEvent localScheduleSpeech2TextEvent = new ScheduleSpeech2TextEvent();
                localScheduleSpeech2TextEvent.speech2TextFragment = true;
                EventBus.getDefault().post(localScheduleSpeech2TextEvent);
            }
        });

        this.mSpeechView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    if (mSpeechUnderstander.isUnderstanding())
                        mSpeechUnderstander.stopUnderstanding();
                }
                else if (event.getAction() == 1) {
                    SystemServiceUtils.getInstance(getActivity()).vibratorOnce(100);
                    int i = mSpeechUnderstander.startUnderstanding(mSpeechUnderstanderListener);
                    if (i != 0)
                        showShortToast(getString(R.string.lc_str_semantic_fail_error_code) + i);
                    mSpeechView.setImageResource(R.drawable.hb_voice_9);
                    return true;
                }
                mSpeechView.setImageResource(R.drawable.hb_voice_9);
                mSpeechUnderstander.stopUnderstanding();
                return true;
            }
        });
    }

    public static ScheduleCreateSpeechFragment newInstance() {
        return new ScheduleCreateSpeechFragment();
    }

    private void textUnderstander(String paramString) {

        if (this.mTextUnderstander.isUnderstanding())
            this.mTextUnderstander.cancel();
        else {
            int error = this.mTextUnderstander.understandText(paramString, this.mTextUnderstanderListener);
            showShortToast(getString(R.string.lc_str_semantic_fail_error_code) + error);
        }
    }

    public void initSpeech() {
        this.mSpeechUnderstander = SpeechUnderstander.createUnderstander(getActivity(), null);
        this.mTextUnderstander = TextUnderstander.createTextUnderstander(getActivity(), null);

        if (getResources().getConfiguration().locale.getLanguage().contains("zh")) {
            this.mSpeechUnderstander.setParameter("language", "zh_cn");
            this.mSpeechUnderstander.setParameter("accent", "mandarin");
        }
        else {
            this.mSpeechUnderstander.setParameter("language", "en_us");
        }
        this.mSpeechUnderstander.setParameter("vad_bos", "3000");
        this.mSpeechUnderstander.setParameter("vad_eos", "3000");
        this.mSpeechUnderstander.setParameter("asr_ptt", "1");
        this.mSpeechUnderstander.setParameter("audio_format", "wav");
        this.mSpeechUnderstander.setParameter("asr_audio_path", getActivity().getCacheDir() + "/iflytek.wav");
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        return paramLayoutInflater.inflate(R.layout.fragment_schedule_create_speech, paramViewGroup, false);
    }

    public void onDestroyView() {
        super.onDestroyView();

        try {
            this.mSpeechUnderstander.cancel();
            this.mSpeechUnderstander.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        this.mChangeTextView = paramView.findViewById(R.id.change_text_view);
        this.mSpeechView = ((ImageView) paramView.findViewById(R.id.speech_view));
        initListener();
        initSpeech();
    }
}