package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;

import java.util.List;

public interface IUpdateProjService {
    Project updateProjBaseInfo(Project proj);
    List<ProjLeaders> updateProjLeaders(UpdatePLeadersRequest leders);
    List<ProjAttchUrls> updateProjAttchUrls(UpdatePAttchUrlsRequest AttchUrls);
    List<ProjTags> updateProjTags(UpdatePTagsRequest tags);
    List<ProjModels> updateProjModels(UpdatePModelsRequest models);
    List<ProjChecklists> updateProjChecklists(UpdatePCheckListsRequest checkLists);
}
