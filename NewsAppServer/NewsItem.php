<?php

class NewsItem {
	
	private $id;
	
	public $title;
	
	public $content;
	
	public $date;
	
	//private $img;
	
	public $cat;
	
	public function NewsItem($id){
		$this->id = $id;
	}
	/*
	public function getId(){
		return $id;
	}
	
	public function getTitle(){
		return $title;
	}
	
	public function getContent(){
		return $content;
	}
	
	public function getDate(){
		return $date;
	}
	
	public function getImage(){
		return $img;
	}
	
	public function getCat(){
		return $cat;
	}
	
	public function setId($id){
		$this->id = $id;
	}
	
	public function setTitle($title){
		$this->title = $title;
	}
	
	public function setContent($content){
		$this->content = $content;
	}
	
	public function setDate($date){
		$this->date = $date;
	}
	
	
	
	public function setImage($img){
		$this->img = $img;
	}
	
	public function setCat($cat){
		$this->cat = $cat;
	}
	
	*/
	
}