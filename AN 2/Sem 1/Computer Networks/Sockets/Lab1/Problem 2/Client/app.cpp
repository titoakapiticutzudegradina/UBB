#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <cstdlib>

int main(){
    int c;
    struct sockaddr_in server;
    //char sth[MAX];

    c = socket(AF_INET, SOCK_STREAM,0);
    if(c < 0){
        printf("Error! Creation client socket\n");
        return 1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(1234);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = inet_addr("127.0.0.1");

    if(connect(c, (struct sockaddr*)&server, sizeof(server)) < 0){
        printf("Error! Connection\n");
        return 1;
    }

    printf("Connection to the server is good!\n");


    //printf("Give a string(put '_' instead of ' '): ");
    //scanf("%s",sth);
    //length = strlen(sth);
    //length = htonl(length);
    //send(c, &length, sizeof(length), 0);
    //send(c, sth, length * sizeof(char), 0);
    //recv(c, &nrspaces, sizeof(nrspaces),0);
    //nrspaces = ntohl(nrspaces);
    //printf("The number of spaces is: %d\n", nrspaces);

    //we provide the string
    char sth;
    printf("Give a string(with '_' not ' '): ");
    scanf("%s",&sth);
    //char *sth = new char[strlen(TEXT)];
    //sth = (char*)TEXT;
    system("sleep 1.5");

    //send the size of the string
    uint16_t length = htons(strlen(&sth));
    send(c, &length, sizeof(length), 0);

    //send the string
    send(c, &sth, length * sizeof(char), 0);

    //receive the result
    uint16_t result;
    recv(c, &result, sizeof(result),0);
    result = ntohs(result);
    printf("The number of spaces is: %d\n",result);
    close(c);
}
