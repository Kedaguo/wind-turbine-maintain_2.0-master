package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Arrangement;
import com.ruoyi.system.domain.dto.ArrangementDto;
import com.ruoyi.system.domain.vo.ArrangementVo;

/**
 * arrangementService接口
 * 
 * @author JianDa
 * @date 2023-04-14
 */
public interface IArrangementService 
{
    /**
     * 查询arrangement
     * 
     * @param aId arrangement主键
     * @return arrangement
     */

    public Arrangement selectArrangementByAId(Long aId);

    /**
     * 查询arrangement列表
     * 
     * @param arrangement arrangement
     * @return arrangement集合
     */
    public List<Arrangement> selectArrangementList(Arrangement arrangement);

    public List<ArrangementDto> selectArrangementDtoList(Arrangement arrangement);

    /**
     * 新增arrangement
     *
     * @return 结果
     */
    public int insertArrangement(ArrangementVo arrangementVo) throws Exception;

    /**
     * 修改arrangement
     * 
     * @param
     * @return 结果
     */
    public int updateArrangement(ArrangementVo arrangementVo);

    /**
     * 批量删除arrangement
     * 
     * @param aIds 需要删除的arrangement主键集合
     * @return 结果
     */
    public int deleteArrangementByAIds(Long[] aIds);

    /**
     * 删除arrangement信息
     * 
     * @param aId arrangement主键
     * @return 结果
     */
    public int deleteArrangementByAId(Long aId);
}
