package com.mdvns.mdvn.pchklst.sapi.service;

import com.mdvns.mdvn.pchklst.sapi.domian.RtrvPCheckListRequest;
import com.mdvns.mdvn.pchklst.sapi.domian.entity.PCheckList;

import java.util.List;

public interface PCheckListService {

    PCheckList createCheckList(PCheckList pCheckList);

    PCheckList updateCheckList(PCheckList pCheckList);

    Integer deleteCheckList(PCheckList pCheckList);

    List<PCheckList> rtrvPCheckList(RtrvPCheckListRequest rtrvPCheckListRequest);


}
