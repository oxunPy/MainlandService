package uz.akfa.regionservice.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum RegionType {
    SIMPLE("code1"), CAPITAL("code2");

    private String code;

    private RegionType(String code){
        this.code = code;
    }

//    @JsonCreator
    public static RegionType decode(final String code){
        return Stream.of(RegionType.values())
                .filter(regionType -> regionType.code.equals(code))
                .findFirst()
                .orElse(null);
    }

//    @JsonValue
    public String getCode(){
        return code;
    }
}
