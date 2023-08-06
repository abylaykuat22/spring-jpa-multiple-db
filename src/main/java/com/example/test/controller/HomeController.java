package com.example.test.controller;

import com.example.test.models.Item;
import com.example.test.models2.Task;
import com.example.test.repository.ItemRepository;
import com.example.test.repository2.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/")
  public String homePage() {
    System.out.println("asd");
    System.out.println(taskRepository.findAll().stream().map(Task::getName).toList());
    System.out.println(itemRepository.findAll().stream().map(Item::getName).toList());
    return null;
  }
}
