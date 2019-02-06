package com.icodev76.redditpicswithnavdrawer.model.children;

public class Children {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Children{" +
                "data=" + data +
                '}';
    }
}
