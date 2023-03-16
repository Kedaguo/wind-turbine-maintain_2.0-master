package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Port;

/**
 * 港口信息Mapper接口
 * 
 * @author 赵建达
 * @date 2023-03-12
 */
public interface PortMapper 
{
    /**
     * 查询港口信息
     * 
     * @param pId 港口信息主键
     * @return 港口信息
     */
    public Port selectPortByPId(Long pId);

    /**
     * 查询港口信息列表
     * 
     * @param port 港口信息
     * @return 港口信息集合
     */
    public List<Port> selectPortList(Port port);

    /**
     * 新增港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    public int insertPort(Port port);

    /**
     * 修改港口信息
     * 
     * @param port 港口信息
     * @return 结果
     */
    public int updatePort(Port port);

    /**
     * 删除港口信息
     * 
     * @param pId 港口信息主键
     * @return 结果
     */
    public int deletePortByPId(Long pId);

    /**
     * 批量删除港口信息
     * 
     * @param pIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePortByPIds(Long[] pIds);
}
