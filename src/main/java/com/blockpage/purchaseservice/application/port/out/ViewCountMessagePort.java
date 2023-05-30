package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon.message.ViewCountMessage;

public interface ViewCountMessagePort {

    void sendViewCount(ViewCountMessage viewCountMessage);
}
