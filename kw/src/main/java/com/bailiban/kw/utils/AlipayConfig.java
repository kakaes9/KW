package com.bailiban.kw.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117632025";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDGvp2irSk+Fxmu5lKMkE4aHmUOhzaPSGqQ9Q35XdHXh82lkTao8ryGp1vbPtR2loPspd1pbdD04Zu8YHYcuEGWkWkBrsW3OXPXrYPXBsp+8dGkI8czqUnDenx82ZOIDsYbqqg28YJqf7pl5Z3osG4GE+1VuutONqQnv2kKk0+jd6Lv5Smdvjksci8rxQnjo8ZEmSICOTLrnLOeOQNnkSzMN6DpJdBnvWBJ6LMssAZzg+ECmMo7hUpc7mONkRyRst1wtNVlzmD8N7wad4khxc4CCmatbTkr2mlzI7yhBfnIfFm3AC29HkiQ7oazSDpScOm8rTzI6n4Y+AbcRW2t+NY7AgMBAAECggEAZbV5MJ5Tg+4Ie8RtLHhRL/asGvJBjrQtrT9rxlkoIZfjOz8EbRPEPVj6nkoaKxI+M2Zy2BiWHiwzKgVnjK0TC74gsfbCQlFJom+vD+TuzmZstgaFVD/nev19JdunL7L32YLNkUipSF1ThAAtfr6Dn+KkzAHbl+xIt/qyA/J9iBazv2mP21qkfxv69wOYUbnNgYweIrpN8TlZYzwx/oCjCzxDU83Icg5FWraKpcjLSTmaZ6RVU8Gw9GwknFXW9sTdhm6Eaatuh4HekchSrLnF9ZnwqR1aSq5OMuMcPrX2cj9JQrj/WU5TKUr6NE2R73O2kK1FmP1gVS/c3kiolrz3UQKBgQDnUq15N1SZ0HkYI4/ooZEKVl+2CyqPRGsO1I4UXCr+mXVCsh9Te7Dg1LjO4bQAabeBLcd7uXLATf9O2vWtjlNSmC9OWARxwO3fKM9EuN0ts+3VOerAX0OLkr8TnamMsE4UlUhVpaMmDk96bzjlsRW31gQoG7NEwG/lYo1qozzSeQKBgQDb8jz0Jn2nAmO8gX9tEsN0/NgcvJfpdalqIc7cL5jhtbzQikLmiDxIzHRyoPslMCGyoZZpUmHDbxuWi4XzzkmkLViChrAE7rJpDmVgaCwqHzsmtlXsUqwInoyl4o+Up3mt5K0DMUKCpkviNNdywdg03gN9jP1jFAhXGmcWu1chUwKBgDvIIS7nfsS1nOYBIsrjHmHGiru4TQpzixttET7vxui/bg+Liw7/M2oXb59/sw48RAFtfFVcSuyp+6Y8biupUZ1R2T/s/VEMpCz9FfvTrs2F55woeKO4uvMSPLFsGIpUcgldvzDrOqu2HRIuNk0eI9e7xp0682x8My7b9mItx6U5AoGBAIVLy5ET+rbKhfNqb/WEl5xA2CCiU1aTjnbZwmpDFl5ELAgeoujfy9k6v5/w3APvpZyCvYta+BDfN1MHf8kATjUHnkqMogR7PPjaVqoUuB9XChwuCOjOOaCHpimqouWukdnHausyYWKYsgiHicLso8F2g5sFZVM0gtF3GN737pgtAoGBAIzun/5OALRfpfxdIGYD08GECkwfr3GZdTxC5XTxy6vPO81pqoVubNK+qFWZAQVLZ3Ipzvj7uSET7iU7GmlE8OGM2rhUnuzMYUa68/pwS77P69dV0hYJckws506lyCtVFiEHaCtufoopzKPiwoioH2xv6OB+BHcWPSKtW9xte+Gb";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvhvO3H8BwtqDZpZkkkYQSfDTx0bQF5cGnH9RDo5245MkrXc9AjTJUKkThFwTmuzD89KC4TNbn6FEHXheya3ovY3gWxQV6sUR6WkcgiKeUU9XY160ZYlZT7aEaMtjWiFSTLb8gQOCOmMGSkiggduRwqxTCiEu4whAsZNuy5dCjH4qaqWF/t/TegkFwujT4BXKIFwN971i0C79Yma9uWZ5Kco8y4jRfG40VCOXf0S8uI7+7Bppl8ZVXWHgeGiBoTtlurbesenMW+//xMGx23rGxIyhKJMTTW5M5IZTqoLecdrGA6UE7Gs80YvIjxbeAh5tgj96sNeN8hGwVSk0QuC4ewIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/kw/order/callBack";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

