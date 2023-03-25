package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Fault;

/**
 * 故障信息Service接口
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
public interface IFaultService 
{
    /**
     * 查询故障信息
     * 
     * @param fId 故障信息主键
     * @return 故障信息
     */
    public Fault selectFaultByFId(Long fId);

    /**
     * 查询故障信息列表
     * 
     * @param fault 故障信息
     * @return 故障信息集合
     */
    public List<Fault> selectFaultList(Fault fault);

    /**
     * 新增故障信息
     * 
     * @param fault 故障信息
     * @return 结果
     */
    public int insertFault(Fault fault);

    /**
     * 修改故障信息
     * 
     * @param fault 故障信息
     * @return 结果
     */
    public int updateFault(Fault fault);

    /**
     * 批量删除故障信息
     * 
     * @param fIds 需要删除的故障信息主键集合
     * @return 结果
     */
    public int deleteFaultByFIds(Long[] fIds);

    /**
     * 删除故障信息信息
     * 
     * @param fId 故障信息主键
     * @return 结果
     */
    public int deleteFaultByFId(Long fId);




}
