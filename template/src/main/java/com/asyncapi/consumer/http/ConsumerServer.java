package {{ params['userJavaPackage'] }}.consumer.http;

import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerServer {

    @PostMapping("/demo/consumer")
    public String doMessage() {

        // TODO 服务端标准验签

        // TODO MOCK成功返回值

        // TODO 服务端标准加签

        return "{\"alipay_open_app_openbizmock_message_send_response\":{\"code\":\"10000\",\"msg\":\"Success\"},\"sign\":\"JCWvzmdhLqnh+XsH66hY067drSD9p0zQDqK2YjM4xSvQn1xG80nCxSnbMTGQd3WPVBl1aWk7/3r2OISQ+aYTsc7CLYuOE+eBdc4SjG9PPLbULV5N1xya0tCfZAEr5L6T+nWEoLhEhH7rWDPSpwFv5YIwoCtfNVchaNXNt0E4oOyaOjZGKlBbTMvPX42cF7/IVgNouJxbajz9EHlxFAf1vpnZGusMqf3OrbESIm48Hb/7G/3jxsiGFviaQsdIU4Ja25Sa+W3dSFfpdrWrbPMTYp3wIRHpFPq92ojfv1sqBPutPI/f+zdltgfZl/w6mf//xtxOU0lNGIA2Zk8bVy4aXw==\"}";
    }
}
