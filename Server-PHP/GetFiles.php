<?php
if(!isset($_POST['CryptU'])){echo "null";exit();}
$CryptU=$_POST['CryptU'];
include 'security.php';
include 'uservar.php';
$echo_out=verify_Return($CryptU,$foreign);
if($echo_out)
{
	$dir="Upload\\".$echo_out;
	if(!is_dir($dir)){mkdir($dir);}
	$path=scandir(($dir),1);
	//var_dump($path);
	if(count($path)==2){echo "##";exit();}
	for($i=0;$i<count($path)-2;$i++){
		echo $path[$i]."\n";
	}
	
}
else {echo "#failed";exit();}

?>