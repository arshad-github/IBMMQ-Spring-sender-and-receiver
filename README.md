# Getting Started

### IBMMQ docker image

* [Pull IBMMQ docker image] (https://hub.docker.com/r/ibmcom/mq/)
* Then run:

```bash
sudo docker run \
  --env LICENSE=accept \
  --env MQ_QMGR_NAME=QM1 \
  --publish 1414:1414 \
  --publish 9443:9443 \
  --detach \
  ibmcom/mq
```

* IBMMQ console is accessible on localhost:9443/ibmmq/console/
* default credentials
    - username "admin"
    - password "passw0rd"
* QM1 is the default queue manager created along with the following queues:
    - DEV.DEAD.LETTER.QUEUE
    - DEV.QUEUE.1
    - DEV.QUEUE.2
    - DEV.QUEUE.3
    
### Usage

* Send GET request to localhost:8080/send -> the message within content.yml will be sent to DEV.QUEUE.1
* The JmsReceiver within Receiver.java will consume this message and print it out.  
