package org.general.system.generator.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource(value = { "classpath:generator.yml" })
@Data
public class GenConstant {

    private String author;

    /** 生成包路径 */
    private String packageName;

    /** 生成包路径 */
    private String moduleName;

    /** 自动去除表前缀，默认是true */
    private String autoRemovePrefix;

    /** 表前缀(类名不会包含表前缀) */
    private String tablePrefix;

}
