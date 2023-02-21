package {{ params['userJavaPackage'] }}.infrastructure;


import com.asyncapi.constant.Common;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AlipayClientConfig {

    @Value("${demo.server.url}")
    private String serverUrl;

    @Value("${demo.app-info.app-id}")
    private String appId;

    @Value("${demo.app-info.private-key}")
    private String privateKey;

    @Value("${demo.app-info.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${demo.format}")
    private String format;

    @Value("${demo.charset}")
    private String charset;

    @Value("${demo.sign_type}")
    private String signType;


    @Bean
    public Map<String, String> configs() {
        Map<String, String> props = new HashMap<>();
        props.put(Common.SERVER_URL, serverUrl);
        props.put(Common.APP_ID, appId);
        props.put(Common.APP_PRIVATE_KEY, privateKey);
        props.put(Common.ALIPAY_PUBLIC_KEY, alipayPublicKey);
        props.put(Common.FORMAT, format);
        props.put(Common.CHARSET, charset);
        props.put(Common.SIGN_TYPE, signType);

        return props;
    }
}
