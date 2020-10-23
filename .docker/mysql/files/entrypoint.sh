#!/bin/bash
echo "liberando acesso ao usuario $MYSQL_USER para conexao remota..."

i=1
res=`netstat |grep -i ":3306" | grep -i "ESTABLISHED" | head -1`
while [ "$i" -le 5 && -z "$res" ]
do
  i=$(($i+1))
  sleep 2
done

echo "grant all privileges on *.* to '$MYSQL_USER'@'%' identified by '$MYSQL_ROOT_PASSWORD'" > ./grant.sql
mysql -u $MYSQL_USER -p${MYSQL_ROOT_PASSWORD} < ./grant.sql