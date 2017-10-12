package com.hf.adminDao.entity;


import com.hf.common.base.BaseIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 自动生成代码      
 * 系统菜单实体        
 * @author HF 
 * @date 2017-8-8 22:26:53            
 */
@Entity
@Table(name = "ts_menu")
public class SystemMenuEntity extends BaseIdEntity {

        private static final long serialVersionUID = 2930610026164477773L;
        /**
         * 菜单类型
         */
        @Column(name = "MENU_TYPE")
        private Long menuType ;

        /**
        * 父级菜单
        */
        @Column(name = "PRO_MENU_ID")
        private Long proMenuId ;
        /**
        * 菜单层级
        */
        @Column(name = "MENU_LEVELS")
        private String menuLevels ;
        /**
        * 菜单名字
        */
        @Column(name = "MENU_NAME")
        private String menuName ;
        /**
        * 菜单地址
        */
        @Column(name = "MENU_ADDRESS")
        private String menuAddress ;

        /**
         * 序号
         */
        @Column(name = "SORT")
        private String sort ;

        public SystemMenuEntity() {
        }

        public SystemMenuEntity(Long proMenuId) {
                this.proMenuId = proMenuId;
        }

        public SystemMenuEntity(Long menuType, Long proMenuId, String menuLevels, String menuName, String menuAddress, String sort) {
                this.menuType = menuType;
                this.proMenuId = proMenuId;
                this.menuLevels = menuLevels;
                this.menuName = menuName;
                this.menuAddress = menuAddress;
                this.sort = sort;
        }

        public Long getMenuType() {
                return menuType;
        }

        public void setMenuType(Long menuType) {
                this.menuType = menuType;
        }

        /**
        * 获取父级菜单
        */
        public Long getProMenuId () {
            return this.proMenuId;
        }

        /**
        * 设置父级菜单
        */
        public void setProMenuId (Long proMenuId) {
            this.proMenuId = proMenuId;
        }
        /**
        * 获取菜单层级
        */
        public String getMenuLevels () {
            return this.menuLevels;
        }

        /**
        * 设置菜单层级
        */
        public void setMenuLevels (String menuLevels) {
            this.menuLevels = menuLevels;
        }
        /**
        * 获取菜单名字
        */
        public String getMenuName () {
            return this.menuName;
        }

        /**
        * 设置菜单名字
        */
        public void setMenuName (String menuName) {
            this.menuName = menuName;
        }
        /**
        * 获取菜单地址
        */
        public String getMenuAddress () {
            return this.menuAddress;
        }

        /**
        * 设置菜单地址
        */
        public void setMenuAddress (String menuAddress) {
            this.menuAddress = menuAddress;
        }

        /**
         * 获取序号
         */
        public String getSort() {
                return sort;
        }

        /**
         * 设置序号
         */
        public void setSort(String sort) {
                this.sort = sort;
        }
}

