package com.duoker.watch.model.iflytek;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */


public class SpeechScheduleBean implements Serializable
{
    private String operation;
    private int rc;
    private SemanticBean semantic;
    private String service;
    private String text;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public SemanticBean getSemantic() {
        return semantic;
    }

    public void setSemantic(SemanticBean semantic) {
        this.semantic = semantic;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
