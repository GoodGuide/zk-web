#!/bin/bash

if [[ -n $ZK_PORT_2181_TCP ]]; then
  export ZKWEB_DEFAULT_NODE="zk:$ZK_PORT_2181_TCP_PORT"
  zk_addr="$ZK_PORT_2181_TCP_ADDR"
  echo "$zk_addr zk" >> /etc/hosts
fi


exec $@
