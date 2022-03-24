package uz.akfa.regionservice.communityService.models;

import java.util.stream.Stream;

public enum GenderType {
    MALE("code1"), FEMALE("code2"), OTHER("code3");

    private String code;

    private GenderType(String code){
        this.code = code;
    }

}
