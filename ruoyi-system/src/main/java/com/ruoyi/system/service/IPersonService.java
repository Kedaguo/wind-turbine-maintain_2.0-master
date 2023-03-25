package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Person;

/**
 * personService接口
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
public interface IPersonService 
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
     * 批量删除person
     * 
     * @param ids 需要删除的person主键集合
     * @return 结果
     */
    public int deletePersonByIds(Long[] ids);

    /**
     * 删除person信息
     * 
     * @param id person主键
     * @return 结果
     */
    public int deletePersonById(Long id);
}
