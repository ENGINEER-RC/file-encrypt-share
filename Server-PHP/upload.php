<?php
include 'security.php';
include 'uservar.php';

if (!(isset($_POST['CryptU']) && isset($_POST['json_user']))) {
    echo "null";
    exit();
}
if (isset($_FILES["userfile"]["error"])) {
    if ($_FILES["userfile"]["error"] > 0) {
        echo "null";
        exit();
    }
} else {
    echo "null";
    exit();
}

$CryptU    = $_POST['CryptU'];
$Servar    = file_get_contents("Server.txt");
$json_user = json_decode(Security::decrypt($_POST['json_user'], $Servar));

$echo_out = verify_Return($CryptU, $foreign);
if (!$echo_out) {
    echo "null";
    exit();
}
$filename = $_FILES["userfile"]["name"];
$forkname = $filename;
$maxsize  = 2097152;
if ($_FILES["userfile"]["size"] > $maxsize) {
    echo "Error: File size is larger than the allowed limit.";
    exit();
}
$userpass     = verify_share($echo_out, $foreign);
$file_in_data = file_get_contents($_FILES["userfile"]["tmp_name"]);

$dir = "Upload\\" . $echo_out . "\\" . $filename;
$dir1 = "Upload\\" . $echo_out;
if(!is_dir($dir1)){mkdir($dir1);}
if (file_exists($dir)) {
    $lol      = strpos($filename, ".", 1);
    $filename = substr($filename, 0, $lol) . "_" . rand(10000, 100000) . substr($filename, $lol);
}
file_put_contents("Upload\\" . $echo_out . "\\" . $filename,$file_in_data);
echo "Your file was uploaded successfully as " . $filename . "\n";



for ($i = 0; $i < count($json_user); $i++) {
    if ($json_user[$i] != $echo_out) {
			$lol      = strpos($forkname, ".", 1);
			$forkname = substr($forkname,0, $lol) . "_" .$echo_out. substr($forkname, $lol);
        if (verify_share($json_user[$i], $foreign)) {
            $newfiledata = Security::encrypt(Security::decrypt($file_in_data, $userpass), verify_share($json_user[$i], $foreign));
            $dir         = "Upload\\" . $json_user[$i] . "\\" . $forkname;
			$dir1 = "Upload\\" .$json_user[$i];
			if(!is_dir($dir1)){mkdir($dir1);}
            if (file_exists($dir)) {
                $lol      = strpos($forkname, ".", 1);
                $forkname = substr($forkname,0, $lol) . "_" . rand(10000, 100000) . substr($forkname, $lol);
            }
            file_put_contents("Upload\\" . $json_user[$i] . "\\" . $forkname,$newfiledata);
            echo "Your file was share successfully as " . $forkname . " To User: " . $json_user[$i] . "\n";
        }
    }
}

