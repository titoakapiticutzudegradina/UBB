#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

int main(){
    int c;
    struct sockaddr_in server;
    uint16_t a;
    uint16_t nrdiv;
    uint16_t div;

    //Create client socket
    c = socket(AF_INET, SOCK_STREAM, 0);
    if(c < 0){
        printf("Error! Creation client server\n");
        return 1;
    }

    //Set server data
    memset(&server, 0, sizeof(server));
    server.sin_port = htons(1234);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = inet_addr("127.0.0.1");

    //Connect to server
    if(connect(c, (struct sockaddr*)&server, sizeof(server))<0){
        printf("Error! Connection to server\n");
        return 1;
    }

    printf("a=");
    scanf("%hu", &a);
    a = htons(a);
    send(c, &a, sizeof(a), 0);
    recv(c, &nrdiv, sizeof(nrdiv),0);
    nrdiv = ntohs(nrdiv);
    printf("The divisors are: ");
    for(int i = 0; i< nrdiv; i++){
        recv(c, &div, sizeof(div),0);
        div = ntohs(div);
        printf("%hu ", div);
        printf(" ");
    }
    close(c);
}
