package org.general.system.common.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.general.system.common.constants.TimeZoneConstant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体父类
 */
@Data
public class BaseEntity implements Serializable {

    @Null(groups = {Insert.class})
    @NotNull(groups = {Update.class})
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = TimeZoneConstant.DEFAULT)
    private Date createTime;

    private Date updateTime;

    public BaseEntity() {
        createTime = new Date();
    }

    public interface Insert {

    }

    public interface Update {

    }

}
