<?php
if(!(isset($_POST['CryptU'])&&isset($_POST['file_name']))){echo "null";exit();}
$CryptU=$_POST['CryptU'];
$Filename=$_POST['file_name'];
include 'security.php';
include 'uservar.php';
$echo_out=verify_Return($CryptU,$foreign);
if($echo_out)
{
	$dir="Upload\\".$echo_out."\\".$Filename;
	if(!file_exists($dir)){echo "##";exit();}
	$filedata=file_get_contents($dir);
	$filedata=$filedata."\\".$Filename;
	echo $filedata;
}
else {echo "#failed";exit();}

?>