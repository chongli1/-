package com.xiexin.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

/**
 * 阿里云的 短信发送 工具lei
 */
public class AliSMSUtil {
    public static void sendMsg(String phoneNum, Integer codeNum){
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI5tM3zGxQ7YoQ7afNgzKL", "LsIpTyKYcLFalwPWkQErQIMi7qbMZX");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);  //此手机号要从前端传进来
        request.putQueryParameter("SignName", "快速指定文件夹清理");  //这个签名要和 自己的阿里云中的 签名一样
        request.putQueryParameter("TemplateCode", "SMS_168825399");  //这个是模板，也要和 自己的阿里云中的 模板一样
        //随机的六位数字
//        int i = new Random().nextInt(999999);// 0-999998  如果 i<100000 那么 + 10000
//        if (i<100000){
//            i = i+100000;
//        }
//        System.out.println("i = " + i);
        request.putQueryParameter("TemplateParam", "{\"code\":\"codeNum\"}");  //下面这个是 验证码，需要自己写个随机数字
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //随机的六位数字
        int codeNum = new Random().nextInt(999999);// 0-999998  如果 i<100000 那么 + 100000
        if (codeNum<100000){
            codeNum = codeNum+100000;
        }
        System.out.println("codeNum = " + codeNum);
    }
}
