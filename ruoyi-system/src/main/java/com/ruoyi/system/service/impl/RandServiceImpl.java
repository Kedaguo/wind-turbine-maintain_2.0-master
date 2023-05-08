package com.ruoyi.system.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RandMapper;
import com.ruoyi.system.domain.Rand;
import com.ruoyi.system.service.IRandService;

import javax.annotation.Resource;

/**
 * randService业务层处理
 * 
 * @author JianDa
 * @date 2023-05-06
 */
@Service
public class RandServiceImpl implements IRandService 
{
    @Resource
    private RandMapper randMapper;

    /**
     * 查询rand
     * 
     * @param randId rand主键
     * @return rand
     */
    @Override
    public Rand selectRandByRandId(Long randId)
    {
        return randMapper.selectRandByRandId(randId);
    }

    /**
     * 查询rand列表
     * 
     * @param rand rand
     * @return rand
     */
    @Override
    public List<Rand> selectRandList(Rand rand)
    {
        return randMapper.selectRandList(rand);
    }

    /**
     * 新增rand
     * 
     *
     * @return 结果
     */
    @Override
    public int insertRand()
    {
        DecimalFormat df = new DecimalFormat("#.################");
        for (int i=0;i<2000;i++){
            Random random = new Random();
            double randDouble = random.nextDouble();
            Rand rand1 = new Rand();
            rand1.setRand(df.format(randDouble));
            randMapper.insertRand(rand1);
        }
        return 1;
    }

    /**
     * 修改rand
     * 
     * @param rand rand
     * @return 结果
     */
    @Override
    public int updateRand(Rand rand)
    {
        return randMapper.updateRand(rand);
    }

    /**
     * 批量删除rand
     * 
     * @param randIds 需要删除的rand主键
     * @return 结果
     */
    @Override
    public int deleteRandByRandIds(Long[] randIds)
    {
        return randMapper.deleteRandByRandIds(randIds);
    }

    /**
     * 删除rand信息
     * 
     * @param randId rand主键
     * @return 结果
     */
    @Override
    public int deleteRandByRandId(Long randId)
    {
        return randMapper.deleteRandByRandId(randId);
    }
}
