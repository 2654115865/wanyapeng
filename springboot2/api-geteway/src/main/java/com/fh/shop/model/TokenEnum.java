package com.fh.shop.model;

import com.fh.IEnum;

public enum TokenEnum implements IEnum {

        TOKEN_TIME_OUT(1001,"请求超时"),
        SIGN_IS_ERROR(1004,"签名无效"),
        APPKEY_IS_EMPTY(1003,"appKey无效"),
        ERROR_IS_ATTACK(1002,"被攻击了"),
        HEADER_IS_MISS(1000,"头信息丢失");


        private int code;

        private String msg;

        private TokenEnum(int code, String msg){
            this.code=code;
            this.msg=msg;
        }


        @Override
        public int getCode() {
                return this.code;
        }

        @Override
        public String getMsg() {
                return this.msg;
        }
}
