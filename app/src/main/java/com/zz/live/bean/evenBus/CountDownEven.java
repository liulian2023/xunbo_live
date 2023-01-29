package com.zz.live.bean.evenBus;

public class CountDownEven {
    String typeName;
    String qishu;
    long countTime;

    public CountDownEven(String typeName, String qishu, long countTime) {
        this.typeName = typeName;
        this.qishu = qishu;
        this.countTime = countTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

    public long getCountTime() {
        return countTime;
    }

    public void setCountTime(long countTime) {
        this.countTime = countTime;
    }
}
