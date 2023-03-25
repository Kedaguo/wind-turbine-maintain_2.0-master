package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.Person;

/**
 * personMapper接口
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
public interface PersonMapper extends BaseMapper<Person>
{
    /**
     * 查询person
     * 
     * @param id person主键
     * @return person
     */
    public Person selectPersonById(Long id);

    /**
     * 查询person列表
     * 
     * @param person person
     * @return person集合
     */
    public List<Person> selectPersonList(Person person);

    /**
     * 新增person
     * 
     * @param person person
     * @return 结果
     */
    public int insertPerson(Person person);

    /**
     * 修改person
     * 
     * @param person person
     * @return 结果
     */
    public int updatePerson(Person person);

    /**
     * 删除person
     * 
     * @param id person主键
     * @return 结果
     */
    public int deletePersonById(Long id);

    /**
     * 批量删除person
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonByIds(Long[] ids);
}
