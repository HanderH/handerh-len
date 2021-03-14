package com.handerh.dubnac.api.facade.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author handerh
 * @date 2021/03/14
 */
@Data
@Builder
public class UserInfoResp {

    private String userName;

    private String userAge;

    private String useSex;
}
