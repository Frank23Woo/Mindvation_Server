package com.mdvns.mdvn.staff.papi.service;

import com.mdvns.mdvn.common.beans.RestResponse;
import com.mdvns.mdvn.staff.papi.domain.RetrieveStaffListRequest;


public interface StaffService {

    RestResponse rtrvStaffList(RetrieveStaffListRequest request);

}
