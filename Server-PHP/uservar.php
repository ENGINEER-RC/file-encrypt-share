<?php
$userdata='{"jsondata":[{"UserName":"Tomrock",  "Password":">USUWJ222OLRTRTZ","Return":"IPpXL+YE7pA8Pq+b"},'
						.'{"UserName":"Clinton","Password":">UPQUJ222QRURPRR","Return":"cbSdsajWytDyLC+Z"},'
						.'{"UserName":"Kevin",  "Password":"TcUSLRLLS11PNUMA","Return":"yozqBAWAxp/ZrNeP"},'
						.'{"UserName":"Ryan",   "Password":"RhjNS4444444SMmZ","Return":"xyPN9j00aNlliklA"}]}';

$foreign=json_decode($userdata);
function verify_user($user,$pass,$foreign){
	$iforeign=count($foreign->jsondata);
	$k=0;
	for($j=0;$j<$iforeign;$j++){
		
	if(($foreign->jsondata[$j]->UserName==$user)&&($foreign->jsondata[$j]->Password==$pass)){
		return $foreign->jsondata[$j]->Return;
		$k=$j;
		break;
	}
	
	}
	if($k==0){return null;}
}
function verify_Return($Returner,$foreign){
	$iforeign=count($foreign->jsondata);
	$k=0;
	for($j=0;$j<$iforeign;$j++){
	if($foreign->jsondata[$j]->Return==$Returner){
		return $foreign->jsondata[$j]->UserName;$k=1;
		break;
	}
	
	}
	if($k==0){return null;}
}
function verify_share($user,$foreign){
	$iforeign=count($foreign->jsondata);
	$k=0;
	for($j=0;$j<$iforeign;$j++){
		
	if($foreign->jsondata[$j]->UserName==$user){
		return $foreign->jsondata[$j]->Password;
		$k=$j;
		break;
	}
	
	}
	if($k==0){return null;}
}


?>