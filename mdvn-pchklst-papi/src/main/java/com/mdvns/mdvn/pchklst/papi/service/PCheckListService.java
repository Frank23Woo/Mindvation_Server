package com.mdvns.mdvn.pchklst.papi.service;

import com.mdvns.mdvn.pchklst.papi.domain.*;

public interface PCheckListService {

    CreatePCheckListResponse createPCheckList(CreatePCheckListRequest  createPCheckListRequest);

    RtrvPCheckListResponse rtrvPCheckList(RtrvPCheckListRequest rtrvPCheckListRequest);

}
