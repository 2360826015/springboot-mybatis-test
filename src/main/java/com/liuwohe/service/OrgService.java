package com.liuwohe.service;

import com.liuwohe.entity.Organization;

import java.util.List;

public interface OrgService {
    List<Organization> selectAll();
    List<Organization> getOrgTree();
}
