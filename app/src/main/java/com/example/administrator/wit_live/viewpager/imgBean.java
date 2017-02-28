package com.example.administrator.wit_live.viewpager;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15.
 */
public class imgBean {
    private String errcode;
    private String errmesg;
    private int count;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmesg() {
        return errmesg;
    }

    public void setErrmesg(String errmesg) {
        this.errmesg = errmesg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<imgs> getList() {
        return list;
    }

    public void setList(List<imgs> list) {
        this.list = list;
    }

    private List<imgs> list;

    public List<imgs> getlist() {
        return list;
    }

    public void setlist(List<imgs> list) {
        this.list = list;
    }

    public static class imgs{
        private String linkUrl;
        private String advertName;
        private String adImg;
        private int showSort;

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getAdvertName() {
            return advertName;
        }

        public void setAdvertName(String advertName) {
            this.advertName = advertName;
        }

        public String getAdImg() {
            return adImg;
        }

        public void setAdImg(String adImg) {
            this.adImg = adImg;
        }

        public int getShowSort() {
            return showSort;
        }

        public void setShowSort(int showSort) {
            this.showSort = showSort;
        }


    }
}
