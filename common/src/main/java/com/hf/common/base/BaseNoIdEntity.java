package com.hf.common.base;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 基本实体
 * Created by 韩峰 on 2016/8/2.
 */
@MappedSuperclass
public class BaseNoIdEntity extends BaseEntity {
    private static final long serialVersionUID = 4123620900938069418L;
    /**
     * 创建人id
     */
    @Column(name = "create_id")
    private String createId;
    /**
     * 创建人name
     */
    @Column(name = "create_name")
    private String createName;
    /**
     * 创建人date
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;
    /**
     * 更新人id
     */
    @Column(name = "update_id")
    private String updateId;
    /**
     * 更新人name
     */
    @Column(name = "update_name")
    private String updateName;
    /**
     * 更新date
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 版本控制 (乐观锁)
     */
    @Version
    private Integer version;

    /**
     * 状态
     *  0 = 删除, 1= 正常
     *  默认 : 1;
     */
    private Integer status;

    // /**
    //  * 执行插入数据之后回调
    //  *  更新创建人人id,name和时间
    //  */
    // /*@PostPersist*/
    // public void setCreateUser(){
    //     UserEntity user = CommonUtils.getUserSession();
    //     this.createId = user.getId();
    //     this.createName = user.getName();
    //     this.createDate = new Date();
    // }
    //
    // /**
    //  * 执行更新之后回调
    //  *  更新最后修改人id,name和时间
    //  */
    // /*@PostUpdate*/
    // public void seUpdateUser(){
    //     UserEntity user = CommonUtils.getUserSession();
    //     this.updateId = user.getId();
    //     this.updateName = user.getName();
    //     this.updateDate = new Date();
    // }

    @Override
    public String toString() {
        Class aClass = this.getClass();
//        aClass.newInstance();
        Field [] decFields = aClass.getDeclaredFields();
        Field [] fields = aClass.getFields();
        StringBuffer sb = new StringBuffer(aClass.getSimpleName());
        sb.append("{");
        try {
            for (Field  decField : decFields) {
                decField.setAccessible(true);
                sb.append(decField.getName() );
                sb.append( "=" );
                sb.append(decField.get(this));
                sb.append( ", " );
            }
            for (Field  field : fields) {
                field.setAccessible(true);
                sb.append(field.getName() );
                sb.append( "=" );
                sb.append(field.get(this));
                sb.append( ", " );
            }
            sb.append("}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void createDate(){
        this.createDate = new Date();
    }

    public void updateDate(){
        this.updateDate = new Date();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
