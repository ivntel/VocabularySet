package com.example.geniusplaza.vocabularyset;

/**
 * Created by geniusplaza on 6/13/17.
 */

public class ApiConstants {
    private String clientId;
    private String clientSecret;
    private String base64 = "RXhXeEZCQ1VmQkZrR3p0YkJMT3lmNHZPRFhmQzZLT01ZdkdSa2h6VDoyVU92NVFQU0xMVHM0cWhzbFBxUTdkbUtuV3Nnc1FLQnN1S3BJUjNaeGVYdmsxRERCRGE2NFpaZGZ3bjdmdk1tZGI4VExYb1QxVjdmNVRTZ08yaExrdkhJUzljSG9ubG1USkJ4UUN6RTZRdTRJUmU1dUdyUkNHa2pTSHdvbTFVbw==";
    public static String refreshToken;
    public static String accessToken;
    public static String databaseResId = null;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}
