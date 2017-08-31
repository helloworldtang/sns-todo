package com.tangcheng.zhiban.sns.todo.web.viewcontroller;

import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailListReqVO;
import com.tangcheng.zhiban.sns.todo.domain.req.TodoDetailReqVO;
import com.tangcheng.zhiban.sns.todo.domain.res.TodoDetailResVO;
import com.tangcheng.zhiban.sns.todo.service.biz.TodoDetailService;
import com.tangcheng.zhiban.sns.todo.web.constant.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(ApiVersion.WEB_V1 + "/user/todo")
public class TodoListController {
    @Autowired
    private TodoDetailService todoDetailService;

    @GetMapping
    public String listTodoList(TodoDetailListReqVO todoDetailListReqVO, Model model) {
        List<TodoDetailResVO> detailResVOList = todoDetailService.listWeb(todoDetailListReqVO);
        model.addAttribute("list", detailResVOList);
        return "todolist/todolistPage";
    }

    @GetMapping("add")
    public String add() {
        return "todolist/addNewTodo";
    }


    @PostMapping("{id}")
    public void finish(@PathVariable Long id) {
        todoDetailService.finish(id);
    }

    @PostMapping
    public String save(@Valid TodoDetailReqVO todoDetailReqVO) {
        todoDetailService.save(todoDetailReqVO);
        return "redirect:"+ApiVersion.WEB_V1 + "/user/todo?finished=false";
    }


}
