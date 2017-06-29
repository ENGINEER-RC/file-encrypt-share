<?php
include 'security.php';
include 'uservar.php';
if(!(isset($_POST['pass'])&&isset($_POST['user']))){echo "null";exit();}
$userU=$_POST['user'];
$userP=$_POST['pass'];
$filedata=file_get_contents("Server.txt");
$user=Security::decrypt($userU,$filedata);
$password=Security::decrypt($userP,$filedata);
$echo_out=verify_user($user,$password,$foreign);
if($echo_out)
{
	echo Security::encrypt($echo_out,$password);
}


//echo $rile=substr(Security::encrypt(substr($userP,16),substr($userP,0,16)),0,16);
?>
