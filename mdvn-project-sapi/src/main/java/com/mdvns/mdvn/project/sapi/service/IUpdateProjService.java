package com.mdvns.mdvn.project.sapi.service;

import com.mdvns.mdvn.project.sapi.domain.UpdatePAttchUrlsRequest;
import com.mdvns.mdvn.project.sapi.domain.UpdatePLeadersRequest;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjAttchUrls;
import com.mdvns.mdvn.project.sapi.domain.entity.ProjLeaders;
import com.mdvns.mdvn.project.sapi.domain.entity.Project;

import java.util.List;

public interface IUpdateProjService {
    Project updateProjBaseInfo(Project proj);
    List<ProjLeaders> updateProjLeaders(UpdatePLeadersRequest leders);
    List<ProjAttchUrls> updateProjAttchUrls(UpdatePAttchUrlsRequest AttchUrls);
}
