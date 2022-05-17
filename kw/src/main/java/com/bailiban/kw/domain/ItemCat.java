package com.bailiban.kw.domain;

public class ItemCat {
    /*
    * `cid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50)   NOT NULL,
  `status` INT(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  PRIMARY KEY (`cid`)
    * */
    private int cid;
    private String name;
    private int status;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemCat{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
