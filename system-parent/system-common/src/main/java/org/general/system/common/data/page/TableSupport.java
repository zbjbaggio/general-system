package org.general.system.common.data.page;

import org.general.system.common.constants.PageConstant;
import org.general.system.common.utils.ServletUtil;
/**
 * 表格数据处理
 *
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtil.getParameterToInt(PageConstant.PAGE_NUM));
        pageDomain.setPageSize(ServletUtil.getParameterToInt(PageConstant.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtil.getParameter(PageConstant.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtil.getParameter(PageConstant.IS_ASC));
        return pageDomain;
    }

}
