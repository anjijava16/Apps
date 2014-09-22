<?php
$con = mysql_connect("localhost","root","root");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("itravel", $con);
$result = mysql_query("SELECT * FROM user");
echo "<table border='1'>
<tr>
<th>User Name</th>
<th>Password</th>
</tr>";

while($row = mysql_fetch_array($result))
  {
  echo "<tr>";
  echo "<td>" . $row['username'] . "</td>";
  echo "<td>" . $row['password'] . "</td>";
  echo "</tr>";
  }
echo "</table>";

mysql_close($con);
?>