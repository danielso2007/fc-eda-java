package com.estudoeda.balances.application.pagination;

public final class PageRequest {
    private final int page;
    private final int size;
    private final String sort;

    public PageRequest(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }
}
