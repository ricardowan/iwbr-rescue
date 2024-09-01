package cn.iwbr.rescue.canal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @description: 资源
 * @author: <a href="mailto:ricardomrwang@gmail.com">wangbaorui</a>
 * @date: 2024-09-01 18:34:14
 */
public class Resource {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;

    /**
     * 是否启用
     */
    protected boolean enabled = true;

    /**
     * 操作人
     */
    protected String operator;

    private String resTitle;

    private String resCatalog;

    private String resDisplayCatalog;

    private String resShareCatalog;

    private String resCode;

    private String resType;

    private String resCapableType;

    private String resRegisterOrg;

    private String resUrl;

    private String resProxyUrl;

    private String resKeywords;

    private String resYear;

    private String resRegionCode;

    private String resLegend;

    private String resUpdatePeriod;

    private String resDescription;

    private String resIcon;

    private String resSource;

    private String resCreator;

    private String resPublisher;

    private Integer resWeight;

    private String resRelationIds;

    private Boolean resDownloadAble;

    private String resShareType;

    private Boolean resTop;

    private Integer resPublishStatus;

    private boolean resCheck;

    private String resSr;

    private String resSpheroid;

    private Integer resHeat;

    private String approvalRole;

    private String serviceAuthId;

    private String dataDutyOrg;

    private String businessType;

    private String resToolProvider;

    private String resToolUseRange;

    private Date resPublishTime;

    private double resGrading;
}
