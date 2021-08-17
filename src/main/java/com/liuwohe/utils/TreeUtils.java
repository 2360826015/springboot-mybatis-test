package com.liuwohe.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.liuwohe.entity.Organization;

//树形结构工具类
public class TreeUtils {

    public  Map<String,Object> mapArray = new LinkedHashMap<String, Object>();

    public static List<Organization> menuCommon; //TreeNode是我刚创建的实体类,你需要放你的实体类
    public static List<Object> list = new ArrayList<>();

    public static List<Object> treeMenu(List<Organization> menu){
        menuCommon = menu;

        for (Organization organization : menu) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            //我的数据库 `TYPE` int(4) DEFAULT NULL COMMENT '部门类型: 1根部门(企业),2二级单位,3三级部门,依次类推
            //这个根据需求,有可能父部门id是一样的,那就在判断语句中把父部门的id等于多少作为if中的条件
            if (organization.getId()==1) {
                setTreeMap(mapArr,organization);
                list.add(mapArr);
            }
        }
        return list;
    }

    //这里的fid参数是父id
    public static List<?> menuChild(Integer fid){
        List<Object> lists = new ArrayList<Object>();
        for(Organization a:menuCommon){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if (a.getParentId()==fid) {
                setTreeMap(childArray,a);
                lists.add(childArray);
            }

        }
        return lists;
    }

    //写上你的类结构
    private static void setTreeMap(Map<String, Object> mapArr, Organization organization){
        mapArr.put("id", organization.getId());
        mapArr.put("text", organization.getText());
        mapArr.put("parentId", organization.getParentId());
        mapArr.put("nodes", menuChild(organization.getId()));
    }

}
