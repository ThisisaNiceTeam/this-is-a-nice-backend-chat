package com.thisisaniceteam.chat.common.client.naver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverProfileResponse {
    private String resultcode;
    private String message;
    private NaverProfileDetailResponse response;
//    {
//        "resultcode": "00",
//            "message": "success",
//            "response": {
//        "email": "openapi@naver.com",
//                "nickname": "OpenAPI",
//                "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//                "age": "40-49",
//                "gender": "F",
//                "id": "32742776",
//                "name": "오픈 API",
//                "birthday": "10-01",
//                "birthyear": "1900",
//                "mobile": "010-0000-0000"
//    }
//    }

}
