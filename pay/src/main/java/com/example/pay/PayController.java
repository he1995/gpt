package com.example.pay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/pay")
public class PayController {

    @RequestMapping("begin")
    public String begin() {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用
            AlipayTradePagePayResponse response = Factory.Payment.Page().pay(
                    "Apple iPhone11 128G",
                    "2234567891",
                    "0.01",
                    ""
            );
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功: " + response.getBody());
                return response.getBody();
            } else {
                System.err.println("调用失败");
                return "fail";
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @GetMapping("query")
    public ResponseResult<String> query() {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用
            AlipayTradeQueryResponse response = Factory.Payment.Common().query("2234567890");

            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功: " + response.getBody());
                if (Objects.equals(response.getTradeStatus(), "TRADE_SUCCESS")) {}
                return ResponseResult.success();
            } else {
                System.err.println("调用失败");
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

        return ResponseResult.fail("error");
    }

    @GetMapping("close")
    public String close() {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用
            AlipayTradeCloseResponse response = Factory.Payment.Common().close("2234567890");
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功: " + response.getHttpBody());
                return response.getHttpBody();
            } else {
                System.err.println("调用失败");
                return "fail";
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = "2021005122659027";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/QeyfAypbjUKH6GYE7LxKYAUaUAI9Jvc5WCh5PJ5JY+N2fNmghr12JZ+hKd4mmdqkrKRM/34tJHj019qaiE8SonDZEaCxtxNV05vRDkd3CgzRolOeQE/d30UBSvgeOb+ivQegALzrL7g9QDGRJLFYGSODfxfAzPCyPmiww0jLxeK+1j+Lr7YcFRRz20tmODoOvA73ki1SdJ2KfG8EFyyz81ydNhMUEGNxc2F2zJhWoV8TXh4eCbouXiMXVGx3bR+fAOHOLSVA6DgRW119s3ZixK7XxQcwAurXqKP3tQKzqjrA6xLXYXJmayl4Gjsuj6SaBFyWCIrMI6ZMTxt8YyLJAgMBAAECggEABLIQpiLEwnSoYn/RKWPKh6Tt2ySFuYygWzcP31IerLyYFAuiqC9GrYTk82b8vkPeu1ZFvmIzbdZMZe56vnqEPnfgf2PQZyyQe/XzCGHqXUDQ6cCrfuIjU9YscoitqMhQLSWo70O1X6zPQCm4TdDxzbDoT00qfyBI9IdVuKlCu0X4la6UBy9iIVWc08o/5uhH4lFcVdyEHDE4BkzB4n+rg0dN+dXJum/Heel3GLbRbbr+QnAOTipWl66kOkcKgBZ5hoC80SnHZN8iM31ol7aE42f7WUQ57CqF2hmHSOIFbYziOHsVzsa7TbdP75lG9erl/CuJbPLRN19GcCGtTDgbNQKBgQDq8nKUznfWk+w4KwuZszGUQFjg8lre+wrWBhBoiUnBBOPtStnkfW4tYLzXIrX1O2Se6tXLnucReYl6baR9PrnbFc7vJmFIWol3Ka+il/yWw2ofodDYrnppD19BI0d8iD6icgUBvaK4lDteCw3W7jwtxGin2RSpHoHIv7ALW3EPtwKBgQDQZUNqLh4MSQksQAxctlaNNXLTxcG7X/PZGGyh4nNdiBvN94x11OwMv0K15Qv0UmEv8MuqINfCUavIfynYIXmS+m+hNZ+LBDKrfFZHDK1kAn8xTJ1HvcNfecmQRLUSaMkGq0pBzwn4RzClQUK3bqXlZsnAb0VRLkUjLC92SmhhfwKBgQDnJ3hiPa0cGddb/8XfB7Ws1Y5cazXKkpW7sqWsI/7DgCs8CNvN+TZQQRAqXkVFrJS34fZM6t8Oi1QedwdSDkfPbGp3Unx+Mk1zRzk9MGncbxDXZF5VAX0AJpaiQLKRrH5rVuDkMI6LKZQrV5bbMvNUWBxbn7G7Hn+0YhwzYFkhiwKBgBOBRtwLUPipZVhk7kNHNbalyAeWPNnC01oAdTp5rX6VnLScdOMaeLWAuUe5FnNZuor4KLfVtxwS9NchP9Ng3rYpBoW6NSKX0/5E9vZTc28m5KzClP3//udSyNDd/WyprhcXtS2PqUk5sI3nG817Z7+e7Uc7outEP2hZXF/f+wEVAoGBAMjYchqROjBCRrUBKHjmno+4HanvDC+8A7Iqmf/kEpTdT4gNxJXq+0s23GMFRaKR9EsAwABZ5OWNgeKy6WrqM58veOY9Mdl7XhzBJVnehoVAYXKOF0hIqwsBkLkjIGSgiN5h/laxEmGp1FFH4T6ZzBOCfZFe8L+lAjffuX27fcFZ";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv9TKbhMcP9dHv0sXeh77pIKILtlDtyD1EsinRa+lCgSrXBtwCF8NSqtCegS++TVxvbGzQnKVulujX5jFyI6UMn9Dd1srDF02doh3WFVWtm9+s2lPGtxF7bn63iVq98DGOU+18ciW+rXiifrQvOcOYGLE9GsZJqx3ApOqGUPrbOwDh1qNtSWHtvf3ibHouTQXscnYmEK7O5ft6IB4nYTAHIYjzuBgb7BOm/Fcb1uJpkpl1d070Tsy0fv4xNh34A41MbWIrGfndBH4DAToXPwqMlCv5nUEdrADXNAJRlFqZdZmzgvOz5pXwj0pPMi8Wj0fp/2Ip+P0XqNziWVpuUJziQIDAQAB";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "http://localhost:8080/pay/notify";

        return config;
    }

}