package com.example.demo.app.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    
    /**
     * 一件タスクデータを取得し、フォーム内に表示
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String updateTask(Model model) {

        Optional<Task> taskOpt = taskService.getTask(1);
        Task task = new Task();
        
        if(taskOpt.isPresent()) {
        	task = taskOpt.get();
        }

        model.addAttribute("task", task);
        List<Task> taskList = taskService.findAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("title", "更新用フォーム");

        return "task/index";
    }
    
    /**
     * タスクデータを一件挿入
     * @param model
     * @return
     */
    @PostMapping("/insert")
    public String insert(Model model) {

        Task task = new Task();
        task.setUserId(1);
        task.setTypeId(1);
        task.setTitle("インサート");
        task.setDetail("インサートの詳細です");
        String date = "2021-03-03 15:00:00";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        task.setDeadline(LocalDateTime.parse(date, dtFt));
    
        taskService.insert(task);
        List<Task> taskList = taskService.findAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("title", "タスク一覧（バリデーション）");
        return "task/index";
        
    }
    
    
    /**
     * タスクidを取得し、一件のデータ更新
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(Model model) {
    	
    	Task task = new Task();
    	task.setId(1);
        task.setUserId(1);
        task.setTypeId(3);
        task.setTitle("アップデート");
        task.setDetail("アップデートの詳細です");
        String date = "2021-07-27 15:12:34";
        DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        task.setDeadline(LocalDateTime.parse(date, dtFt));

    	//更新処理
    	taskService.update(task);
   
    	List<Task> taskList = taskService.findAll();
        model.addAttribute("taskList", taskList);
        model.addAttribute("title", "タスク一覧（バリデーション）");
        return "task/index";
        
    }
    
    
    /**
     * タスクidを取得し、一件のデータ削除
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String delete(Model model) {

    	//タスクを一件削除しリダイレクト
        taskService.deleteById(1);
        return "redirect:/task";
    }
    
    /**
     * 選択したタスクタイプのタスク一覧を表示
     * @param model
     * @return
     */
    @GetMapping("/selectType")
    public String selectType( Model model) {

        List<Task> taskList = taskService.findByType(1);

        model.addAttribute("taskList", taskList);
        model.addAttribute("title", "タスク一覧");

        return "task/index";
    }
    
    

}