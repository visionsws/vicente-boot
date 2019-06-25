package com.vicente.vicenteboot.enums;

public enum TaskInstEnum {

    PREPARE("准备中", 1),
    TOBERUN("待执行", 2),
    RUNNING("执行中", 3),
    STOP("停止中", 4),
    COMPLETE("执行完毕", 5),
    ERROR("异常", 6);

    private String name;
    private  int value;


    private TaskInstEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static TaskInstEnum match(int value){
        for (TaskInstEnum item: TaskInstEnum.values()) {
            if (item.value==(value)) {
                return item;
            }
        }
        return null;
    }

}
