package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TlWindturbine;

/**
 * 故障风机监控Service接口
 * 
 * @author 张存
 * @date 2023-03-12
 */
public interface ITlWindturbineService 
{
    /**
     * 查询故障风机监控
     * 
     * @param id 故障风机监控主键
     * @return 故障风机监控
     */
    public TlWindturbine selectTlWindturbineById(Long id);

    /**
     * 查询故障风机监控列表
     * 
     * @param tlWindturbine 故障风机监控
     * @return 故障风机监控集合
     */
    public List<TlWindturbine> selectTlWindturbineList(TlWindturbine tlWindturbine);

    /**
     * 新增故障风机监控
     * 
     * @param tlWindturbine 故障风机监控
     * @return 结果
     */
    public int insertTlWindturbine(TlWindturbine tlWindturbine);

    /**
     * 修改故障风机监控
     * 
     * @param tlWindturbine 故障风机监控
     * @return 结果
     */
    public int updateTlWindturbine(TlWindturbine tlWindturbine);

    /**
     * 批量删除故障风机监控
     * 
     * @param ids 需要删除的故障风机监控主键集合
     * @return 结果
     */
    public int deleteTlWindturbineByIds(Long[] ids);

    /**
     * 删除故障风机监控信息
     * 
     * @param id 故障风机监控主键
     * @return 结果
     */
    public int deleteTlWindturbineById(Long id);
}
