package org.general.system.common.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.general.system.common.data.page.PageDomain;
import org.general.system.common.data.page.TableDataInfo;
import org.general.system.common.data.page.TableSupport;
import org.general.system.common.utils.SqlUtil;

import java.util.List;

public class BaseController {

    protected void startPage() {
        PageDomain pageDomain = TableSupport.getPageDomain();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
