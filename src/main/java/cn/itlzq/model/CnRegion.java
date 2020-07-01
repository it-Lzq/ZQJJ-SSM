package cn.itlzq.model;

public class CnRegion {

    //id
    private int id;
    //地区码
    private String code;
    //地区名称
    private String name;
    //地区简称
    private String shortName;
    //上级地区编码  如果是000000 表示无
    private String parentCode;
    //经度
    private String lng;
    //纬度
    private String lat;
    //排序
    private int sort;
    //地区级别  - 1省份  2市  3县/区 4街道
    private int level;

    public CnRegion() {
    }

    @Override
    public String toString() {
        return "CnRegion{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", sort=" + sort +
                ", level=" + level +
                '}';
    }

    public CnRegion(int id, String code, String name, String shortName, String parentCode, String lng, String lat, int sort, int level) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.parentCode = parentCode;
        this.lng = lng;
        this.lat = lat;
        this.sort = sort;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
