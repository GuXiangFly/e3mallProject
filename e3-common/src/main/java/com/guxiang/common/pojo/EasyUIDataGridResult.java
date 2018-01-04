package com.guxiang.common.pojo;

import java.util.List;
import java.io.Serializable;

/**
 * EasyUIDataGridResult
 *
 * @author guxiang
 * @date 2017/7/16
 */
public class EasyUIDataGridResult implements Serializable{

    private long total;
    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
