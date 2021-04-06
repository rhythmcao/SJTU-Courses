## The default port number is 2680, make sure this port is idle 

##TCP Usage:

[] is the parameter

#TCPServer:
1. java -jar TCPServer.jar
2. java -jar TCPServer.jar [ip that the server wants to bind]
The received file will be saved in the default directory where TCPServer.jar resides with default name

#TCPClient:
java -jar TCPClient.jar [hostname or ip of server]
Then type in the absolute filepath and filename according to the instructions


##RDTUDP Usage:

[] is the parameter

#RDTReciever
java -jar RDTReceiver.jar
The file will be saved in binary form in the default directory where RDTReceiver.jar resides with default filename 'sample'+[count]

#RDTSender
java -jar RDTSender [hostname or ip of server] [absolute filepath and filename to upload]