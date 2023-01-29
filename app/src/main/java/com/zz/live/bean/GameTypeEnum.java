package com.zz.live.bean;

public enum GameTypeEnum {

    KUAISAN(1,"快三"),
    SSC(2,"时时彩"),
    RACE(3,"赛车"),
    MARKSIX(4,"六合彩"),
    DANDAN(5,"PC蛋蛋"),
    HAPPY8(6,"快乐8"),
    LUCKFARM(7,"幸运农场"),
    HAPPY10(8,"快乐10"),
    XUANWU(9,"11选五"),
    LIVESHOP(1212,"直播购彩");



    /**
     * 状态值
     */
    private int value;
    /**
     * 类型描述
     */
    private String description;

    GameTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static GameTypeEnum valueOf(int value){
        for (GameTypeEnum typeEnume:GameTypeEnum.values()){
            if (typeEnume.getValue()==value){
                return typeEnume;
            }
        }
        return null;
    }


}
