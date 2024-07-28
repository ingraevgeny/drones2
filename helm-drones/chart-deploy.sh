#!/bin/bash

svc[drones]=8083
svc[clients]=8081
svc[approot]=8085


for key in "${!svc[@]}"
do
  service=$key
  port=${svc[$key]}
  helm uninstall $service
  kill -9 `ps -ef | grep $service | awk 'NR==1{print $2}'`

  echo "install " $service
  helm install $service oci://registry-1.docker.io/dock999evg/$service --version 201-dev

  kubectl wait --for=condition=Ready pod/$service

  trap 'kill 0' SIGINT;
  kubectl port-forward $service $port:$port > /dev/null &

  echo =================================
do
