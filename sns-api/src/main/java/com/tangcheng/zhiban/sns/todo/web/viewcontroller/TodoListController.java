package com.tangcheng.zhiban.sns.todo.web.viewcontroller;

import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoSearchReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.service.biz.TodoDetailService;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Todo View", description = "Todo View")
@Controller
@RequestMapping(ApiVersion.WEB_V1 + "/user/todo")
public class TodoListController {

    @Autowired
    private TodoDetailService todoDetailService;

    @ApiOperation("display todo list")
    @GetMapping
    public String listTodoList(TodoDetailListReqVO todoDetailListReqVO, Model model) {
        List<TodoDetailResVO> detailResVOList = todoDetailService.listWeb(todoDetailListReqVO);
        model.addAttribute("list", detailResVOList);
        return "todolist/todolistPage";
    }

    @ApiOperation("display todo item page for add ")
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("url", ApiVersion.WEB_V1 + "/user/todo");
        model.addAttribute("btnShowValue", "Add");
        return "todolist/addNewTodo";
    }

    @ApiOperation("display todo item page for add ")
    @PostMapping
    public String save(@Valid TodoDetailReqVO todoDetailReqVO) {
        todoDetailService.save(todoDetailReqVO);
        return "redirect:" + ApiVersion.WEB_V1 + "/user/todo?finished=false";
    }


    @ApiOperation("display todo item page for modify ")
    @GetMapping("{id}/modify")
    public String update(@PathVariable Long id, Model model) {
        TodoDetailResVO detailResVO = todoDetailService.get(id);
        if (detailResVO == null) {
            return "redirect:" + ApiVersion.WEB_V1 + "/user/todo?finished=false";
        }

        model.addAttribute("url", ApiVersion.WEB_V1 + "/user/todo/" + id + "/modify");
        model.addAttribute("item", detailResVO);
        model.addAttribute("btnShowValue", "Modify");
        return "todolist/addNewTodo";
    }

    @ApiOperation("modify todo item")
    @PostMapping("{id}/modify")
    public String update(@PathVariable Long id,
                         @Valid TodoDetailReqVO todoDetailReqVO,
                         Model model) {
        todoDetailService.update(id, todoDetailReqVO);

        TodoDetailListReqVO todoDetailListReqVO = new TodoDetailListReqVO();
        todoDetailListReqVO.setFinished(false);
        return listTodoList(todoDetailListReqVO, model);
    }

    @ApiOperation("finish one todo item")
    @PostMapping("{id}")
    public void finish(@PathVariable Long id) {
        todoDetailService.finish(id);
    }


    @ApiOperation("display todo list for search")
    @GetMapping("search")
    public String search(Model model) {
        model.addAttribute("url", ApiVersion.WEB_V1 + "/user/todo/search");
        return "todolist/todoSearch";
    }


    @ApiOperation("search target todo item")
    @PostMapping("search")
    public String search(@Valid TodoSearchReqVO searchReqVO, Model model) {
        List<TodoDetailResVO> detailResVOList = todoDetailService.search(searchReqVO);
        model.addAttribute("key", searchReqVO.getKey());
        model.addAttribute("url", ApiVersion.WEB_V1 + "/user/todo/search");
        model.addAttribute("list", detailResVOList);
        return "todolist/todoSearch";
    }


}
