package com.alphapay.api.model.beans;

public class Pagination {
    private Long page;
    private Long pageLimit;
    private Long totalPage;
    private Long total;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(Long pageLimit) {
        this.pageLimit = pageLimit;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
