package com.duoker.watch.db.model;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class ClasssetBean implements Serializable
{
    private int   classenable;
    private int    classweek;
    private String classmorningbegin;
    private String classmorningend;
    private String classafternoonbegin;
    private String classafternoonend;

    public String getClassafternoonbegin() {
        return classafternoonbegin;
    }

    public void setClassafternoonbegin(String classafternoonbegin) {
        this.classafternoonbegin = classafternoonbegin;
    }

    public String getClassafternoonend() {
        return classafternoonend;
    }

    public void setClassafternoonend(String classafternoonend) {
        this.classafternoonend = classafternoonend;
    }

    public int getClassenable() {
        return classenable;
    }

    public void setClassenable(int classenable) {
        this.classenable = classenable;
    }

    public String getClassmorningbegin() {
        return classmorningbegin;
    }

    public void setClassmorningbegin(String classmorningbegin) {
        this.classmorningbegin = classmorningbegin;
    }

    public String getClassmorningend() {
        return classmorningend;
    }

    public void setClassmorningend(String classmorningend) {
        this.classmorningend = classmorningend;
    }

    public int getClassweek() {
        return classweek;
    }

    public void setClassweek(int classweek) {
        this.classweek = classweek;
    }
}
