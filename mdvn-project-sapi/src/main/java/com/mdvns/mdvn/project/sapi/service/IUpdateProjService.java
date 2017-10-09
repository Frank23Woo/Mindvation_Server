package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.*;
import com.mdvns.mdvn.project.sapi.domain.entity.*;

import java.util.List;

public interface IUpdateProjService {
    Project updateProjBaseInfo(Project proj);
    List<Staff> updateProjLeaders(UpdatePLeadersRequest leders);
    List<ProjAttchUrls> updateProjAttchUrls(UpdatePAttchUrlsRequest AttchUrls);
    List<Tag> updateProjTags(UpdatePTagsRequest tags);
    List<Model> updateProjModels(UpdatePModelsRequest models);
    List<ProjChecklists> updateProjChecklists(UpdatePCheckListsRequest checkLists);
}
