package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysStudentInfo;

/**
 * 学生信息管理Service接口
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
public interface ISysStudentInfoService 
{
    /**
     * 查询学生信息管理
     * 
     * @param studentInfoId 学生信息管理主键
     * @return 学生信息管理
     */
    public SysStudentInfo selectSysStudentInfoByStudentInfoId(Long studentInfoId);

    /**
     * 查询学生信息管理列表
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 学生信息管理集合
     */
    public List<SysStudentInfo> selectSysStudentInfoList(SysStudentInfo sysStudentInfo);

    /**
     * 新增学生信息管理
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 结果
     */
    public int insertSysStudentInfo(SysStudentInfo sysStudentInfo);

    /**
     * 修改学生信息管理
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 结果
     */
    public int updateSysStudentInfo(SysStudentInfo sysStudentInfo);

    /**
     * 批量删除学生信息管理
     * 
     * @param studentInfoIds 需要删除的学生信息管理主键集合
     * @return 结果
     */
    public int deleteSysStudentInfoByStudentInfoIds(Long[] studentInfoIds);

    /**
     * 删除学生信息管理信息
     * 
     * @param studentInfoId 学生信息管理主键
     * @return 结果
     */
    public int deleteSysStudentInfoByStudentInfoId(Long studentInfoId);
}
