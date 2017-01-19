package com.app.mvc.controller.serializable;

import java.io.Serializable;

/**
 * 此处请注意使用序列化，否则无法发送
 * Created by Administrator on 2017/1/18.
 */
public class EmpBean implements Serializable {


    private String empName;

    private Integer empAge;

    @Override
    public String toString() {
        return "EmpBean{" +
                "empName='" + empName + '\'' +
                ", empAge=" + empAge +
                '}';
    }

    public String getEmpName() {
        return empName;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }


}
