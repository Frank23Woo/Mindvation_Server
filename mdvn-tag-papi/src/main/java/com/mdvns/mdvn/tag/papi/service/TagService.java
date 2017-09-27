package com.mdvns.mdvn.tag.papi.service;

import com.mdvns.mdvn.common.exception.RestDefautResponse;
import com.mdvns.mdvn.tag.papi.domain.*;

public interface TagService {


    RestDefautResponse createTag(CreateTagRequest request);

    RetrieveTagListResponse rtrvTagList(RetrieveTagListRequest request);

    Tag updateQuoteCnt(UpdateQuoteCntRequest updateQuoteCntRequest);
}
