package com.andreych.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = "com.andreych")
public class PracticeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(PracticeApplication.class, args);
		Graph graph = new Graph();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addVertex('G');

		graph.addEdge(0, 1, 3);
		graph.addEdge(0, 2, 5);
		graph.addEdge(0, 3, 7);
		graph.addEdge(1, 4, 6);
		graph.addEdge(2, 4, 4);
		graph.addEdge(2, 3, 3);
		graph.addEdge(3, 5, 3);
		graph.addEdge(4, 6, 6);
		graph.addEdge(5, 6, 4);

		System.out.println("Элементы имеют кратчайшие пути из точки A: ");
		graph.path();
		graph.clean();
	}

}
