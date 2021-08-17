package com.liuwohe.service.Impl;

import com.liuwohe.repository.OrganizationMapper;
import com.liuwohe.entity.Organization;
import com.liuwohe.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@SuppressWarnings("all")
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrganizationMapper orgMapper;

    @Override
    public List<Organization> selectAll() {
        return  orgMapper.selectAll();
    }
    @Override
    public List<Organization> getOrgTree(){
        return  orgMapper.selectAll();
//           return TreeUtils.treeMenu(orgMapper.selectAll());
    }
}
