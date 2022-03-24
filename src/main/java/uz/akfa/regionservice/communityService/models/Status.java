package uz.akfa.regionservice.communityService.models;

import java.util.stream.Stream;

public enum Status {
    MARRIED("code1"), NOT_MARRIED("code2"), GRADUATED("code3"), STUDYING("code4");

    private String code;

    private Status(String code){
        this.code = code;
    }


}
