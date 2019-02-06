package com.icodev76.redditpicswithnavdrawer.model.children;

public class Data {

    private String url;
    private int created_utc;
    private String thumbnail;
    private boolean is_reddit_media_domain;

    public boolean isIs_reddit_media_domain() {
        return is_reddit_media_domain;
    }

    public void setIs_reddit_media_domain(boolean is_reddit_media_domain) {
        this.is_reddit_media_domain = is_reddit_media_domain;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(int created_utc) {
        this.created_utc = created_utc;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Data{" +
                "url='" + url + '\'' +
                ", created_utc=" + created_utc +
                ", thumbnail='" + thumbnail + '\'' +
                ", is_reddit_media_domain=" + is_reddit_media_domain +
                '}';
    }
}
