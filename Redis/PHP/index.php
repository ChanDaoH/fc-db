<?php
function handler($event, $context) {
    $host = $_ENV['REDIS_HOST'];
    $port = $_ENV['REDIS_PORT'];
    $pwd = $_ENV['REDIS_PASSWORD'];
    $redis = new Redis();
    if ($redis->connect($host, $port) == false) {
            die($redis->getLastError());
      }
    if ($redis->auth($pwd) == false) {
            die($redis->getLastError());
     }
    if ($redis->set("foo", "bar") == false) {
            die($redis->getLastError());
    }
    $value = $redis->get("foo");
    echo $value;
    return "hello world";
}