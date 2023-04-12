package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Maintain;

/**
 * maintainService接口
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
public interface IMaintainService 
{
    /**
     * 查询maintain
     * 
     * @param mId maintain主键
     * @return maintain
     */
    public Maintain selectMaintainByMId(Long mId);

    /**
     * 查询maintain列表
     * 
     * @param maintain maintain
     * @return maintain集合
     */
    public List<Maintain> selectMaintainList(Maintain maintain);

    /**
     * 新增maintain
     * 
     * @param maintain maintain
     * @return 结果
     */
    public int insertMaintain(Maintain maintain);

    /**
     * 修改maintain
     * 
     * @param maintain maintain
     * @return 结果
     */
    public int updateMaintain(Maintain maintain);

    /**
     * 批量删除maintain
     * 
     * @param mIds 需要删除的maintain主键集合
     * @return 结果
     */
    public int deleteMaintainByMIds(Long[] mIds);

    /**
     * 删除maintain信息
     * 
     * @param mId maintain主键
     * @return 结果
     */
    public int deleteMaintainByMId(Long mId);

}
