package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Person;
import com.ruoyi.system.service.IPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * personController
 * 
 * @author 赵建达
 * @date 2023-03-24
 */
@RestController
@RequestMapping("/system/person")
public class PersonController extends BaseController
{
    @Autowired
    private IPersonService personService;

    /**
     * 查询person列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:list')")
    @GetMapping("/list")
    public TableDataInfo list(Person person)
    {
        startPage();
        List<Person> list = personService.selectPersonList(person);
        return getDataTable(list);
    }

    /**
     * 导出person列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:export')")
    @Log(title = "person", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Person person)
    {
        List<Person> list = personService.selectPersonList(person);
        ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
        util.exportExcel(response, list, "person数据");
    }

    /**
     * 获取person详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:person:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personService.selectPersonById(id));
    }

    /**
     * 新增person
     */
    @PreAuthorize("@ss.hasPermi('system:person:add')")
    @Log(title = "person", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Person person)
    {
        return toAjax(personService.insertPerson(person));
    }

    /**
     * 修改person
     */
    @PreAuthorize("@ss.hasPermi('system:person:edit')")
    @Log(title = "person", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Person person)
    {
        return toAjax(personService.updatePerson(person));
    }

    /**
     * 删除person
     */
    @PreAuthorize("@ss.hasPermi('system:person:remove')")
    @Log(title = "person", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personService.deletePersonByIds(ids));
    }
}
