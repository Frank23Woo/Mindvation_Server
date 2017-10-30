package com.mdvns.mdvn.department.sapi.service.impl;

import com.mdvns.mdvn.department.sapi.domain.*;
import com.mdvns.mdvn.department.sapi.domain.entity.Department;
import com.mdvns.mdvn.department.sapi.domain.entity.Position;
import com.mdvns.mdvn.department.sapi.repository.DepartmentRepository;
import com.mdvns.mdvn.department.sapi.repository.PositionRepository;
import com.mdvns.mdvn.department.sapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public DepartmentDetail createDepartment(CreateOrUpdateDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request)) {
            return null;
        }

        Department department = new Department();
        department.setName(request.getName());
        department.setIsDeleted(0);
        department.setCreateTime(new Timestamp(System.currentTimeMillis()));
        department = departmentRepository.save(department);
        department.setId("D" + department.getUuId());
        department = departmentRepository.save(department);

        List<Position> positionList = request.getPositions();
        if (positionList != null && !positionList.isEmpty()) {
            for (Position position : positionList) {
                position.setDepartmentId(department.getId());
                position.setId(null);
                position.setIsDeleted(0);
            }

            positionList = positionRepository.save(positionList);
        }

        return createDepartmentDetail(department, positionList);
    }

    @Override
    public Boolean deleteDepartment(QueryDepartmentRequest request) {
        if (request == null || request.getDepartmentId() == null) {
            return null;
        }

        Department department = departmentRepository.findFirstByIdAndIsDeleted(request.getDepartmentId(), 0);
        if (department != null) {
            department.setIsDeleted(1);
            departmentRepository.save(department);
        }

        return true;
    }

    @Override
    public DepartmentDetail getDepartment(QueryDepartmentRequest request) {
        if (request == null || request.getDepartmentId() == null) {
            return null;
        }

        Department department = departmentRepository.findFirstByIdAndIsDeleted(request.getDepartmentId(), 0);
        if (department == null) {
            return null;
        }
        List<Position> positionList = positionRepository.findAllByDepartmentIdAndIsDeleted(department.getId(), 0);

        return createDepartmentDetail(department, positionList);
    }


    @Override
    public RtrvDepartmentListResponse getAllDepartment(RtrvDepartmentListRequest request) {
        int page = request.getPage() == null ? 0 : request.getPage();
        int pageSize = request.getPageSize() == null ? 10 : request.getPageSize();

        List<DepartmentDetail> result = new ArrayList<>();
        Pageable pageable = new PageRequest(page, pageSize, new Sort(Sort.Direction.ASC, "createTime"));
        Page<Department> departmentPage = departmentRepository.findAllByIsDeleted(0, pageable);
        List<Department> departmentList = departmentPage.getContent();
        for (Department department : departmentList) {
            List<Position> positionList = positionRepository.findAllByDepartmentIdAndIsDeleted(department.getId(), 0);
            result.add(createDepartmentDetail(department, positionList));
        }

        RtrvDepartmentListResponse response = new RtrvDepartmentListResponse();
        response.setTotalPage(departmentPage.getTotalPages());
        response.setDepartmentList(result);

        return response;
    }

    @Override
    public DepartmentDetail updateDepartment(CreateOrUpdateDepartmentRequest request) {
        if (request == null || StringUtils.isEmpty(request.getId())) {
            return null;
        }

        Department department = departmentRepository.findFirstByIdAndIsDeleted(request.getId(), 0);
        if (department == null) {
            return null;
        }

        if (!department.getName().equals(request.getName())) {
            department.setName(request.getName());
            departmentRepository.save(department);
        }

        List<Position> positionList = request.getPositions();
        if (positionList == null || positionList.isEmpty()) {
            // delete all position
            positionList = positionRepository.findAllByDepartmentIdAndIsDeleted(request.getId(), 0);
            positionRepository.delete(positionList);
        } else {
            // 逐个对比
            List<Position> oldItems = positionRepository.findAllByDepartmentIdAndIsDeleted(request.getId(), 0);
            for (Position position : oldItems) {
                position.setIsDeleted(1);
            }

            List<Position> positionsForAdd = new ArrayList<>();
            for (Position position : positionList) {
                if (StringUtils.isEmpty(position.getId())) {
                    position.setIsDeleted(0);
                    position.setDepartmentId(request.getId());
                    positionsForAdd.add(position);
                    continue;
                }

                boolean find = false;
                for (int i = 0; i < oldItems.size(); i++) {
                    if (oldItems.get(i).getId() == position.getId()) {
                        if (!oldItems.get(i).getName().equals(position.getName())) {
                            oldItems.get(i).setName(position.getName());
                        }
                        oldItems.get(i).setIsDeleted(0);
                        find = true;
                    }
                }
            }

            positionRepository.save(oldItems);
            if (!positionsForAdd.isEmpty()) {
                positionRepository.save(positionsForAdd);
            }
        }

        return createDepartmentDetail(department, positionRepository.findAllByDepartmentIdAndIsDeleted(department.getId(), 0));
    }

    @Override
    public List<Department> findDepartmentListByIds(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        String[] idArray = ids.split(",");

        List<Department> departmentList = departmentRepository.findAllByIdIn(Arrays.asList(idArray));
        if (departmentList.size() == idArray.length) {
            return departmentList;
        } else {
            // 传入参数包含重复id
            ArrayList<Department> result = new ArrayList<>();

            boolean find = false;
            for (String id : idArray) {
                find = false;
                for (Department department : departmentList) {
                    if (department.getId().equals(id)) {
                        result.add(department);
                        find = true;
                        break;
                    }
                }
            }
            return result;
        }
    }

    @Override
    public List<Position> findPositionListByIds(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        String[] idArray = ids.split(",");
        List<Position> positionList = positionRepository.findAllByIdIn(convertStringListToIntegerList(Arrays.asList(idArray)));
        if (positionList.size() == idArray.length) {
            return positionList;
        } else {
            // 传入参数包含重复id
            ArrayList<Position> result = new ArrayList<>();

            boolean find = false;
            for (String id : idArray) {
                find = false;
                for (Position position : positionList) {
                    if (position.getId().toString().equals(id)) {
                        result.add(position);
                        find = true;
                        break;
                    }
                }
            }
            return result;
        }
    }

    @Override
    public Position findPositionById(Integer id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        Position position = positionRepository.getOne(id);
        return position;
    }

    @Override
    public DepartmentDetail findDepartmentById(String departmentId) {
        Department department = departmentRepository.findFirstByIdAndIsDeleted(departmentId, 0);
        if (department == null) {
            return null;
        }

        return createDepartmentDetail(department, null);
    }

    // 公共方法
    private DepartmentDetail createDepartmentDetail(Department department, List<Position> positionList) {
        DepartmentDetail departmentDetail = new DepartmentDetail();
        departmentDetail.setUuId(department.getUuId());
        departmentDetail.setId(department.getId());
        departmentDetail.setName(department.getName());
        departmentDetail.setIsDeleted(department.getIsDeleted());
        departmentDetail.setCreateTime(department.getCreateTime());
        departmentDetail.setPositions(positionList);
        return departmentDetail;
    }

    private List<Integer> convertStringListToIntegerList(List<String> list) {
        List<Integer> intList = new ArrayList<>();
        for (String str : list) {
            try {
                intList.add(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return intList;
    }
}
