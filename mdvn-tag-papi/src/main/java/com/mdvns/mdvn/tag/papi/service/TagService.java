package com.mdvns.mdvn.tag.papi.service;

import com.mdvns.mdvn.tag.papi.domain.*;

public interface TagService {


    CreateTagResponse createTag(CreateTagRequest request);

    RetrieveTagListResponse rtrvTagList(RetrieveTagListRequest request);
}
