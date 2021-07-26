package com.example.demo.app.task;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    /**
     * タスクの一覧を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String task(Model model) {

        //Taskのリストを取得する
        List<Task> taskList = taskService.findAll();

        model.addAttribute("taskList", taskList);
        model.addAttribute("title", "タスク一覧");

        return "task/index";
    }

    
    
    
    

}
