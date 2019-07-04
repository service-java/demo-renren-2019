package io.renren.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * 生成验证码配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-20 19:22
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");

        // 限制范围
        // properties.put("kaptcha.textproducer.char.string", "abcde2345678gfynmnpwx");

        // 验证码长度 default: '5' 注意是字符串
        properties.put("kaptcha.textproducer.char.length", "4");
        // 红绿 -> 色盲用户不易辨识
        properties.put("kaptcha.textproducer.font.color", "blue");
        // 文字间隔
        properties.put("kaptcha.textproducer.char.space", "2");

        // @fix Centos验证码乱码问题
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,cmr10,宋体,楷体,微软雅黑");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
