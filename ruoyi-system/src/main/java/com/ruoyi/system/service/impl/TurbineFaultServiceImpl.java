package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TurbineFaultMapper;
import com.ruoyi.system.domain.TurbineFault;
import com.ruoyi.system.service.ITurbineFaultService;

/**
 * turbineFaultService业务层处理
 * 
 * @author 赵建达
 * @date 2023-03-14
 */
@Service
public class TurbineFaultServiceImpl implements ITurbineFaultService 
{
    @Autowired
    private TurbineFaultMapper turbineFaultMapper;

    /**
     * 查询turbineFault
     * 
     * @param tId turbineFault主键
     * @return turbineFault
     */
    @Override
    public TurbineFault selectTurbineFaultByTId(Long tId)
    {
        return turbineFaultMapper.selectTurbineFaultByTId(tId);
    }

    /**
     * 查询turbineFault列表
     * 
     * @param turbineFault turbineFault
     * @return turbineFault
     */
    @Override
    public List<TurbineFault> selectTurbineFaultList(TurbineFault turbineFault)
    {
        return turbineFaultMapper.selectTurbineFaultList(turbineFault);
    }

    /**
     * 新增turbineFault
     * 
     * @param turbineFault turbineFault
     * @return 结果
     */
    @Override
    public int insertTurbineFault(TurbineFault turbineFault)
    {
        return turbineFaultMapper.insertTurbineFault(turbineFault);
    }

    /**
     * 修改turbineFault
     * 
     * @param turbineFault turbineFault
     * @return 结果
     */
    @Override
    public int updateTurbineFault(TurbineFault turbineFault)
    {
        return turbineFaultMapper.updateTurbineFault(turbineFault);
    }

    /**
     * 批量删除turbineFault
     * 
     * @param tIds 需要删除的turbineFault主键
     * @return 结果
     */
    @Override
    public int deleteTurbineFaultByTIds(Long[] tIds)
    {
        return turbineFaultMapper.deleteTurbineFaultByTIds(tIds);
    }

    /**
     * 删除turbineFault信息
     * 
     * @param tId turbineFault主键
     * @return 结果
     */
    @Override
    public int deleteTurbineFaultByTId(Long tId)
    {
        return turbineFaultMapper.deleteTurbineFaultByTId(tId);
    }
}
