package com.czz.test.markdown.controller;

import com.czz.test.markdown.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author czz
 * @version 1.0
 * @date 2020/8/27 21:22
 */
@RestController
@Api(value = "MarkDown测试接口",tags = "测试客户端")
public class MarkDownTest {

    @PostMapping("json/{kgName}")
//    @ApiOperation(value = "返回json",notes = "测试接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name",value = "客户名称"),
//            @ApiImplicitParam(name = "id",value = "客户id")
//    })
    public ResponseEntity<String> markDown(@PathVariable String kgName,
                                           @RequestBody User user){
        String json="{\"success\":{\"message\":\"HelloWorld:"+user.getName()+"\",\"status_code\":200}}";
        return ResponseEntity.ok(json);
    }
}
