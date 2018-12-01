package com.fun.guptas.sahodar;

import android.util.Log;

/**
 * Created by guptas on 1/17/2018.
 */

public class ListItem {
    public String head;
    public String desc;
    public String url_a;
    public String paragraph;

    public ListItem(String head, String desc, String url_a, String paragraph) {
        this.head = head;
        this.desc = desc;
        this.url_a = url_a;
        this.paragraph = paragraph;
        Log.d("STORING", "DATA" + head + desc + url_a + paragraph);
    }

    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url_a;
    }

    public void setUrl(String url_a) {
        this.url_a = url_a;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
