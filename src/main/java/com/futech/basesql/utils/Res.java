package com.futech.basesql.utils;

import java.util.List;
import java.util.Map;

public class Res {

    List<Map<String, String>> res;

    public Res(List<Map<String, String>> res) {
        this.res = res;
    }

    public List<Map<String, String>> getRes() {
        return res;
    }

    public void setRes(List<Map<String, String>> res) {
        this.res = res;
    }
}
