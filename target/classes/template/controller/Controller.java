package ${package_controller};
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
@RestController
@RequestMapping("/${table}")
@CrossOrigin
public class ${Table}Controller {

    @Autowired
    private ${Table}Service ${table}Service;

    /***
     * ${Table}分页条件搜索实现
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) ${Table} ${table}, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<${Table}> pageInfo = ${table}Service.findPage(${table}, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * ${Table}分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<${Table}> pageInfo = ${table}Service.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param ${table}
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<${Table}>> findList(@RequestBody(required = false) ${Table} ${table}){
        List<${Table}> list = ${table}Service.findList(${table});
        return new Result<List<${Table}>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        ${table}Service.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改${Table}数据
     * @param ${table}
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody ${Table} ${table},@PathVariable Integer id){
        //设置ID
        ${table}.setId(id);
        //修改数据
        ${table}Service.update(${table});
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    @PostMapping
    public Result add(@RequestBody ${Table} ${table}){
        ${table}Service.add(${table});
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询${Table}数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<${Table}> findById(@PathVariable Integer id){
        //根据ID查询
        ${Table} ${table} = ${table}Service.findById(id);
        return new Result<${Table}>(true,StatusCode.OK,"查询成功",${table});
    }

    /***
     * 查询${Table}全部数据
     * @return
     */
    @GetMapping
    public Result<${Table}> findAll(){
        List<${Table}> list = ${table}Service.findAll();
        return new Result<${Table}>(true, StatusCode.OK,"查询成功",list) ;
    }
}
