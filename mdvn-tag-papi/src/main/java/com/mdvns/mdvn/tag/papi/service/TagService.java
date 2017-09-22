package com.mdvns.mdvn.tag.papi.service;

import com.mdvns.mdvn.tag.papi.domain.CreateTagRequest;
import com.mdvns.mdvn.tag.papi.domain.CreateTagResponse;

public interface TagService {


    CreateTagResponse createTag(CreateTagRequest request);
}
