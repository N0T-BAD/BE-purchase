package com.blockpage.purchaseservice.adaptor.web.apispec;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileSkinRequest {

    private Long profileSkinId;
}
