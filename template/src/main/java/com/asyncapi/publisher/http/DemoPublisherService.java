package {{ params['userJavaPackage'] }}.publisher.http;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAppOpenbizmockMessageSendRequest;
import com.alipay.api.response.AlipayOpenAppOpenbizmockMessageSendResponse;
import com.asyncapi.constant.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
{% for channelName, channel in asyncapi.channels() %}
        {%- if channel.hasSubscribe() %}
        {%- for message in channel.subscribe().messages() %}
import {{params['userJavaPackage']}}.model.{{message.payload().uid() | camelCase | upperFirst}};
        {%- endfor -%}
        {% endif -%}
        {% endfor %}


import java.util.Map;

@Service
public class DemoPublisherService {

    @Autowired
    private Map<String, String> configs;

    {% for channelName, channel in asyncapi.channels() %}
        {%- if channel.hasSubscribe() %}
            {%- if channel.subscribe().hasMultipleMessages() %}
                {%- set varName = "object" %}
            {%- else %}
                {%- set varName = channel.subscribe().message().payload().uid() | camelCase %}
            {%- endif %}
        {% if channel.description() or channel.subscribe().description() %}/**{% for line in channel.description() | splitByLines %}
         * {{line | safe}}{% endfor %}{% for line in channel.subscribe().description() | splitByLines %}
         * {{line | safe}}{% endfor %}
         */{% endif %}
    public void {{channel.subscribe().id() | camelCase}}({{varName | upperFirst}} {{varName}}) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(
                    configs.get(Common.SERVER_URL),
                    configs.get(Common.APP_ID),
                    configs.get(Common.APP_PRIVATE_KEY),
                    configs.get(Common.FORMAT),
                    configs.get(Common.CHARSET),
                    configs.get(Common.ALIPAY_PUBLIC_KEY),
                    configs.get(Common.SIGN_TYPE)
            );

            AlipayOpenAppOpenbizmockMessageSendRequest request = new AlipayOpenAppOpenbizmockMessageSendRequest();
            request.setBizContent(JSON.toJSONString({{varName}}));

            System.out.println(JSON.toJSON(request.getBizContent()));

            AlipayOpenAppOpenbizmockMessageSendResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("SEND MESSAGE SUCCESS...");
            } else {
                System.out.println("FAILED TO SEND MESSAGE...");
            }
            System.out.println(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        {%- endif %}
    {%- endfor %}
}
