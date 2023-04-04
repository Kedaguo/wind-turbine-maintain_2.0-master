package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息管理对象 sys_student_info
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public class SysStudentInfo
{
    private static final long serialVersionUID = 1L;

    /** 学生信息编号 */
    private Long studentInfoId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 小组编号 */
    @Excel(name = "小组编号")
    private Long groupId;

    /** 学生成绩 */
    @Excel(name = "学生成绩")
    private Long studentPoints;

    /** 亏损/盈利 */
    @Excel(name = "亏损/盈利")
    private Long cost;

    /** 学号/工号唯一 */
    @Excel(name = "学号/工号唯一")
    private String userName;

    /** 花费时间 */
    @Excel(name = "花费时间")
    private Long time;

    public void setStudentInfoId(Long studentInfoId) 
    {
        this.studentInfoId = studentInfoId;
    }

    public Long getStudentInfoId() 
    {
        return studentInfoId;
    }
    public void setMajorName(String majorName) 
    {
        this.majorName = majorName;
    }

    public String getMajorName() 
    {
        return majorName;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setStudentPoints(Long studentPoints) 
    {
        this.studentPoints = studentPoints;
    }

    public Long getStudentPoints() 
    {
        return studentPoints;
    }
    public void setCost(Long cost) 
    {
        this.cost = cost;
    }

    public Long getCost() 
    {
        return cost;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentInfoId", getStudentInfoId())
            .append("majorName", getMajorName())
            .append("groupId", getGroupId())
            .append("studentPoints", getStudentPoints())
            .append("cost", getCost())
            .append("userName", getUserName())
            .append("time", getTime())
            .toString();
    }
}
