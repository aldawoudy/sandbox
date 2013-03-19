<?php

include 'NewsItem.php';
$titles = array(
		"EN DIRECT. Neige et verglas : encore pres de 60 000 foyers prives d'electricite",
		"Neige : attaques, Ayrault et ses ministres se defendent",
		"Ile-de-France : le verglas perturbe les transports",
		"Marseille : deux morts dans une fusillade a la kalachnikov",
		"99 500 emplois detruits en un an",
		
		"Accord sur l'emploi : l'UMP pourrait voter la loi avec le PS",
		"Jean Tiberi condamne : 10 mois avec sursis, 3 ans d'ineligibilite",
		"L'Elysee table sur 3,7% de deficit public en 2013",
		"Hollande : 'Tous ceux qui veulent m'interpeller peuvent le faire'",
		"Chahute a Dijon, Hollande appelle a 'la mobilisation' pour l'emploi",
		
		"EN IMAGES. Les belles du salon automobile de Geneve",
		"Une 2CV de legende et des voitures de Coluche aux encheres",
		"VIDEO. Diesel : Arnaud Montebourg clot le debat pour 2013",
		"PSA Peugeot Citroen se lance dans le livret d'epargne",
		"VIDEO. Nous avons teste la Renault Zoe",
		
		"2013 : Android devrait depasser Apple sur le marche des tablettes",
		"Le salaire moyen atteint 2 410 euros bruts mensuels",
		"Poissy : la production de l'usine Peugeot stoppee",
		"Retraites : Hollande confirme l'urgence d'une reforme",
		"A bord du plus gros paquebot d'Europe"
		);

$contents = $titles;
$date = "Publie le 13.03.2013, 11h00";
$categories = array( 
		1,1,1,1,1,
		2,2,2,2,2,
		3,3,3,3,3,
		4,4,4,4,4
		);


$news = array();

for($i = 1 ; $i < 21; $i++) {
	
	$item = new NewsItem($i);
	$item->title= $titles[$i-1];
	$item->content= $titles[$i-1];
	$item->date= $date;
	$item->cat = $categories[$i-1];
	$news[$i] = $item;
	
	
}

//var_dump($news);


$catNews = array();
$s = 1;
$e = 6;
if(isset($_GET['c'])) {
	switch($_GET['c']) {
		case 'latest':
			$s = 1;
			$e = 6;
			break;
		case 'politics':
			$s = 6;
			$e = 11;
			break;
		case 'sports':
			$s = 11;
			$e = 16;
			break;
		case 'economies':
			$s = 16;
			$e = 21;
			break;
	}
}



for($i = $s ; $i < $e ; $i++) {
	$catNews[$i] = (array)$news[$i];
}



//var_dump($catNews);
$toEncode = array("news" => $catNews);

header('Content-type: application/json');
$out = array_values($catNews);
$json = json_encode(array("news" => $out));
$json = str_replace('\\u0000NewsItem\u0000id', "id", $json);
echo $json;

