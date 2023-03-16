package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FaultMapper;
import com.ruoyi.system.domain.Fault;
import com.ruoyi.system.service.IFaultService;

/**
 * 故障信息Service业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
@Service
public class FaultServiceImpl implements IFaultService 
{
    @Autowired
    private FaultMapper faultMapper;

    /**
     * 查询故障信息
     * 
     * @param fId 故障信息主键
     * @return 故障信息
     */
    @Override
    public Fault selectFaultByFId(Long fId)
    {
        return faultMapper.selectFaultByFId(fId);
    }

    /**
     * 查询故障信息列表
     * 
     * @param fault 故障信息
     * @return 故障信息
     */
    @Override
    public List<Fault> selectFaultList(Fault fault)
    {
        return faultMapper.selectFaultList(fault);
    }

    /**
     * 新增故障信息
     * 
     * @param fault 故障信息
     * @return 结果
     */
    @Override
    public int insertFault(Fault fault)
    {
        return faultMapper.insertFault(fault);
    }

    /**
     * 修改故障信息
     * 
     * @param fault 故障信息
     * @return 结果
     */
    @Override
    public int updateFault(Fault fault)
    {
        return faultMapper.updateFault(fault);
    }

    /**
     * 批量删除故障信息
     * 
     * @param fIds 需要删除的故障信息主键
     * @return 结果
     */
    @Override
    public int deleteFaultByFIds(Long[] fIds)
    {
        return faultMapper.deleteFaultByFIds(fIds);
    }

    /**
     * 删除故障信息信息
     * 
     * @param fId 故障信息主键
     * @return 结果
     */
    @Override
    public int deleteFaultByFId(Long fId)
    {
        return faultMapper.deleteFaultByFId(fId);
    }
}
