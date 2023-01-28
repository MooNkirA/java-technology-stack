package com.moon.sample.utils;

// import com.alibaba.fastjson.JSON;
// import com.taobao.api.ApiException;
// import com.taobao.api.DefaultTaobaoClient;
// import com.taobao.api.TaobaoClient;
// import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
// import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
//
// import java.util.HashMap;
// import java.util.Map;

public class AlibabaPhoneCodeGen {
    // public static void sendPhoneCode(String phone, String code) throws ApiException {
    //     String url = "http://gw.api.taobao.com/router/rest";
    //     TaobaoClient client = new DefaultTaobaoClient(url, "24488246", "7c19b90635bf0f630cf18c544cb91");
    //     AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
    //     req.setExtend("");
    //     req.setSmsType("normal");
    //     req.setSmsFreeSignName("黑马小王");
    //
    //     Map map = new HashMap();
    //     map.put("code", code);//RandomCode.genCode()+""
	// 	/*map.put("sn",  "000006" );
	// 	map.put("point", "100");
	// 	map.put("allpoint", "450");*/
    //
    //     String jsonString = JSON.toJSONString(map);
    //
    //     req.setSmsParamString(jsonString);
    //     req.setRecNum(phone);
    //     req.setSmsTemplateCode("SMS_75900094");
    //     AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
    //     System.out.println(rsp.getBody());
    // }
}
