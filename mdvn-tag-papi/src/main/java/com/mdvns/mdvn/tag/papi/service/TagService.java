package com.mdvns.mdvn.tag.papi.service;


import com.mdvns.mdvn.common.beans.RestDefaultResponse;
import com.mdvns.mdvn.tag.papi.domain.*;

public interface TagService {


    RestDefaultResponse createTag(CreateTagRequest request);

    RetrieveTagListResponse rtrvTagList(RetrieveTagListRequest request);

    Tag updateQuoteCnt(UpdateQuoteCntRequest updateQuoteCntRequest);
}
