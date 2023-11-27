package com.chr.tree.domain.comment.service.impl;

import com.chr.tree.domain.comment.presentation.data.response.LinkResponse;
import com.chr.tree.domain.comment.service.CreateLinkService;
import com.chr.tree.global.annotation.ServiceWithTransactional;
import com.chr.tree.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactional
@RequiredArgsConstructor
public class CreateLinkServiceImpl implements CreateLinkService {

    private final UserUtil userUtil;

    // TODO : Domain 적용
    String domain = "https://{backend_url}/";

    @Override
    public LinkResponse execute() {
        return LinkResponse.builder()
                .link(domain + "comment/" + userUtil.currentUser().getUserId())
                .build();
    }
}
