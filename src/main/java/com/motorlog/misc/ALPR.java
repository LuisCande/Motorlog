package com.motorlog.misc;

import com.openalpr.api.invoker.*;
import com.openalpr.api.models.*;
import com.openalpr.api.DefaultApi;

// https://cloud.openalpr.com/cloudapi/
// email: motorlogapplication@gmail.com
// password: ISPP_SOBRESALIENTE

public class ALPR {

    private static String secret_key = "sk_9289ccd3bf5716fa4612eaed";

    // Consumes an image encoded in base64 and returns a license plate (if found).
    public static String read(String bytes) throws ApiException {
        DefaultApi apiInstance = new DefaultApi();
        String secretKey = secret_key;
        String country = "eu";
        Integer recognizeVehicle = 0;
        String state = "es";
        Integer returnImage = 0;
        Integer topn = 1;
        String prewarp = "";
        InlineResponse200 result = apiInstance.recognizeBytes(bytes, secretKey,
                country, recognizeVehicle, state, returnImage, topn, prewarp);
        if (!result.getResults().isEmpty())
            return result.getResults().get(0).getPlate();
        return "";
    }
}
