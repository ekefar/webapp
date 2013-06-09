package com.teamdev.webapp1.controller.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class TableResponse {
    public final List rows;
    public final long total;
    public final int page;

    public TableResponse(List rows, int total, int page) {
        this.rows = rows;
        this.total = total;
        this.page = page;
    }

    public TableResponse(Page page) {
        this.rows = page.getContent();
        this.page = page.getNumber() + 1;
        this.total = page.getTotalElements();
    }
}
