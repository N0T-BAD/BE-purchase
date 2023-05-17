package com.blockpage.purchaseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    //purchase
    NO_FOUND_PROFILE_SKIN_PRODUCT("요청하신 NFT는 존재 하지 않습니다.", HttpStatus.NO_CONTENT),
    NO_FOUND_NFT_PRODUCT("요청하신 프로필스킨이 존재 하지 않습니다.", HttpStatus.NO_CONTENT),
    PROFILE_SKIN_INTERNAL_ERROR("프로필스킨 기본값을 지정하는 중 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //feign client
    BLOCK_SERVICE_SERVER_UNAVAILABLE("블럭 서비스가 이용이 불가능합니다.", HttpStatus.NO_CONTENT),         //오류 메세지 검토 필요
    BLOCK_SERVICE_CLIENT_BAD_REQUEST("블럭 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.NO_CONTENT),     //오류 메세지 검토 필요

    MEMBER_SERVICE_SERVER_UNAVAILABLE("블럭 서비스가 이용이 불가능합니다.", HttpStatus.NO_CONTENT),         //오류 메세지 검토 필요
    MEMBER_SERVICE_CLIENT_BAD_REQUEST("블럭 서비스에 잘못된 요청을 보냈습니다.", HttpStatus.NO_CONTENT),     //오류 메세지 검토 필요

    UNKNOWN_ERROR("알수없는 에러가 발생하였습니다.", HttpStatus.NO_CONTENT),     //오류 메세지 검토 필요
    ;

    private final String message;
    private final HttpStatus httpStatus;

}
