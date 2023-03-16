package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TurbineMaintainMapper;
import com.ruoyi.system.domain.TurbineMaintain;
import com.ruoyi.system.service.ITurbineMaintainService;

/**
 * turbineMaintainService业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
@Service
public class TurbineMaintainServiceImpl implements ITurbineMaintainService 
{
    @Autowired
    private TurbineMaintainMapper turbineMaintainMapper;

    /**
     * 查询turbineMaintain
     * 
     * @param tId turbineMaintain主键
     * @return turbineMaintain
     */
    @Override
    public TurbineMaintain selectTurbineMaintainByTId(Long tId)
    {
        return turbineMaintainMapper.selectTurbineMaintainByTId(tId);
    }

    /**
     * 查询turbineMaintain列表
     * 
     * @param turbineMaintain turbineMaintain
     * @return turbineMaintain
     */
    @Override
    public List<TurbineMaintain> selectTurbineMaintainList(TurbineMaintain turbineMaintain)
    {
        return turbineMaintainMapper.selectTurbineMaintainList(turbineMaintain);
    }

    /**
     * 新增turbineMaintain
     * 
     * @param turbineMaintain turbineMaintain
     * @return 结果
     */
    @Override
    public int insertTurbineMaintain(TurbineMaintain turbineMaintain)
    {
        return turbineMaintainMapper.insertTurbineMaintain(turbineMaintain);
    }

    /**
     * 修改turbineMaintain
     * 
     * @param turbineMaintain turbineMaintain
     * @return 结果
     */
    @Override
    public int updateTurbineMaintain(TurbineMaintain turbineMaintain)
    {
        return turbineMaintainMapper.updateTurbineMaintain(turbineMaintain);
    }

    /**
     * 批量删除turbineMaintain
     * 
     * @param tIds 需要删除的turbineMaintain主键
     * @return 结果
     */
    @Override
    public int deleteTurbineMaintainByTIds(Long[] tIds)
    {
        return turbineMaintainMapper.deleteTurbineMaintainByTIds(tIds);
    }

    /**
     * 删除turbineMaintain信息
     * 
     * @param tId turbineMaintain主键
     * @return 结果
     */
    @Override
    public int deleteTurbineMaintainByTId(Long tId)
    {
        return turbineMaintainMapper.deleteTurbineMaintainByTId(tId);
    }
}
