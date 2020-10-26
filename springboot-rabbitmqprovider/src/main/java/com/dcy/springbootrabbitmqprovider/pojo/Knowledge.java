package com.dcy.springbootrabbitmqprovider.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Knowledge
 * @Description TODO
 * @Author Mr.Dong
 * @Date 2020/10/26 13:54
 * @Version 1.0
 */
public class Knowledge implements Serializable {


    private Long id;
    private String knowledgename;
    private String summer;
    private String keyword;
    private Integer version;
    private Integer param4;
    private Integer param5;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnowledgename() {
        return knowledgename;
    }

    public void setKnowledgename(String knowledgename) {
        this.knowledgename = knowledgename;
    }

    public String getSummer() {
        return summer;
    }

    public void setSummer(String summer) {
        this.summer = summer;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getParam4() {
        return param4;
    }

    public void setParam4(Integer param4) {
        this.param4 = param4;
    }

    public Integer getParam5() {
        return param5;
    }

    public void setParam5(Integer param5) {
        this.param5 = param5;
    }

}
