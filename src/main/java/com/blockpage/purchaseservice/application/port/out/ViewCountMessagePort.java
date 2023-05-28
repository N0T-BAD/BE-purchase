package com.blockpage.purchaseservice.application.port.out;

import com.blockpage.purchaseservice.adaptor.infrastructure.message.async.webtoon.ViewCountMessage;

public interface ViewCountMessagePort {

    void sendViewCount(ViewCountMessage viewCountMessage);
}
