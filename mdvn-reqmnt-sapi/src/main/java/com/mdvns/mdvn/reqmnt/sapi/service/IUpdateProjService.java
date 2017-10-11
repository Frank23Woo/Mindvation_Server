package com.mdvns.mdvn.reqmnt.sapi.service;

import com.mdvns.mdvn.common.beans.Staff;
import com.mdvns.mdvn.common.beans.Tag;
import com.mdvns.mdvn.reqmnt.sapi.domain.*;
import com.mdvns.mdvn.reqmnt.sapi.domain.entity.*;

import java.util.List;

public interface IUpdateProjService {
    RequirementInfo updateProjBaseInfo(RequirementInfo proj);
    List<Staff> updateProjLeaders(UpdateRMembersRequest leders);
    List<ReqmntAttchUrl> updateProjAttchUrls(UpdatePAttchUrlsRequest AttchUrls);
    List<Tag> updateProjTags(UpdatePTagsRequest tags);
    List<ReqmntCheckList> updateProjChecklists(UpdatePCheckListsRequest checkLists);
}
