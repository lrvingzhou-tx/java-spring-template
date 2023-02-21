package {{ params['userJavaPackage'] }}.publisher.http;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.asyncapi.constant.Common;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

    {% for channelName, channel in asyncapi.channels() %}
            {%- if channel.hasPublish() %}
            {%- for message in channel.publish().messages() %}
import {{ params['userJavaPackage'] }}.model.{{message.payload().uid() | camelCase | upperFirst}};
        {%- endfor %}
        {%- endif %}
        {%- endfor %}

@Component
public class DemoPublisher implements CommandLineRunner {

    @Autowired
    DemoPublisherService publisherService;

    @Override
    public void run(String... args) {

        System.out.println("******* Sending message: *******");

        {%- for channelName, channel in asyncapi.channels() %}
            {%- if channel.hasSubscribe() %}
                {%- for message in channel.subscribe().messages() %}
        publisherService.{{channel.subscribe().id() | camelCase}}({% if asyncapi | isProtocol('http') %}example(){% else %}"Hello World from {{channelName}}"{% endif %});
                {% endfor -%}
            {% endif -%}
        {%- endfor %}

        System.out.println("Message sent");
    }


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

    private {{varName | upperFirst}} example() {
        {% if channel.subscribe().message().examples()%}
        String examples = "{{channel.subscribe().message().examples() | examplesToString | addEscape| safe}}";
        {% else %}
        String examples = "{}"
        {% endif %}

        Object payload = JSONObject.parseObject(examples).get(Common.PAYLOAD);
        {{varName | upperFirst}}  {{varName}};

        if(payload != null) {
            {{varName}} = JSON.parseObject(payload.toString(), new TypeReference<{{varName | upperFirst}}>() {
            });
        } else {
            {{varName}} = new {{varName | upperFirst}}();
        }

        return {{varName}};
    }
        {% endif -%}
    {%- endfor %}
}

