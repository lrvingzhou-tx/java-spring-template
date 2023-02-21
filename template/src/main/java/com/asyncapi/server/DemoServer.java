package {{ params['userJavaPackage'] }}.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoServer {

    @PostMapping("/message.do")
    public String doMessage(HttpServletRequest request) {
        System.out.println(request.getHeaderNames());
        System.out.println(request.getQueryString());
        System.out.println(binaryRead(request));

        // TODO 服务端标准验签

        // TODO MOCK成功返回值

        // TODO 服务端标准加签

        return "{\"alipay_open_app_openbizmock_message_send_response\":{\"code\":\"10000\",\"msg\":\"Success\"},\"sign\":\"JCWvzmdhLqnh+XsH66hY067drSD9p0zQDqK2YjM4xSvQn1xG80nCxSnbMTGQd3WPVBl1aWk7/3r2OISQ+aYTsc7CLYuOE+eBdc4SjG9PPLbULV5N1xya0tCfZAEr5L6T+nWEoLhEhH7rWDPSpwFv5YIwoCtfNVchaNXNt0E4oOyaOjZGKlBbTMvPX42cF7/IVgNouJxbajz9EHlxFAf1vpnZGusMqf3OrbESIm48Hb/7G/3jxsiGFviaQsdIU4Ja25Sa+W3dSFfpdrWrbPMTYp3wIRHpFPq92ojfv1sqBPutPI/f+zdltgfZl/w6mf//xtxOU0lNGIA2Zk8bVy4aXw==\"}";
    }

    private String binaryRead(HttpServletRequest request) throws IOException {
        int contentLengthLong = request.getContentLength();
        ServletInputStream inputStream = request.getInputStream();

        byte[] buffer = new byte[contentLengthLong];
        inputStream.readLine(buffer, 0, contentLengthLong);

        return new String(buffer);
    }
}
