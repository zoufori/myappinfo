package com.bdqn.myappinfo.tools;

public class Pages {

    private int currentPageNo;  //当前页
    private int page;    //页面显示数据量
    private int totalPageCount;   //页面总数
    private int totalCount;    //总数据量

    public Pages(){
        this.currentPageNo = 1;
        this.page = MyConstant.PAGE;
        this.totalPageCount = 1;
        this.totalCount = 0;
    }

    public Pages(int currentPageNo, int page, int totalPageCount, int totalCount){
        this.currentPageNo = currentPageNo;
        this.page = page;
        this.totalPageCount = totalPageCount;
        this.totalCount = totalCount;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
