package com.bailiban.kw.domain;

import java.util.List;


public class PageInfo<T> {
    //页码大小
    private int pageSize;
    //总记录数
    private int totalCount;
    //总页数
    private int totalPages;
    //当前页
    private int currentPage;
    //上一页
    private int prePage;
    //下一页
    private int nextPage;
    //当前页数的集合
    private List<T> list;

    public PageInfo(){

    }

    public PageInfo(int totalCount,int currentPage,int pageSize){
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize =pageSize;
        //根据总记录数 和页码大小计算出总页数
        this.totalPages = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
        //根据当前页计算 上一页
        this.prePage = this.currentPage>1?this.currentPage-1:1;
        //根据当前页计算 下一页
        this.nextPage =this.currentPage<this.totalPages?this.currentPage+1:this.totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
