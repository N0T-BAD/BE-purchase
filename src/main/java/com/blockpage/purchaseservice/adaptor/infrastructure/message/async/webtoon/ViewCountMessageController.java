package com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon;

import com.blockpage.purchaseservice.application.port.out.ViewCountMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewCountMessageController implements ViewCountMessagePort {

    private final ViewCountMessageSender viewCountMessageSender;

    @Override
    public void sendViewCount(ViewCountMessage viewCountMessage) {
        viewCountMessageSender.sendViewCount(viewCountMessage);
    }
}
