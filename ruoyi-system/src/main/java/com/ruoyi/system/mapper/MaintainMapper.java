package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Maintain;

/**
 * maintainMapper接口
 * 
 * @author 赵建达
 * @date 2023-03-13
 */
public interface MaintainMapper 
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
     * 删除maintain
     * 
     * @param mId maintain主键
     * @return 结果
     */
    public int deleteMaintainByMId(Long mId);

    /**
     * 批量删除maintain
     * 
     * @param mIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintainByMIds(Long[] mIds);
}
