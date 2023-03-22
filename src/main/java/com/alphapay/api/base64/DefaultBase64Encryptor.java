package com.alphapay.api.base64;

import java.util.Base64;

public class DefaultBase64Encryptor implements Base64Encryptor{

    @Override
    public String encodeToString(byte[] src) {
        return Base64.getEncoder().encodeToString(src);
//        return DatatypeConverter.printBase64Binary(src);
    }

    @Override
    public byte[] decode(String src) {
        return Base64.getDecoder().decode(src);
//        return DatatypeConverter.parseBase64Binary(src);
    }

}
