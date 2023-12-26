package cn.iwbr.rescue.entity.server;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 资源查询用dto
 * @program: tip
 * @author: JamesWan
 * @create: 2022/05/17
 */
@Data
public class ResourceDto {

    private String id;

    private Date createAt;

    private String resTitle;

    private String resCatalog;

    private String catalogPath;

    private String resDisplayCatalog;

    private String displayCatalogPath;

    private String resCode;

    private String resType;

    private String resCapableType;

    private String resRegisterOrg;

    private String resUrl;

    private String resProxyUrl;

    private String resProxyDataUrl;

    private String resKeywords;

    private String resYear;

    private String resRegionCode;

    private String resLegend;

    private String resUpdatePeriod;

    private String resDescription;

    private String resIcon;

    private String resSource;

    private Boolean resTop;

    private String resSr;

    private String resSpheroid;

    private Integer resWeight;

    private String resRelationIds;

    private String resCreator;

    private String resPublisher;

    private Boolean resDownloadAble;

    private String resShareType;

    private String resShareTypeName;

    private String resShareCatalog;

    private Integer resPublishStatus;

    private boolean resCheck;

    private Boolean isCreator;

    private String token;

    private Map creatorInfo;

    private List<ResourceDto> relationList;

    private List<Map> keyWordsInfo;

    private String resStatus = "up";

    private Map authInfo;

    private String fillCatalogName;

    private String fillDisplayCatalogName;

    private String resTypeName;

    private String resCapableTypeName;

    private Integer resHeat;

    private String approvalRole;

    private String serviceAuthId;

    private String dataDutyOrg;

    private String businessType;
}
