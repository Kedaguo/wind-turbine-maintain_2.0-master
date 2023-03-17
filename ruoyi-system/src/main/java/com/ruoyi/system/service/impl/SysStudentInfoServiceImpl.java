package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysStudentInfoMapper;
import com.ruoyi.system.domain.SysStudentInfo;
import com.ruoyi.system.service.ISysStudentInfoService;

/**
 * 学生信息管理Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-17
 */
@Service
public class SysStudentInfoServiceImpl implements ISysStudentInfoService 
{
    @Autowired
    private SysStudentInfoMapper sysStudentInfoMapper;

    /**
     * 查询学生信息管理
     * 
     * @param studentInfoId 学生信息管理主键
     * @return 学生信息管理
     */
    @Override
    public SysStudentInfo selectSysStudentInfoByStudentInfoId(Long studentInfoId)
    {
        return sysStudentInfoMapper.selectSysStudentInfoByStudentInfoId(studentInfoId);
    }

    /**
     * 查询学生信息管理列表
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 学生信息管理
     */
    @Override
    public List<SysStudentInfo> selectSysStudentInfoList(SysStudentInfo sysStudentInfo)
    {
        return sysStudentInfoMapper.selectSysStudentInfoList(sysStudentInfo);
    }

    /**
     * 新增学生信息管理
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 结果
     */
    @Override
    public int insertSysStudentInfo(SysStudentInfo sysStudentInfo)
    {
        return sysStudentInfoMapper.insertSysStudentInfo(sysStudentInfo);
    }

    /**
     * 修改学生信息管理
     * 
     * @param sysStudentInfo 学生信息管理
     * @return 结果
     */
    @Override
    public int updateSysStudentInfo(SysStudentInfo sysStudentInfo)
    {
        return sysStudentInfoMapper.updateSysStudentInfo(sysStudentInfo);
    }

    /**
     * 批量删除学生信息管理
     * 
     * @param studentInfoIds 需要删除的学生信息管理主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentInfoByStudentInfoIds(Long[] studentInfoIds)
    {
        return sysStudentInfoMapper.deleteSysStudentInfoByStudentInfoIds(studentInfoIds);
    }

    /**
     * 删除学生信息管理信息
     * 
     * @param studentInfoId 学生信息管理主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentInfoByStudentInfoId(Long studentInfoId)
    {
        return sysStudentInfoMapper.deleteSysStudentInfoByStudentInfoId(studentInfoId);
    }
}
