package com.hf.adminWeb.vo;


import com.hf.common.base.BaseEntity;

import java.util.List;

/**
 * Created by rain on 2017/8/18.
 */
public class Tree<T extends BaseEntity> implements Comparable {

    private String id;
    private String pid;
    private T t;
    private Integer sort;
    private List<Tree> children;

    public Tree() {
    }

    public Tree(String id, String pid, T t, List<Tree> children) {
        this.id = id;
        this.pid = pid;
        this.t = t;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(Object o) {
        Tree tree;
        if (null != o) {
            tree = (Tree)o;
        }else {
            tree = new Tree();
        }
        return this.sort.compareTo(tree.getSort());
    }
}
