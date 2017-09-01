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
    public String add(Model model) {
        model.addAttribute("url", ApiVersion.WEB_V1 + "/user/todo");
        model.addAttribute("btnShowValue", "Add");
        return "todolist/addNewTodo";
    }

    @PostMapping
    public String save(@Valid TodoDetailReqVO todoDetailReqVO) {
        todoDetailService.save(todoDetailReqVO);
        return "redirect:" + ApiVersion.WEB_V1 + "/user/todo?finished=false";
    }


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

    @PostMapping("{id}/modify")
    public String update(@PathVariable Long id,
                         @Valid TodoDetailReqVO todoDetailReqVO,
                         Model model) {
        todoDetailService.update(id, todoDetailReqVO);

        TodoDetailListReqVO todoDetailListReqVO = new TodoDetailListReqVO();
        todoDetailListReqVO.setFinished(false);
        return listTodoList(todoDetailListReqVO, model);
    }


    @PostMapping("{id}")
    public void finish(@PathVariable Long id) {
        todoDetailService.finish(id);
    }


}
